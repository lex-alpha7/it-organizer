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

import org.springframework.stereotype.Component;
import ru.akhitev.organizer.entity.Project;
import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.entity.TicketLink;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForEditor;
import ru.akhitev.organizer.logic.business.dto.ticket.link.TicketLinkForEditor;
import ru.akhitev.organizer.logic.business.dto.ticket.link.TicketLinkForList;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TicketLinkConverter {

    public Set<TicketLinkForList> convertFromLinksToLinksForList(Collection<TicketLink> links) {
        if (links == null) {
            return Collections.emptySet();
        }
        return links.stream()
                .map( link ->
                        new TicketLinkForList(link.getId(),
                                link.getType(),
                                link.getName(),
                                link.getLink()))
                .collect(Collectors.toSet());
    }

    public TicketLink mergeLinkForListToLink(TicketLink link, TicketLinkForEditor linkForEditor, Ticket ticket) {
        if (link == null) {
            link = new TicketLink();
        }
        link.setName(linkForEditor.getName());
        link.setTicket(ticket);
        link.setType(linkForEditor.getType());
        link.setLink(linkForEditor.getLink());
        return link;
    }
}
