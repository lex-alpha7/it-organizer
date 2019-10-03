import React from 'react';
import ProjectListItem from './ProjectListItem'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash, faEdit, faFileMedical } from '@fortawesome/free-solid-svg-icons'

const ProjectList = (props) => {
    let project_element_html = "";
    if (props.projects) {
        project_element_html = props.projects.map(
            function(project) {
                return <ProjectListItem activateAndGetTickets={props.activateAndGetTickets} project={project} />;
            }
        );
    }

    return(
        <div className="btn-group">
          <button type="button" className="btn btn-primary dropdown-toggle" data-toggle="dropdown">
            Projects
          </button>
                { project_element_html &&
                    <div className="dropdown-menu">
                        {project_element_html}
                        <div className="btn-group dropdown-item">
                            <button type="button" className="btn btn-outline-success"><FontAwesomeIcon icon={faFileMedical} /></button>
                        </div>
                    </div>
                }
        </div>
    );
}

export default ProjectList;