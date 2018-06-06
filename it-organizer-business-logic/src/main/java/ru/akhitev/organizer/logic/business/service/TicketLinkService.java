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
import ru.akhitev.organizer.logic.business.dto.ticket.link.TicketLinkForEdit;
import ru.akhitev.organizer.repository.TicketLinkRepository;

/**
 * The aim of service is to provide give, remove and save DTOs and VOs.
 * A service uses converters, repositories and other services.
 * No one else should use data base layer.
 */
@Service
public class TicketLinkService {
    /** The main repository. */
    @Autowired
    private TicketLinkRepository linkRepository;

    /** The main converter. */
    @Autowired
    private TicketLinkConverter converter;

    /** Uses for getting {@link TicketService#activeTicket}. */
    @Autowired
    private TicketService ticketService;

    /**
     * The method saves DTO.
     * If it is a create operation and there is no entity, then a new one is created.
     * {@link TicketService#activeTicket} is set to project in the ticket.
     *
     * @param linkForEditor DTO, which will be saved.
     */
    public void saveLink(TicketLinkForEdit linkForEditor) {
        Integer id = linkForEditor.getId();
        TicketLink link = null;
        if (id != null) {
            link = linkRepository.getOne(id);
        }
        link = converter.mergeLinkForEditToLink(link, linkForEditor, ticketService.getActiveTicket());
        linkRepository.save(link);
    }

    /**
     * The method removes found by id entity in data base.
     * @param linkID ID to find entity in data base.
     */
    public void removeLink(Integer linkID) {
        linkRepository.deleteById(linkID);
    }
}
