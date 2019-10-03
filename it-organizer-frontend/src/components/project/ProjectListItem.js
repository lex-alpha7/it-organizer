import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash, faEdit } from '@fortawesome/free-solid-svg-icons'

const ProjectListItem = (props) => {
    return <div className="btn-group dropdown-item">
            <button type="button" className="btn btn-outline-primary" onClick={() => props.activateAndGetTickets(props.project)}>{props.project.name}</button>
            <button type="button" className="btn btn-outline-warning"><FontAwesomeIcon icon={faEdit} /></button>
            <button type="button" className="btn btn-outline-danger"><FontAwesomeIcon icon={faTrash} /></button>
        </div>
}

export default ProjectListItem;