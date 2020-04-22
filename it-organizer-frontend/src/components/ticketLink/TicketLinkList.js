import React from 'react';
import TicketLinkListItem from './TicketLinkListItem'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFileMedical } from '@fortawesome/free-solid-svg-icons'

const TicketLinkList = (props) => {
    let element_html = "";
    if (props.ticketLinks) {
        element_html = props.ticketLinks.map(
            function(ticketLink) {
                let key = 'ticketLinkid' + ticketLink.id;
                return <TicketLinkListItem key={key} ticketLink={ticketLink}
                    saveTicket={props.saveTicket}
                    updateTicket={props.updateTicket}
                    ticket={props.ticket}
                    showSuccessAlert={props.showSuccessAlert}
                    showErrorAlert={props.showErrorAlert}/>;
            }
        );
    }
    return(
        <div>
          { element_html &&
                          <div className="btn-group">
                            <button type="button" className="btn btn-secondary dropdown-toggle"  data-toggle="dropdown">Links</button>
                            <div className="dropdown-menu">
                              {element_html}
                            </div>
                          </div>
                      }
        </div>
    );
}

export default TicketLinkList;