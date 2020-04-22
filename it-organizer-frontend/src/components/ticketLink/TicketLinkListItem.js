import React from 'react';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash } from '@fortawesome/free-solid-svg-icons'

class TicketLinkListItem extends React.Component {
    deleteTicketLink = async (ticketLinkId) => {
            let delete_ticketLinkId_url = `http://localhost:8080/it-organizer/rest/ticket_link/delete/${ticketLinkId}`;
            axios.delete(delete_ticketLinkId_url)
            .then((result) => {
                if (result.status === 200) {
                    this.props.showSuccessAlert('Прогресс успешно удален');
                    this.props.saveTicket();
                    this.props.updateTicket(this.props.ticket);
                } else {
                    this.props.showErrorAlert('При удалении прогресса произошла ошибка');
                }
            });

        }
        render() {
            return <li className="list-group-item">
                {this.props.ticketLink.link}<br/>
                <button type="button" className="btn btn-outline-danger btn-sm" onClick={() => this.deleteTicketLink(this.props.ticketLink.id)}><FontAwesomeIcon icon={faTrash} /></button>
            </li>;
        }
}

export default TicketLinkListItem;