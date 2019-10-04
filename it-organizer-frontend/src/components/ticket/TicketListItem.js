import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash } from '@fortawesome/free-solid-svg-icons'

const TicketListItem = (props) => {
    let displayedName = props.ticket.displayedName;
    return <div>{ displayedName &&
                <div className="btn-group dropdown-item">
                <button type="button" className="btn btn-outline-primary" onClick={() => props.editTicket(props.ticket)}>{displayedName}</button>
                <button type="button" className="btn btn-danger"><FontAwesomeIcon icon={faTrash} /></button>
            </div>
        }</div>
}

export default TicketListItem;