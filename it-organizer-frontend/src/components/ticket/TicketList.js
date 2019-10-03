import React from 'react';
import TicketListItem from './TicketListItem'

const TicketList = (props) => {
    let ticket_element_html = "";
    if (props.tickets) {
        ticket_element_html = props.tickets.map(
            function(ticket) {
                //let activation_url = `http://localhost:8080/it-organizer/project/activate/${project.id}`;
                return <TicketListItem ticket={ticket}/>;
            }
        );
    }
    return(
            <div className="btn-group">
              <button type="button" className="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                Tickets
              </button>
                    { ticket_element_html &&
                        <div className="dropdown-menu">
                            {ticket_element_html}
                        </div>
                    }
            </div>
        );
}

export default TicketList;