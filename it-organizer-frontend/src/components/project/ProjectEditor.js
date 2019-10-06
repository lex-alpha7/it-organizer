import React from 'react';
import axios from 'axios';

class ProjectEditor extends React.Component {
    state = {
        id: undefined,
        name: undefined
    }

    constructor(props) {
        super(props);
        this.state = {
            id: props.projectForEdit.id,
            name: props.projectForEdit.name
        }
    }

    componentDidUpdate(prevProps) {
        if (this.props.projectForEdit.id !== prevProps.projectForEdit.id ||
            this.props.projectForEdit.name !== prevProps.projectForEdit.name) {
            this.setState({
                id: this.props.projectForEdit.id,
                name: this.props.projectForEdit.name
            });
        }
    }

    saveProject = async (e) => {
        e.preventDefault();
        const projectName = e.target.elements.projectName.value;
        const projectId = e.target.elements.projectId.value;
        axios.put('http://localhost:8080/it-organizer/rest/project/save',
        {
            name: projectName,
            id: projectId
        }).then((result) => {
            if (result.status === 200) {
                this.props.showSuccessAlert('Проект успешно сохранен');
            } else {
                this.props.showErrorAlert('При сохранении проекта произошла ошибка');
            }
        });
    }


    onProjectNameChange(value) {
        this.setState({
            name: value
        });
    }

    render() {
        return(
            <div id='projectEdit' className='container'>
                <div className='jumbotron'>
                    <form className="was-validated" onSubmit={this.saveProject}>
                        <input type='hidden' name='projectId' id='projectId' value={this.state.id} />
                        <div className="form-group">
                            <label>Project Name:</label>
                            <input id='projectName' name='projectName' type='text' className='form-control'
                                value={this.state.name} required='required'
                                onChange={e => this.onProjectNameChange(e.target.value)}/>
                            <div className="valid-feedback">Valid.</div>
                            <div className="invalid-feedback">Please fill out this field.</div>
                        </div>
                        <button type='submit' className='btn btn-primary'>Сохранить</button>
                    </form>
                </div>
            </div>);
    }
}

export default ProjectEditor;