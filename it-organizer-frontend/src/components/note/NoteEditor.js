import React from 'react';
import axios from 'axios';
import RichEditor from '../RichEditor'

class NoteEditor extends React.Component {
    state = {
        id: undefined,
        title: undefined,
        noteNote: undefined
    }

    constructor(props) {
        super(props);
        this.state = {
            id: props.noteForEdit.id,
            title: props.noteForEdit.title,
            noteNote: props.noteForEdit.note
         }
    }

    componentDidUpdate(prevProps) {
        if (this.props.noteForEdit.id !== prevProps.noteForEdit.id ||
            this.props.noteForEdit.title !== prevProps.noteForEdit.title ||
            this.props.noteForEdit.noteNote !== prevProps.noteForEdit.noteNote) {
            this.setState({
                id: this.props.noteForEdit.id,
                title: this.props.noteForEdit.title,
                noteNote: this.props.noteForEdit.note
            });
        }
    }

    updateNote = (json) => {
        this.setState({noteNote: json})
    }

    save = async (e) => {
        e.preventDefault();
        const noteTitle = e.target.elements.noteTitle.value;
        const noteId = e.target.elements.noteId.value;
        const noteNote = this.state.noteNote;
        axios.put('http://localhost:8080/it-organizer/rest/note/save',
        {
            title: noteTitle,
            id: noteId,
            note: noteNote
        }).then((result) => {
            if (result.status === 200) {
                this.props.showSuccessAlert('Заметка успешно сохранена');
            } else {
                this.props.showErrorAlert('При сохранении заметки произошла ошибка');
            }
        });
    }

    onNoteTitleChange(value) {
        this.setState({
            title: value
        });
    }

    onNoteNoteChange(value) {
        this.setState({
            noteNote: value
        });
    }

    render() {
        return(
            <div className='container'>
                <div className='jumbotron'>
                    <form className="was-validated" onSubmit={this.save}>
                        <input type='hidden' name='noteId' id='noteId' value={this.state.id || ''} />
                        <div className="form-group">
                            <label>Note Title:</label>
                            <input id='noteTitle' name='noteTitle' type='text' className='form-control'
                                value={this.state.title || ''} required='required'
                                onChange={e => this.onNoteTitleChange(e.target.value)}/>
                            <div className="valid-feedback">Valid.</div>
                            <div className="invalid-feedback">Please fill out this field.</div>
                        </div>
                        <div className="form-group">
                            <label>Note:</label>
                            <RichEditor field={this.state.noteNote} updateWorkSpace={this.updateNote}/>
                            <div className="valid-feedback">Valid.</div>
                            <div className="invalid-feedback">Please fill out this field.</div>
                        </div>
                        <button type='submit' className='btn btn-primary'>Сохранить</button>
                    </form>
                </div>
            </div>);
    }
}

export default NoteEditor;