import React from 'react';
import axios from 'axios';
import RichEditor from '../RichEditor'
import ProgressList from '../progress/ProgressList'
import TicketLinkList from '../ticketLink/TicketLinkList'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFileMedical, faSave, faCaretRight } from '@fortawesome/free-solid-svg-icons'

class TicketEditor extends React.Component {
    state = {
        id: undefined,
        projectId: undefined,
        key: undefined,
        priority: undefined,
        name: undefined,
        stepsToReproduce: undefined,
        workspace: undefined,
        displayedName: undefined,
        status: undefined,
        links: undefined,
    }

    constructor(props) {
        super(props);
        this.state = {
            id: this.props.ticketForEdit.id,
            projectId: this.props.ticketForEdit.projectId,
            key: this.props.ticketForEdit.key,
            priority: this.props.ticketForEdit.priority,
            name: this.props.ticketForEdit.name,
            stepsToReproduce: this.props.ticketForEdit.stepsToReproduce,
            workspace: this.props.ticketForEdit.workspace,
            displayedName: this.props.ticketForEdit.displayedName,
            status: this.props.ticketForEdit.status,
            links: this.props.ticketForEdit.links,
            progresses: this.props.progresses,
            ticketLinks: this.props.ticketLinks
         }
    }

    componentDidUpdate(prevProps) {
        if (this.props.ticketForEdit.id !== prevProps.ticketForEdit.id ||
            this.props.ticketForEdit.projectId !== prevProps.ticketForEdit.projectId ||
            this.props.ticketForEdit.key !== prevProps.ticketForEdit.key ||
            this.props.ticketForEdit.priority !== prevProps.ticketForEdit.priority ||
            this.props.ticketForEdit.name !== prevProps.ticketForEdit.name ||
            this.props.ticketForEdit.stepsToReproduce !== prevProps.ticketForEdit.stepsToReproduce ||
            this.props.ticketForEdit.workspace !== prevProps.ticketForEdit.workspace ||
            this.props.ticketForEdit.displayedName !== prevProps.ticketForEdit.displayedName ||
            this.props.ticketForEdit.status !== prevProps.ticketForEdit.status ||
            this.props.ticketForEdit.links !== prevProps.ticketForEdit.links) {
            this.setState({
                id: this.props.ticketForEdit.id,
                projectId: this.props.ticketForEdit.projectId,
                key: this.props.ticketForEdit.key,
                priority: this.props.ticketForEdit.priority,
                name: this.props.ticketForEdit.name,
                stepsToReproduce: this.props.ticketForEdit.stepsToReproduce,
                workspace: this.props.ticketForEdit.workspace,
                displayedName: this.props.ticketForEdit.displayedName,
                status: this.props.ticketForEdit.status,
                links: this.props.ticketForEdit.links
            });
        }
    }

    saveTicket = async () => {
        axios.put('http://localhost:8080/it-organizer/rest/ticket/save',
        {
            id: this.state.id,
            projectId: this.state.projectId,
            key: this.state.key,
            priority: this.state.priority,
            name: this.state.name,
            stepsToReproduce: this.state.stepsToReproduce,
            workspace: this.state.workspace,
            displayedName: this.state.displayedName,
            status: this.state.status,
        }).then((result) => {
            if (result.status === 200) {
                this.props.showSuccessAlert('Тикет успешно сохранен');
            } else {
                this.props.showErrorAlert('При сохранении тикеты произошла ошибка');
            }
        });
    }

    updateWorkSpace = (json) => {
        this.setState({workspace: json})
    }

    updateStepsToReproduce = (json) => {
        this.setState({stepsToReproduce: json})
    }

    onKeyChange(value) {
        this.setState({
            key: value
        });
    }

    onPriorityChange(value) {
        this.setState({
            priority: value
        });
    }

    onNameChange(value) {
        this.setState({
            name: value
        });
    }

    onStepsToReproduceChange(value) {
        this.setState({
            stepsToReproduce: value
        });
    }

    onWorkspaceChange(e) {
        console.log('editorState = ')

    }

    onStatusChange(value) {
        this.setState({
            status: value
        });
    }

    render() {
        return(
            <div>
                <nav className="navbar navbar-expand-sm  navbar-dark bg-dark">
                    <ul className="navbar-nav">
                        <li className='nav-item'>
                            <button type="button" className="btn btn-outline-success btn-sm" onClick={() => this.saveTicket()}>
                                <FontAwesomeIcon icon={faSave} /> Save Ticket
                            </button>
                        </li>
                        <li className='nav-item'>
                            <button type="button" className="btn btn-outline-success btn-sm"  data-toggle="modal" data-target="#progressModal">
                              <FontAwesomeIcon icon={faFileMedical} /> Add Progress
                            </button>
                        </li>
                        <li className='nav-item'>
                            <button type="button" className="btn btn-outline-success btn-sm"  data-toggle="modal" data-target="#ticketLinkModal">
                                <FontAwesomeIcon icon={faFileMedical} /> Add Ticket Link
                            </button>
                        </li>
                    </ul>
                </nav>

                <div className="modal" id="progressModal">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                    <h4 className="modal-title">Добавление прогресса</h4>
                                <button type="button" className="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div className="modal-body">
                                <div className="form-group">
                                    <label htmlFor="progressText">Прогресс:</label>
                                    <textarea className="form-control" rows="5" id="progressText"></textarea>
                                </div>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-success" data-dismiss="modal" onClick={() => this.props.saveProgress(document.getElementById("progressText").value)}>Сохранить</button>
                                <button type="button" className="btn btn-danger" data-dismiss="modal">Отмена</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="modal" id="ticketLinkModal">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h4 className="modal-title">Добавление ссылки</h4>
                                <button type="button" className="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div className="modal-body">
                                <div className="form-group">
                                    <label htmlFor="ticketLinkName">Название ссылки:</label>
                                    <input type="text" className="form-control" id="ticketLinkName" />
                                </div>
                                <div className="form-group">
                                    <label htmlFor="ticketLinkLink">Ссылка:</label>
                                    <input type="text" className="form-control" id="ticketLinkLink" />
                                </div>
                                <div className="form-group">
                                    <label htmlFor="ticketLinkType">Тип:</label>
                                    <select className="form-control" id="ticketLinkType">
                                        <option>LINK_TO_TICKET</option>
                                        <option>ATTACHMENT</option>
                                        <option>USEFUL_LINK</option>
                                    </select>
                                </div>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-success" data-dismiss="modal" onClick={() => this.props.saveTicketLink(document.getElementById("ticketLinkName").value, document.getElementById("ticketLinkLink").value, document.getElementById("ticketLinkType").value)}>Сохранить</button>
                                <button type="button" className="btn btn-danger" data-dismiss="modal">Отмена</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div className='container-fluid'>
                    <div className="row">
                        <div className="col-sm-4">
                            <div className="form-group">
                                <label>Key:</label>
                                <input id='ticketKey' name='ticketKey' type='text' className='form-control'
                                    value={this.state.key || ''} required='required'
                                    onChange={e => this.onKeyChange(e.target.value)}/>
                                <div className="valid-feedback">Valid.</div>
                                <div className="invalid-feedback">Please fill out this field.</div>
                            </div>
                            <div className="form-group">
                                <label>Name:</label>
                                <input id='ticketName' name='ticketName' type='text' className='form-control'
                                    value={this.state.name || ''} required='required'
                                    onChange={e => this.onNameChange(e.target.value)}/>
                                <div className="valid-feedback">Valid.</div>
                                <div className="invalid-feedback">Please fill out this field.</div>
                            </div>
                            <div className="form-group">
                                <label>Priority:</label>
                                <input id='ticketPriority' name='ticketPriority' type='text' className='form-control'
                                    value={this.state.priority || ''} required='required'
                                    onChange={e => this.onPriorityChange(e.target.value)}/>
                                <div className="valid-feedback">Valid.</div>
                                <div className="invalid-feedback">Please fill out this field.</div>
                            </div>
                            <div className="form-group">
                                <label>Status:</label>
                                <select className="form-control" id='ticketStatus' name='ticketStatus'
                                    value={this.state.status || ''} required='required'
                                    onChange={e => this.onStatusChange(e.target.value)}>
                                    <option>OPEN</option>
                                    <option>IN_PROGRESS</option>
                                    <option>WAIT</option>
                                    <option>CLOSED</option>
                                </select>
                                <div className="valid-feedback">Valid.</div>
                                <div className="invalid-feedback">Please fill out this field.</div>
                            </div>
                            <div className="form-group">
                                <TicketLinkList ticketLinks={this.state.ticketLinks}
                                                                saveTicket={this.saveTicket}
                                                                saveTicketLink={this.props.saveTicketLink}
                                                                updateTicket={this.props.updateTicket}
                                                                ticket={this.props.ticketForEdit}
                                                                showSuccessAlert={this.props.showSuccessAlert}
                                                                showErrorAlert={this.props.showErrorAlert}/>
                                <div className="valid-feedback">Valid.</div>
                                <div className="invalid-feedback">Please fill out this field.</div>
                            </div>
                        </div>
                        <div className="col-sm-8">
                            <div className="form-group">
                                <label>Steps to Reproduce:</label>
                                <RichEditor field={this.state.stepsToReproduce || ''} updateWorkSpace={this.updateStepsToReproduce}/>
                                <div className="valid-feedback">Valid.</div>
                                <div className="invalid-feedback">Please fill out this field.</div>
                            </div>
                            <div className="form-group">
                                <label>Tasks:</label>

                                <div className="valid-feedback">Valid.</div>
                                <div className="invalid-feedback">Please fill out this field.</div>
                            </div>
                            <div className="form-group">
                                <label>Progress:</label>
                                <ProgressList progresses={this.state.progresses}
                                    saveTicket={this.saveTicket}
                                    saveProgress={this.props.saveProgress}
                                    updateTicket={this.props.updateTicket}
                                    ticket={this.props.ticketForEdit}
                                    showSuccessAlert={this.props.showSuccessAlert}
                                    showErrorAlert={this.props.showErrorAlert}/>
                                <div className="valid-feedback">Valid.</div>
                                <div className="invalid-feedback">Please fill out this field.</div>
                            </div>
                        </div>
                    </div>
                    <div className='row'>
                        <div className="col-sm-12">
                            <div className="form-group">
                                <label>Workspace:</label>
                                <RichEditor field={this.state.workspace} updateWorkSpace={this.updateWorkSpace}/>
                                <div className="valid-feedback">Valid.</div>
                                <div className="invalid-feedback">Please fill out this field.</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default TicketEditor;