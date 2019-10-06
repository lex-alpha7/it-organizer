import React from 'react';
import NoteListItem from './NoteListItem'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFileMedical } from '@fortawesome/free-solid-svg-icons'

const NoteList = (props) => {
    let note_element_html = "";
    if (props.notes) {
        note_element_html = props.notes.map(
            function(note) {
                let noteKey = 'nid' + note.id;
                return <NoteListItem key={noteKey}
                    note={note}
                    editNote={props.editNote}
                    showSuccessAlert={props.showSuccessAlert}
                    showErrorAlert={props.showErrorAlert}/>;
            }
        );
    }

    return(
        <div className="btn-group">
          <button type="button" className="btn btn-primary dropdown-toggle" data-toggle="dropdown">
            Notes {props.notes && <span className="badge badge-dark">{props.notes.length}</span>}
          </button>
                { note_element_html &&
                    <div className="dropdown-menu">
                        {note_element_html}
                        <div className="btn-group dropdown-item">
                            <button type="button" className="btn btn-outline-success" onClick={() => props.editNote(undefined)}><FontAwesomeIcon icon={faFileMedical} /></button>
                        </div>
                    </div>
                }

        </div>
    );
}

export default NoteList;