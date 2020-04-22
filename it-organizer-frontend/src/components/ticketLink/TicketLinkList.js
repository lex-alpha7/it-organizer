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
          <button type="button" className="btn btn-outline-success"  data-toggle="modal" data-target="#ticketLinkModal">
            <FontAwesomeIcon icon={faFileMedical} />
          </button>
          { element_html &&
                          <ul className="list-group list-group-flush">
                              {element_html}
                          </ul>
                      }
          <div className="modal" id="ticketLinkModal">
            <div className="modal-dialog">
              <div className="modal-content">
                <div className="modal-header">
                  <h4 className="modal-title">Добавление прогресса</h4>
                  <button type="button" className="close" data-dismiss="modal">&times;</button>
                </div>
                <div className="modal-body">
                    <div className="form-group">
                        <label htmlFor="ticketLinkName">Название ссылки:</label>
                        <input type="text" className="form-control" id="ticketLinkName" />
                    </div>
                    <div className="form-group">
                        <label htmlFor="ticketLinkLink">Ссылка:</label>
                        <input type="text" className="form-control" id="ticketLinkLink" />
                    </div>
                    <div className="form-group">
                        <label htmlFor="ticketLinkType">Тип:</label>
                        <select class="form-control" id="ticketLinkType">
                            <option>LINK_TO_TICKET</option>
                            <option>ATTACHMENT</option>
                            <option>USEFUL_LINK</option>
                        </select>
                    </div>
                </div>
                <div className="modal-footer">
                  <button type="button" className="btn btn-success" data-dismiss="modal" onClick={() => props.saveTicketLink(document.getElementById("ticketLinkName").value, document.getElementById("ticketLinkLink").value, document.getElementById("ticketLinkType").value)}>Сохранить</button>
                  <button type="button" className="btn btn-danger" data-dismiss="modal">Отмена</button>
                </div>
              </div>
            </div>
          </div>
        </div>
    );
}

export default TicketLinkList;