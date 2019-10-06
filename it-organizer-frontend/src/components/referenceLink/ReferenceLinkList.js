import React from 'react';
import ReferenceLinkListItem from './ReferenceLinkListItem'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFileMedical } from '@fortawesome/free-solid-svg-icons'

const ReferenceLinkList = (props) => {
    let element_html = "";
    if (props.referenceLinks) {
        element_html = props.referenceLinks.map(
            function(referenceLink) {
                let key = 'rlid' + referenceLink.id;
                return <ReferenceLinkListItem key={key}
                    referenceLink={referenceLink}
                    editReferenceLink={props.editReferenceLink}
                    showSuccessAlert={props.showSuccessAlert}
                    showErrorAlert={props.showErrorAlert}/>;
            }
        );
    }
    return(
        <div className="btn-group">
          <button type="button" className="btn btn-primary dropdown-toggle" data-toggle="dropdown">
            Полезные ссылки {props.referenceLinks && <span className="badge badge-dark">{props.referenceLinks.length}</span>}
          </button>
                { element_html &&
                    <div className="dropdown-menu">
                        {element_html}
                        <div className="btn-group dropdown-item">
                            <button type="button" className="btn btn-outline-success" onClick={() => props.editReferenceLink(undefined)}><FontAwesomeIcon icon={faFileMedical} /></button>
                        </div>
                    </div>
                }

        </div>
    );
}

export default ReferenceLinkList;