import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSave, faCaretRight } from '@fortawesome/free-solid-svg-icons'

const MenuHeader = (props) => {
     return <span class="navbar-text" style={{color: 'white'}}>
            { props.project && <span>{props.project.name} </span> }
            { props.ticket && <span>
                    <button type="button" className="btn btn-dark"><FontAwesomeIcon icon={faCaretRight} /></button>
                    <span>{props.ticket.displayedName} </span>
                    <button type="button" className="btn btn-outline-success"><FontAwesomeIcon icon={faSave} /></button>
                </span> }
              </span>
}

export default MenuHeader;