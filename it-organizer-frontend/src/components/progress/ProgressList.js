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
          <button type="button" className="btn btn-outline-success"  data-toggle="modal" data-target="#progressModal">
            <FontAwesomeIcon icon={faFileMedical} />
          </button>
          { element_html &&
                          <ul className="list-group list-group-flush">
                              {element_html}
                          </ul>
                      }
          <div className="modal" id="progressModal">
            <div className="modal-dialog">
              <div className="modal-content">
                <div className="modal-header">
                  <h4 className="modal-title">Добавление прогресса</h4>
                  <button type="button" className="close" data-dismiss="modal">&times;</button>
                </div>
                <div className="modal-body">
                    <div className="form-group">
                        <label htmlFor="progressText">Прогресс:</label>
                        <textarea className="form-control" rows="5" id="progressText"></textarea>
                    </div>
                </div>
                <div className="modal-footer">
                  <button type="button" className="btn btn-success" data-dismiss="modal" onClick={() => props.saveProgress(document.getElementById("progressText").value)}>Сохранить</button>
                  <button type="button" className="btn btn-danger" data-dismiss="modal">Отмена</button>
                </div>
              </div>
            </div>
          </div>
        </div>
    );
}

export default ProgressList;