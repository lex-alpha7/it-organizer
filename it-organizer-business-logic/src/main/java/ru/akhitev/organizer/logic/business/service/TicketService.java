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
import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.logic.business.converter.TicketConverter;
import ru.akhitev.organizer.logic.business.vo.ticket.TicketForList;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForEditor;
import ru.akhitev.organizer.repository.TicketRepository;

import java.util.Collections;
import java.util.Set;

@Service
public class TicketService {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private TicketRepository repository;

    @Autowired
    private TicketConverter converter;

    private Ticket activeTicket;

    public Set<TicketForList> giveTicketsForListByProject(Integer nameSize) {
        if (projectService.getActiveProject() == null) {
            return Collections.emptySet();
        }
        Set<Ticket> tickets = repository.findByProject(projectService.getActiveProject());
        return converter.prepareTicketsForList(tickets, nameSize);
    }

    public TicketForEditor giveTicketForEdit(Integer ticketId) {
        return converter.prepareTicketForEditor(repository.getOne(ticketId));
    }

    public Integer saveTicket(TicketForEditor ticketForEditor) {
        Integer id = ticketForEditor.getId();
        Ticket ticket = null;
        if (id != null) {
            ticket = repository.getOne(id);
        }
        ticket = converter.mergeProjectForListToProject(ticket, ticketForEditor, projectService.getActiveProject());
        return repository.save(ticket).getId();
    }

    public void removeTicket(Integer ticketID) {
        repository.deleteById(ticketID);
    }

    public Ticket getActiveTicket() {
        return activeTicket;
    }

    public void activateTicket(Integer activeTicketId) {
        this.activeTicket = repository.getOne(activeTicketId);
    }

    public boolean ifActiveTicket() {
        return activeTicket != null;
    }
}
