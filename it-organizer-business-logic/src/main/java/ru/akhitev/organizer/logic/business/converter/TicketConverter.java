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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.akhitev.organizer.db.entity.Ticket;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForEdit;
import ru.akhitev.organizer.logic.business.vo.ticket.TicketForShow;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/** {@inheritDoc} */
@Component
public class TicketConverter implements Converter<Ticket, TicketForShow, TicketForEdit> {

    /** size for adjustment too long names */
    @Value("${name.size}")
    private Integer nameSize;

    /** Uses to prepare ticket links during preparation for editor. */
    private final TicketLinkConverter linkConverter;

    @Autowired
    public TicketConverter(TicketLinkConverter linkConverter) {
        this.linkConverter = linkConverter;
    }

    /** {@inheritDoc} */
    @Override
    public Set<TicketForShow> prepareForShow(Collection<Ticket> tickets) {
        if (tickets == null) {
            return Collections.emptySet();
        }
        return tickets.stream()
                .map( ticket ->
                        new TicketForShow(ticket.getId(),
                                ticket.getKey(),
                                ticket.getPriority(),
                                ticket.getName(),
                                nameSize))
                .collect(Collectors.toSet());
    }

    /** {@inheritDoc} */
    @Override
    public TicketForEdit prepareForEdit(Ticket ticket) {
        return new TicketForEdit(ticket.getId(), ticket.getProject().getId(), ticket.getKey(), ticket.getPriority(),
                ticket.getName(), ticket.getWorkspace(), ticket.getStatus(), ticket.getStepsToReproduce(),
                linkConverter.prepareForShow(ticket.getLinks()));
    }

    /** {@inheritDoc} */
    @Override
    public Ticket merge(Ticket ticket, TicketForEdit ticketForEdit) {
        if (ticket == null) {
            ticket = new Ticket();
        }
        ticket.setName(ticketForEdit.getName());
        ticket.setKey(ticketForEdit.getKey());
        ticket.setWorkspace(ticketForEdit.getWorkspace());
        ticket.setStatus(ticketForEdit.getStatus());
        ticket.setPriority(ticketForEdit.getPriority());
        ticket.setStepsToReproduce(ticketForEdit.getStepsToReproduce());
        return ticket;
    }
}
