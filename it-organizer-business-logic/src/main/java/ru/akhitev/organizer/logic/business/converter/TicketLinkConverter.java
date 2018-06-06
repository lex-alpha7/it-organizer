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
import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.entity.TicketLink;
import ru.akhitev.organizer.logic.business.dto.ticket.link.TicketLinkForEdit;
import ru.akhitev.organizer.logic.business.vo.ticket.link.TicketLinkForShow;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The aim of the class is to create VO, DTO or their lists from entity. And make entity from them.
 */
@Component
public class TicketLinkConverter {

    /**
     * This method converts notes into VOs to show in a sidebar.
     *
     * @param links could be null. it is safe.
     * @return emptyList if progresses are equal to null or a set of VOs
     */
    public Set<TicketLinkForShow> prepareLinksForShow(Collection<TicketLink> links) {
        if (links == null) {
            return Collections.emptySet();
        }
        return links.stream()
                .map( link ->
                        new TicketLinkForShow(link.getId(),
                                link.getType(),
                                link.getName(),
                                link.getLink()))
                .collect(Collectors.toSet());
    }

    /**
     * This method prepares an entity for saving.
     * If there is no entity (in case, it's a new one), a new note will be created and used. In another case an existed one will be used.
     *
     * @param link could be null. it is safe.
     * @param linkForEditor mustn't be null. It's data will be set to entity.
     * @param ticket will be used to link an entity to it in database.
     * @return full prepared entity will be returned. It'll be ready to store in a data base.
     */
    public TicketLink mergeLinkForEditToLink(TicketLink link, TicketLinkForEdit linkForEditor, Ticket ticket) {
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
