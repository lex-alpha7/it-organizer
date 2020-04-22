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

        openInNewTab = (link) => {
          const win = window.open(link, '_blank');
          win.focus();
        }

        render() {
            return <div className="btn-group dropdown-item">
                <button type="button" className="btn btn-outline-primary" onClick={() => this.openInNewTab(this.props.ticketLink.link)}>{this.props.ticketLink.name}</button>
                <button type="button" className="btn btn-outline-danger" onClick={() => this.deleteTicketLink(this.props.ticketLink.id)}><FontAwesomeIcon icon={faTrash} /></button>
            </div>;
        }
}

export default TicketLinkListItem;