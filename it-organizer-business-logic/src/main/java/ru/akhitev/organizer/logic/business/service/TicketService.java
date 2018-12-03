/*
 * IT-Organizer is an organizer for a developer and other IT-specialists.
 * Copyright (c) 2017 Aleksei Khitev (Хитёв Алексей Юрьевич).
 *
 * This file is part of IT-Organizer
 *
 * IT-Organizer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * IT-Organizer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.akhitev.organizer.logic.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhitev.organizer.db.entity.Project;
import ru.akhitev.organizer.db.entity.Ticket;
import ru.akhitev.organizer.logic.business.converter.TicketConverter;
import ru.akhitev.organizer.logic.business.vo.ticket.TicketForShow;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForEdit;
import ru.akhitev.organizer.db.repository.TicketRepository;

import java.util.Collections;
import java.util.Set;

/** {@inheritDoc} */
@Service
public class TicketService extends AbstractNodeService<Project, TicketConverter, TicketRepository, Ticket, TicketForShow, TicketForEdit> {

    /** Uses for getting {@link ProjectService#activeProject}. */
    private final ProjectService projectService;

    /** If there is an active ticket, then tasks and other lists is shown and saved with correct link to this ticket. */
    private Ticket activeTicket;

    @Autowired
    public TicketService(TicketRepository repository, TicketConverter converter, ProjectService projectService) {
        super(converter, repository);
        this.projectService = projectService;
    }

    /**
     * The method returns an activated entity.
     * If there is an active ticket, then tasks and other lists is shown and saved with correct link to this ticket.
     *
     * @return activated entity.
     */
    public Ticket getActiveTicket() {
        return activeTicket;
    }

    /**
     * The method make a found by ID entity activated.
     * If there is an active ticket, then tasks and other lists is shown and saved with correct link to this ticket.
     *
     * @param activeTicketId ID to find entity in data base.
     */
    public void activateTicket(Integer activeTicketId) {
        this.activeTicket = repository.getOne(activeTicketId);
    }

    /**
     * The method return true if there is an activated ticket. Otherwise it returns false.
     * @return if there is an activated ticket.
     */
    public boolean ifActiveTicket() {
        return activeTicket != null;
    }

    /** {@inheritDoc} */
    @Override
    Set<Ticket> queryEntitiesForActiveRoot() {
        return repository.findByProject(projectService.getActiveProject());
    }

    /** {@inheritDoc} */
    @Override
    Project activeRoot() {
        return projectService.getActiveProject();
    }
}
