import React from 'react';
import axios from 'axios';

class ProjectEditor extends React.Component {
    state = {
        id: undefined,
        name: undefined
    }

    constructor(props) {
        super(props);
        this.getProject(props.project_id);
    }

    getProject = async (project_id) => {
        const project_edit_url = `http://localhost:8080/it-organizer/rest/project/edit/${project_id}`;
        const project_edit_rest = await fetch(project_edit_url);
        const project_for_edit = await project_edit_rest.json();
        console.log(project_for_edit);
        this.setState({id: project_for_edit.id});
        this.setState({name: project_for_edit.name});
    }

    saveProject = async (e) => {
        e.preventDefault();
        const projectName = e.target.elements.projectName.value;
        const projectId = e.target.elements.projectId.value;
        axios.put('http://localhost:8080/it-organizer/rest/project/save',
        {
            name: projectName,
            id: projectId
        });
    }


    onProjectNameChange(value) {
        this.setState({
            name: value
        });
    }

    render() {
        return(
            <form className="was-validated" onSubmit={this.saveProject}>
                <input type='hidden' name='projectId' id='projectId' value={this.state.id} />
                <div class="form-group">
                    <label>Project Name:</label>
                    <input id='projectName' name='projectName' type='text' className='form-control'
                        value={this.state.name} required='required'
                        onChange={e => this.onProjectNameChange(e.target.value)}/>
                    <div className="valid-feedback">Valid.</div>
                    <div className="invalid-feedback">Please fill out this field.</div>
                </div>
                <button type='submit' className='btn btn-primary'>Сохранить</button>
            </form>);
    }
}

export default ProjectEditor;