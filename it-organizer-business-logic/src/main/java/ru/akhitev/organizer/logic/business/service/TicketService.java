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
import ru.akhitev.organizer.db.entity.Ticket;
import ru.akhitev.organizer.logic.business.converter.TicketConverter;
import ru.akhitev.organizer.logic.business.vo.ticket.TicketForShow;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForEdit;
import ru.akhitev.organizer.db.repository.TicketRepository;

import java.util.Collections;
import java.util.Set;

/**
 * The aim of service is to provide give, remove and save DTOs and VOs.
 * A service uses converters, repositories and other services.
 * No one else should use data base layer.
 */
@Service
public class TicketService {
    /** The main repository. */
    @Autowired
    private TicketRepository repository;

    /** The main converter. */
    @Autowired
    private TicketConverter converter;

    /** Uses for getting {@link ProjectService#activeProject}. */
    @Autowired
    private ProjectService projectService;

    /** If there is an active ticket, then tasks and other lists is shown and saved with correct link to this ticket. */
    private Ticket activeTicket;

    /**
     * Returns collection of VOs.
     * If there is no {@link ProjectService#activeProject}, then empty set is returned.
     *
     * @return collection of VOs.
     */
    public Set<TicketForShow> giveTicketsForShowForActiveProject() {
        if (projectService.getActiveProject() == null) {
            return Collections.emptySet();
        }
        Set<Ticket> tickets = repository.findByProject(projectService.getActiveProject());
        return converter.prepareForShow(tickets);
    }

    /**
     * The method prepares DTO from entity, get by ID.
     *
     * @param ticketId ID to find entity in data base.
     * @return DTO from entity.
     */
    public TicketForEdit giveTicketForEdit(Integer ticketId) {
        return converter.prepareForEdit(repository.getOne(ticketId));
    }

    /**
     * The method saves DTO.
     * If it is a create operation and there is no entity, then a new one is created.
     * {@link ProjectService#activeProject} is set to project in the ticket.
     *
     * @param ticketForEdit DTO, which will be saved.
     */
    public void saveTicket(TicketForEdit ticketForEdit) {
        Integer id = ticketForEdit.getId();
        Ticket ticket = null;
        if (id != null) {
            ticket = repository.getOne(id);
        }
        ticket = converter.merge(ticket, ticketForEdit);
        ticket.setProject(projectService.getActiveProject());
        repository.save(ticket);
    }

    /**
     * The method removes found by id entity in data base.
     * @param ticketID ID to find entity in data base.
     */
    public void removeTicket(Integer ticketID) {
        repository.deleteById(ticketID);
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
}
