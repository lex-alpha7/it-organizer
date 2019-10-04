import React from 'react';
import ProjectListItem from './ProjectListItem'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFileMedical } from '@fortawesome/free-solid-svg-icons'

const ProjectList = (props) => {
    let project_element_html = "";
    if (props.projects) {
        project_element_html = props.projects.map(
            function(project) {
                return <ProjectListItem
                    activateAndGetTickets={props.activateAndGetTickets}
                    project={project}
                    editProject={props.editProject}
                    showSuccessAlert={props.showSuccessAlert}
                    showErrorAlert={props.showErrorAlert}/>;
            }
        );
    }

    return(
        <div className="btn-group">
          <button type="button" className="btn btn-primary dropdown-toggle" data-toggle="dropdown">
            Projects {props.projects && <span class="badge badge-dark">{props.projects.length}</span>}
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