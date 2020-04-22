import React from 'react';
import ProgressListItem from './ProgressListItem'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFileMedical } from '@fortawesome/free-solid-svg-icons'

const ProgressList = (props) => {
    let element_html = "";
    if (props.progresses) {
        element_html = props.progresses.map(
            function(progress) {
                let key = 'progressid' + progress.id;
                return <ProgressListItem key={key} progress={progress}
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
                          <ul className="list-group list-group-flush">
                              {element_html}
                          </ul>
                      }
        </div>
    );
}

export default ProgressList;