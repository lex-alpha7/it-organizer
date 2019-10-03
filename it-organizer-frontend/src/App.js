import React from 'react';
import ProjectList from './components/ProjectList';
import TicketList from './components/TicketList';
import 'bootstrap/dist/css/bootstrap.min.css'
import './components/main.css'

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
//        e.preventDefault();
        const project_list_rest = await fetch('http://localhost:8080/it-organizer/rest/project/list');
        const project_list = await project_list_rest.json();
        console.log(project_list);
        this.setState({projects: project_list});
        this.setState({tickets: project_list});
    }

    activateProjectAndGetTickets = async () => {
        const project_acivate_rest = await fetch('http://localhost:8080/it-organizer/rest/project/acivate/272');
        const project_acivate_reply = await project_acivate_rest.json();
        console.log(project_acivate_reply);
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
            <nav className="navbar-left">
                <ProjectList projects={this.state.projects} activateAndGetTickets={this.activateProjectAndGetTickets} />
                <TicketList tickets={this.state.tickets} />
            </nav>
        );
    }
}

export default App;