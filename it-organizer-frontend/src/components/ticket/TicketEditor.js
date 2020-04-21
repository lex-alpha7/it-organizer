import React from 'react';
import axios from 'axios';
import RichEditor from '../RichEditor'
import ProgressList from '../progress/ProgressList'

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
            progresses: this.props.progresses
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

    save = async (e) => {
        e.preventDefault();
        axios.put('http://localhost:8080/it-organizer/rest/ticket/save',
        {
            id: this.state.id,
            projectId: this.state.projectId,
            key: e.target.elements.ticketKey.value,
            priority: e.target.elements.ticketPriority.value,
            name: e.target.elements.ticketName.value,
            stepsToReproduce: this.state.stepsToReproduce,
            workspace: this.state.workspace,
            displayedName: this.state.displayedName,
            status: e.target.elements.ticketStatus.value,
            links: this.state.links
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
            <div className='container-fluid'>
                <form className="was-validated" onSubmit={this.save}>
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
                                <label>Tasks:</label>

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
                                <label>Progress:</label>
                                <ProgressList progresses={this.state.progresses}
                                    saveProgress={this.props.saveProgress}
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
                    <button type='submit' className='btn btn-primary'>Сохранить</button>
                </form>
            </div>
        );
    }
}

export default TicketEditor;