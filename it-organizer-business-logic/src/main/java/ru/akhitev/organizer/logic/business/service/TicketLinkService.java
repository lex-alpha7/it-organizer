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
import ru.akhitev.organizer.entity.TicketLink;
import ru.akhitev.organizer.logic.business.converter.TicketLinkConverter;
import ru.akhitev.organizer.logic.business.dto.ticket.link.TicketLinkForEditor;
import ru.akhitev.organizer.repository.TicketLinkRepository;

@Service
public class TicketLinkService {
    @Autowired
    private TicketLinkRepository linkRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketLinkConverter converter;

    public Integer saveLink(TicketLinkForEditor linkForEditor) {
        Integer id = linkForEditor.getId();
        TicketLink link = null;
        if (id != null) {
            link = linkRepository.getOne(id);
        }
        link = converter.mergeLinkForListToLink(link, linkForEditor, ticketService.getActiveTicket());
        return linkRepository.save(link).getId();
    }

    public void removeLink(Integer linkId) {
        linkRepository.delete(linkId);
    }
}
