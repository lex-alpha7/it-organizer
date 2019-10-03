import React from 'react';
import './expand-collapse.css'
import './main.css'

const TicketList = (props) => {
    let ticket_element_html = "";
    if (props.tickets) {
        ticket_element_html = props.tickets.map(
            function(ticket) {
                //let activation_url = `http://localhost:8080/it-organizer/project/activate/${project.id}`;
                return <tr><td className='nav_list_element'>{ticket.displayedName}</td></tr>;
            }
        );
    }
    return(
            <article className="navbar-left-menu">
                <h2 className="sidebar-title">T<span className="nav-label">ickets</span></h2>
                <span className="nav-label">
                        {ticket_element_html &&
                            <table>
                                <tbody>
                                    {ticket_element_html}
                                </tbody>
                            </table>
                        }


                    <button type="button" name="addProject"></button>
                </span>
            </article>
        );
}

export default TicketList;