import React from 'react';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash } from '@fortawesome/free-solid-svg-icons'

class ProgressListItem extends React.Component {
    deleteProgress = async (progressId) => {
            let delete_progressId_url = `http://localhost:8080/it-organizer/rest/progress/delete/${progressId}`;
            axios.delete(delete_progressId_url)
            .then((result) => {
                if (result.status === 200) {
                    this.props.showSuccessAlert('Прогресс успешно удален');
                } else {
                    this.props.showErrorAlert('При удалении прогресса произошла ошибка');
                }
            });

        }
        render() {
            return <li className="list-group-item">
                {this.props.progress.progress}<br/>
                <span className="small">{this.props.progress.date}</span><button type="button" className="btn btn-outline-danger" onClick={() => this.deleteProgress(this.props.progress.id)}><FontAwesomeIcon icon={faTrash} /></button>
            </li>;
        }
}

export default ProgressListItem;