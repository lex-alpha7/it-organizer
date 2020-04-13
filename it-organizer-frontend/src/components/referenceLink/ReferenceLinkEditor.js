import React from 'react';
import axios from 'axios';

class ReferenceLinkEditor extends React.Component {
    state = {
        id: undefined,
        name: undefined,
        link: undefined
    }

    constructor(props) {
        super(props);
        this.state = {
            id: this.props.referenceLink.id,
            name: this.props.referenceLink.name,
            link: this.props.referenceLink.link
         }
    }

    componentDidUpdate(prevProps) {
        if (this.props.referenceLink.id !== prevProps.referenceLink.id ||
            this.props.referenceLink.name !== prevProps.referenceLink.name ||
            this.props.referenceLink.link !== prevProps.referenceLink.link) {
            this.setState({
                id: this.props.referenceLink.id,
                name: this.props.referenceLink.name,
                link: this.props.referenceLink.link
            });
        }
    }

    save = async (e) => {
        e.preventDefault();
        const name = e.target.elements.refLinkName.value;
        const id = e.target.elements.refLinkId.value;
        const link = e.target.elements.refLinkLink.value;
        axios.put('http://localhost:8080/it-organizer/rest/reference_link/save',
        {
            name: name,
            id: id,
            link: link
        }).then((result) => {
            if (result.status === 200) {
                this.props.showSuccessAlert('Полезная ссылка успешно сохранена');
            } else {
                this.props.showErrorAlert('При сохранении полезной ссылке произошла ошибка');
            }
        });
    }

    onRefLinkNameChange(value) {
        this.setState({
            name: value
        });
    }

    onRefLinkLinkChange(value) {
        this.setState({
            link: value
        });
    }

    render() {
        return(
            <div className='container'>
                <div className='jumbotron'>
                    <form className="was-validated" onSubmit={this.save}>
                        <input type='hidden' name='refLinkId' id='refLinkId' value={this.state.id || ''} />
                        <div className="form-group">
                            <label>Note Title:</label>
                            <input id='refLinkName' name='refLinkName' type='text' className='form-control'
                                value={this.state.name || ''} required='required'
                                onChange={e => this.onRefLinkNameChange(e.target.value)}/>
                            <div className="valid-feedback">Valid.</div>
                            <div className="invalid-feedback">Please fill out this field.</div>
                        </div>
                        <div className="form-group">
                            <label>Note:</label>
                            <input id='refLinkLink' name='refLinkLink' type='text' className='form-control'
                                value={this.state.link || ''} required='required'
                                onChange={e => this.onRefLinkLinkChange(e.target.value)}/>
                            <div className="valid-feedback">Valid.</div>
                            <div className="invalid-feedback">Please fill out this field.</div>
                        </div>
                        <button type='submit' className='btn btn-primary'>Сохранить</button>
                    </form>
                </div>
            </div>);
    }
}

export default ReferenceLinkEditor;