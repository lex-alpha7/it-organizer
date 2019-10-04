import React from 'react';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash, faEdit } from '@fortawesome/free-solid-svg-icons'

class ProjectListItem extends React.Component {
    deleteProject = async (projectId) => {
        let delete_project_url = `http://localhost:8080/it-organizer/rest/project/delete/${projectId}`;
        axios.delete(delete_project_url);
    }

    render() {
        return <div className="btn-group dropdown-item">
                <button type="button" className="btn btn-outline-primary" onClick={() => this.props.activateAndGetTickets(this.props.project)}>{this.props.project.name}</button>
                <button type="button" className="btn btn-outline-warning" onClick={() => this.props.editProject(this.props.project.id)}><FontAwesomeIcon icon={faEdit} /></button>
                <button type="button" className="btn btn-outline-danger" onClick={() => this.deleteProject(this.props.project.id)}><FontAwesomeIcon icon={faTrash} /></button>
            </div>
    }
}

export default ProjectListItem;