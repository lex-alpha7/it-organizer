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
package ru.akhitev.organizer.logic.business.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akhitev.organizer.entity.Project;
import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForEditor;
import ru.akhitev.organizer.logic.business.vo.ticket.TicketForList;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The aim of the class is to create VO, DTO or their lists from entity. And make entity from them.
 */
@Component
public class TicketConverter {
    /** Uses to prepare ticket links during preparation for editor. */
    @Autowired
    private TicketLinkConverter linkConverter;

    /**
     * This method converts notes into VOs to show in a sidebar.
     *
     * @param tickets could be null. it is safe.
     * @param nameSize a note's name will be adjusted by this size.
     * @return emptyList if progresses are equal to null or a set of VOs
     */
    public Set<TicketForList> prepareTicketsForList(Collection<Ticket> tickets, Integer nameSize) {
        if (tickets == null) {
            return Collections.emptySet();
        }
        return tickets.stream()
                .map( ticket ->
                        new TicketForList(ticket.getId(),
                                ticket.getKey(),
                                ticket.getPriority(),
                                ticket.getName(),
                                nameSize))
                .collect(Collectors.toSet());
    }

    /**
     * The method prepares object for editor.
     * Data from entity is set into DTO.
     *
     * @param ticket entity, which is a source for DTO.
     * @return a DTO, filled with data from an entity.
     */
    public TicketForEditor prepareTicketForEditor(Ticket ticket) {
        return new TicketForEditor(ticket.getId(), ticket.getProject().getId(), ticket.getKey(), ticket.getPriority(),
                ticket.getName(), ticket.getWorkspace(), ticket.getStatus(), ticket.getStepsToReproduce(),
                linkConverter.prepareLinksForList(ticket.getLinks()));
    }

    /**
     * This method prepares an entity for saving.
     * If there is no entity (in case, it's a new one), a new note will be created and used. In another case an existed one will be used.
     *
     * @param ticket could be null. it is safe.
     * @param ticketForEditor mustn't be null. It's data will be set to entity.
     * @param activeProject will be used to link an entity to it in database.
     * @return full prepared entity will be returned. It'll be ready to store in a data base.
     */
    public Ticket mergeProjectForListToProject(Ticket ticket, TicketForEditor ticketForEditor, Project activeProject) {
        if (ticket == null) {
            ticket = new Ticket();
        }
        ticket.setName(ticketForEditor.getName());
        ticket.setProject(activeProject);
        ticket.setKey(ticketForEditor.getKey());
        ticket.setWorkspace(ticketForEditor.getWorkspace());
        ticket.setStatus(ticketForEditor.getStatus());
        ticket.setPriority(ticketForEditor.getPriority());
        ticket.setStepsToReproduce(ticketForEditor.getStepsToReproduce());
        return ticket;
    }
}
