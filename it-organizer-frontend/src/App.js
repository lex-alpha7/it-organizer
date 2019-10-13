import React from 'react';
import axios from 'axios';
import ProjectList from './components/project/ProjectList';
import TicketList from './components/ticket/TicketList';
import NoteList from './components/note/NoteList';
import ReferenceLinkList from './components/referenceLink/ReferenceLinkList';
import ProjectEditor from './components/project/ProjectEditor';
import TicketEditor from './components/ticket/TicketEditor';
import NoteEditor from './components/note/NoteEditor';
import ReferenceLinkEditor from './components/referenceLink/ReferenceLinkEditor';
import MenuHeader from './components/MenuHeader'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import './css/main.css'
import $ from 'jquery';

class App extends React.Component {
    state = {
        projects: undefined,
        tickets: undefined,
        notes: undefined,
        referenceLinks: undefined,
        activeProject: undefined,
        activeTicket: undefined,
        ticketForEdit: undefined,
        projectForEdit: undefined,
        noteForEdit: undefined,
        referenceLinkForEdit: undefined,
        resultMessage: undefined
    }

    constructor(props) {
        super(props);
        this.getProjectList();
    }

    getProjectList = async () => {
        //e.preventDefault();
        const project_list_rest = await fetch('http://localhost:8080/it-organizer/rest/project/list');
        const project_list = await project_list_rest.json();
        this.setState({projects: project_list});
    }

    activateProjectAndGetTickets = async (project) => {
        const activate_url = `http://localhost:8080/it-organizer/rest/project/activate/${project.id}`;
        await fetch(activate_url);
        this.setState({activeProject: project});
        this.getTicketList();
        this.getNoteList();
        this.getReferenceLinkList();
    }

    getTicketList = async () => {
        const ticket_list_rest = await fetch('http://localhost:8080/it-organizer/rest/ticket/list');
        const ticket_list = await ticket_list_rest.json();
        this.setState({tickets: ticket_list});
    }

    getNoteList = async () => {
        const rest = await fetch('http://localhost:8080/it-organizer/rest/note/list');
        const list = await rest.json();
        this.setState({notes: list});
    }

    getReferenceLinkList = async () => {
        const rest = await fetch('http://localhost:8080/it-organizer/rest/reference_link/list');
        let list = await rest.json();
        console.log('list = ' + list)
        this.setState({referenceLinks: list});
    }

    cleanMainPart = () => {
        this.setState({
            projectForEdit: undefined,
            noteForEdit: undefined,
            referenceLinkForEdit: undefined
        });
    }

    editProject = async (projectId) => {
        this.cleanMainPart();
        let projectForEdit = undefined;
        if (projectId) {
            const project_edit_url = `http://localhost:8080/it-organizer/rest/project/edit/${projectId}`;
            const project_edit_rest = await axios(project_edit_url);
            projectForEdit = await project_edit_rest.data;
        } else {
            projectForEdit = {
                id: undefined,
                name: undefined
            }
        }
        this.setState({
            projectForEdit: projectForEdit
        });
    }

    editTicket = async (ticket) => {
        this.setState({activeTicket: ticket});
        //ticketForEdit
        this.cleanMainPart();
        let ticketForEdit = undefined;
        if (ticket && ticket.id) {
            const url = `http://localhost:8080/it-organizer/rest/ticket/edit/${ticket.id}`;
            const rest = await axios(url);
            ticketForEdit = await rest.data;
        } else {
            ticketForEdit = {
                id: undefined,
                projectId: undefined,
                key: undefined,
                priority: undefined,
                name: undefined,
                stepsToReproduce: undefined,
                workspace: undefined,
                displayedName: undefined,
                status: undefined,
                links: undefined
            }
        }
        this.setState({
            ticketForEdit: ticketForEdit
        });
    }

    editNote = async (noteId) => {
        this.cleanMainPart();
        let noteForEdit = undefined;
        if (noteId) {
            const url = `http://localhost:8080/it-organizer/rest/note/edit/${noteId}`;
            const rest = await axios(url);
            noteForEdit = await rest.data;
        } else {
            noteForEdit = {
                id: undefined,
                name: undefined
            }
        }
        this.setState({
            noteForEdit: noteForEdit
        });
    }

    editReferenceLink = async (referenceLinkId) => {
        this.cleanMainPart();
        let referenceLinkForEdit = undefined;
        if (referenceLinkId) {
            const url = `http://localhost:8080/it-organizer/rest/reference_link/edit/${referenceLinkId}`;
            const rest = await axios(url);
            referenceLinkForEdit = await rest.data;
        } else {
            referenceLinkForEdit = {
                id: undefined,
                name: undefined,
                link: undefined
            }
        }
        this.setState({
            referenceLinkForEdit: referenceLinkForEdit
        });
    }

    updateNavBar = () => {
        this.getProjectList();
        this.getTicketList();
        this.getNoteList();
        this.getReferenceLinkList();
    }

    showSuccessAlert = (message) => {
        this.setState({resultMessage: message});
        $('#result_alert').removeClass();
        $('#result_alert').addClass('alert alert-success  show');
        $("#result_alert").fadeTo(2000, 500).slideUp(500, function(){
            $("#result_alert").slideUp(500);
        });
        this.updateNavBar();
    }

    showErrorAlert = (message) => {
        this.setState({resultMessage: message});
        $('#result_alert').removeClass();
        $('#result_alert').addClass('alert alert-danger  show');
        $("#result_alert").fadeTo(2000, 500).slideUp(500, function(){
                    $("#result_alert").slideUp(500);
                });
        this.updateNavBar();
    }

    render() {
        return(
            <div>
                <nav className="navbar navbar-expand-sm  navbar-dark bg-dark">
                    <div className="collapse navbar-collapse" id="navbarText">
                        <ul className="navbar-nav">
                            <li className='nav-item'>
                                <ProjectList
                                    projects={this.state.projects}
                                    activateAndGetTickets={this.activateProjectAndGetTickets}
                                    editProject={this.editProject}
                                    showSuccessAlert={this.showSuccessAlert}
                                    showErrorAlert={this.showErrorAlert}/>
                            </li>
                            {this.state.tickets &&
                                <li className='nav-item'><TicketList tickets={this.state.tickets}
                                    editTicket={this.editTicket}
                                    showSuccessAlert={this.showSuccessAlert}
                                    showErrorAlert={this.showErrorAlert}/></li>
                            }
                            {this.state.notes &&
                                <li className='nav-item'><NoteList notes={this.state.notes}
                                    editNote={this.editNote}
                                    showSuccessAlert={this.showSuccessAlert}
                                    showErrorAlert={this.showErrorAlert}/></li>
                            }
                            {this.state.referenceLinks &&
                                <li className='nav-item'>
                                    <ReferenceLinkList referenceLinks={this.state.referenceLinks}
                                        editReferenceLink={this.editReferenceLink}
                                        showSuccessAlert={this.showSuccessAlert}
                                        showErrorAlert={this.showErrorAlert}/>
                                </li>
                            }
                        </ul>
                    </div>
                    <MenuHeader project={this.state.activeProject} ticket={this.state.activeTicket} />
                </nav>
                <div id='result_alert' className="fade">
                    {this.state.resultMessage && this.state.resultMessage}
                </div>
                {this.state.projectForEdit && <ProjectEditor projectForEdit={this.state.projectForEdit}
                                                      showSuccessAlert={this.showSuccessAlert}
                                                      showErrorAlert={this.showErrorAlert}/>
                }
                {this.state.noteForEdit && <NoteEditor noteForEdit={this.state.noteForEdit}
                                                      showSuccessAlert={this.showSuccessAlert}
                                                      showErrorAlert={this.showErrorAlert}/>
                }
                {this.state.referenceLinkForEdit && <ReferenceLinkEditor referenceLink={this.state.referenceLinkForEdit}
                                                      showSuccessAlert={this.showSuccessAlert}
                                                      showErrorAlert={this.showErrorAlert}/>
                }
                {this.state.ticketForEdit && <TicketEditor ticketForEdit={this.state.ticketForEdit}
                                                      showSuccessAlert={this.showSuccessAlert}
                                                      showErrorAlert={this.showErrorAlert}/>
                }
            </div>
        );
    }
}

export default App;