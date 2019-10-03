import React from 'react';
import './expand-collapse.css'
import './main.css'

const ProjectList = (props) => {
    let project_element_html = "";
    if (props.projects) {
        project_element_html = props.projects.map(
            function(project) {
                let activation_url = `http://localhost:8080/it-organizer/project/activate/${project.id}`;
                return <tr><td className='nav_list_element'><button onClick={props.activateAndGetTickets}>{project.name}</button></td></tr>;
            }
        );
    }

    return(
        <article className='navbar-left-menu'>
            <h2 className="sidebar-title">P<span className="nav-label">rojects</span></h2>
                { project_element_html &&
                    <table>
                        <tbody>
                            {project_element_html}
                        </tbody>
                    </table>
                }
            <button type="button" name="addProject"></button>
        </article>
    );
}

export default ProjectList;