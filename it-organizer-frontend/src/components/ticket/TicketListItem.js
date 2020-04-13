import React from 'react';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash } from '@fortawesome/free-solid-svg-icons'

class TicketListItem extends React.Component {
    deleteProject = async (ticketId) => {
            let delete_ticket_url = `http://localhost:8080/it-organizer/rest/ticket/delete/${ticketId}`;
            axios.delete(delete_ticket_url)
            .then((result) => {
                if (result.status === 200) {
                    this.props.showSuccessAlert('Тикет успешно удален');
                } else {
                    this.props.showErrorAlert('При удалении тикета произошла ошибка');
                }
            });

        }

        render() {
            return <div className="btn-group dropdown-item">
                            <button type="button" className="btn btn-outline-primary" onClick={() => this.props.editTicket(this.props.ticket)}>{this.props.ticket.displayedName}</button>
                            <button type="button" className="btn btn-danger" onClick={() => this.deleteProject(this.props.ticket.id)}><FontAwesomeIcon icon={faTrash} /></button>thi
                        </div>
        }
}

export default TicketListItem;