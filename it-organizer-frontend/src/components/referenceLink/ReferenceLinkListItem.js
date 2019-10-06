import React from 'react';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash, faEdit } from '@fortawesome/free-solid-svg-icons'

class ReferenceLinkListItem extends React.Component {
    deleteReferenceLink = async (id) => {
        let url = `http://localhost:8080/it-organizer/rest/reference_link/delete/${id}`;
        axios.delete(url)
        .then((result) => {
            if (result.status === 200) {
                this.props.showSuccessAlert('Полезная ссылка успешно удалена');
            } else {
                this.props.showErrorAlert('При удалении полезной ссылки произошла ошибка');
            }
        });

    }

    openInNewTab = (link) => {
      const win = window.open(link, '_blank');
      win.focus();
    }

    render() {
        return <div className="btn-group dropdown-item">
            <button type="button" className="btn btn-outline-primary" onClick={() => this.openInNewTab(this.props.referenceLink.link)}>{this.props.referenceLink.name}</button>
            <button type="button" className="btn btn-outline-warning" onClick={() => this.props.editReferenceLink(this.props.referenceLink.id)}><FontAwesomeIcon icon={faEdit} /></button>
            <button type="button" className="btn btn-outline-danger" onClick={() => this.deleteReferenceLink(this.props.referenceLink.id)}><FontAwesomeIcon icon={faTrash} /></button>
        </div>
    }
}

export default ReferenceLinkListItem;