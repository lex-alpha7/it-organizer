import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSave, faCaretRight } from '@fortawesome/free-solid-svg-icons'

const MenuHeader = (props) => {
     return <span className="navbar-text" style={{color: 'white'}}>
            { props.project && <span>{props.project.name} </span> }
            { props.ticket && <span>
                    <button type="button" className="btn btn-dark"><FontAwesomeIcon icon={faCaretRight} /></button>
                    <span>{props.ticket.displayedName} </span>
                </span> }
              </span>
}

export default MenuHeader;

//<button type="button" className="btn btn-outline-success"><FontAwesomeIcon icon={faSave} /></button>