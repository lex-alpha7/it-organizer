import React from 'react';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash } from '@fortawesome/free-solid-svg-icons'

class NoteListItem extends React.Component {
    deleteNote = async (noteId) => {
        let delete_note_url = `http://localhost:8080/it-organizer/rest/note/delete/${noteId}`;
        axios.delete(delete_note_url)
        .then((result) => {
            if (result.status === 200) {
                this.props.showSuccessAlert('Заметка успешно удалена');
            } else {
                this.props.showErrorAlert('При удалении заметки произошла ошибка');
            }
        });

    }

    render() {
        return <div className="btn-group dropdown-item">
                <button type="button" className="btn btn-outline-primary" onClick={() => this.props.editNote(this.props.note.id)}>{this.props.note.title}</button>
                <button type="button" className="btn btn-outline-danger" onClick={() => this.deleteNote(this.props.note.id)}><FontAwesomeIcon icon={faTrash} /></button>
            </div>
    }
}

export default NoteListItem;