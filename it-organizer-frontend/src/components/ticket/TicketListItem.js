import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash, faEdit } from '@fortawesome/free-solid-svg-icons'

const TicketListItem = (props) => {
    return <div className="btn-group dropdown-item">
            <button type="button" className="btn btn-outline-primary">{props.ticket.displayedName}</button>
            <button type="button" className="btn btn-danger"><FontAwesomeIcon icon={faTrash} /></button>
        </div>
}

export default TicketListItem;