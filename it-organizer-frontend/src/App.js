import React from 'react';
import ProjectList from './components/project/ProjectList';
import TicketList from './components/ticket/TicketList';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import './css/main.css'

class App extends React.Component {
    state = {
        projects: undefined,
        tickets: undefined
    }

    constructor(props) {
        super(props);
        this.getProjectList();
    }

    getProjectList = async () => {
        //e.preventDefault();
        const project_list_rest = await fetch('http://localhost:8080/it-organizer/rest/project/list');
        const project_list = await project_list_rest.json();
        console.log(project_list);
        this.setState({projects: project_list});
        this.setState({tickets: project_list});
    }

    activateProjectAndGetTickets = async () => {
        const project_acivate_rest = await fetch('http://localhost:8080/it-organizer/rest/project/activate/272');
        this.getTicketList();
    }

    getTicketList = async () => {
        const ticket_list_rest = await fetch('http://localhost:8080/it-organizer/rest/ticket/list');
        const ticket_list = await ticket_list_rest.json();
        console.log(ticket_list);
        this.setState({tickets: ticket_list});
    }

    render() {
        return(
            <nav className="navbar navbar-expand-sm  bg-dark">
                <ul className="navbar-nav">
                    <li className='nav-item'>
                        <ProjectList projects={this.state.projects} activateAndGetTickets={this.activateProjectAndGetTickets} />
                    </li>
                    <li className='nav-item'><TicketList tickets={this.state.tickets} /></li>
                </ul>
                <span className='fas fa-edit'></span>
            </nav>

        );
    }
}

export default App;