import React from 'react';
import TicketLinkListItem from './TicketLinkListItem'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFileMedical } from '@fortawesome/free-solid-svg-icons'

const TicketLinkList = (props) => {
    let linksToTickets = "";
    let useFullLinks = "";
    let attachments = "";
    if (props.ticketLinks) {
        linksToTickets = props.ticketLinks.map(
            function(ticketLink) {
                if (ticketLink.type == 'LINK_TO_TICKET') {
                    let key = 'ticketLinkid' + ticketLink.id;
                    return <TicketLinkListItem key={key} ticketLink={ticketLink}
                        saveTicket={props.saveTicket}
                        updateTicket={props.updateTicket}
                        ticket={props.ticket}
                        showSuccessAlert={props.showSuccessAlert}
                        showErrorAlert={props.showErrorAlert}/>;
                } else {
                    return "";
                }
            }
        );
        useFullLinks = props.ticketLinks.map(
            function(ticketLink) {
                if (ticketLink.type == 'USEFUL_LINK') {
                    let key = 'ticketLinkid' + ticketLink.id;
                    return <TicketLinkListItem key={key} ticketLink={ticketLink}
                        saveTicket={props.saveTicket}
                        updateTicket={props.updateTicket}
                        ticket={props.ticket}
                        showSuccessAlert={props.showSuccessAlert}
                        showErrorAlert={props.showErrorAlert}/>;
                } else {
                    return "";
                }
            }
        );
        attachments = props.ticketLinks.map(
            function(ticketLink) {
                if (ticketLink.type == 'ATTACHMENT') {
                    let key = 'ticketLinkid' + ticketLink.id;
                    return <TicketLinkListItem key={key} ticketLink={ticketLink}
                        saveTicket={props.saveTicket}
                        updateTicket={props.updateTicket}
                        ticket={props.ticket}
                        showSuccessAlert={props.showSuccessAlert}
                        showErrorAlert={props.showErrorAlert}/>;
                } else {
                    return "";
                }
            }
        );
    }
    return(
        <div>
          { linksToTickets &&
              <div className="btn-group">
                <button type="button" className="btn btn-outline-primary dropdown-toggle"  data-toggle="dropdown">Links to Tickets</button>
                <div className="dropdown-menu">
                  {linksToTickets}
                </div>
              </div>
          }
          { useFullLinks &&
              <div className="btn-group">
                <button type="button" className="btn btn-outline-primary dropdown-toggle"  data-toggle="dropdown">Usefull Links</button>
                <div className="dropdown-menu">
                  {useFullLinks}
                </div>
              </div>
          }
          { attachments &&
              <div className="btn-group">
                <button type="button" className="btn btn-outline-primary dropdown-toggle"  data-toggle="dropdown">Attachments</button>
                <div className="dropdown-menu">
                  {attachments}
                </div>
              </div>
          }
        </div>
    );
}

export default TicketLinkList;