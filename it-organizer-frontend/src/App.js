import React from 'react';
import ProjectList from './components/project/ProjectList';
import TicketList from './components/ticket/TicketList';
import MenuHeader from './components/MenuHeader'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import './css/main.css'

class App extends React.Component {
    state = {
        projects: undefined,
        tickets: undefined,
        activeProject: undefined,
        activeTicket: undefined
    }

    constructor(props) {
        super(props);
        this.getProjectList();
    }

    getProjectList = async () => {
        //e.preventDefault();
        const project_list_rest = await fetch('http://localhost:8080/it-organizer/rest/project/list');
        const project_list = await project_list_rest.json();
        console.log(project_list);
        this.setState({projects: project_list});
        this.setState({tickets: project_list});
    }

    activateProjectAndGetTickets = async (project) => {
        const activate_url = `http://localhost:8080/it-organizer/rest/project/activate/${project.id}`;
        const project_acivate_rest = await fetch(activate_url);
        this.setState({activeProject: project});
        this.getTicketList();
    }

    getTicketList = async () => {
        const ticket_list_rest = await fetch('http://localhost:8080/it-organizer/rest/ticket/list');
        const ticket_list = await ticket_list_rest.json();
        console.log(ticket_list);
        this.setState({tickets: ticket_list});
    }

    editTicket = (ticket) => {
        this.setState({activeTicket: ticket});
    }

    render() {
        return(
            <nav className="navbar navbar-expand-sm  navbar-dark bg-dark">
                <div className="collapse navbar-collapse" id="navbarText">
                    <ul className="navbar-nav">
                        <li className='nav-item'>
                            <ProjectList projects={this.state.projects} activateAndGetTickets={this.activateProjectAndGetTickets} />
                        </li>
                        <li className='nav-item'><TicketList tickets={this.state.tickets} editTicket={this.editTicket} /></li>
                    </ul>
                </div>
                <MenuHeader project={this.state.activeProject} ticket={this.state.activeTicket} />
            </nav>

        );
    }
}

export default App;