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
package ru.akhitev.organizer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.akhitev.organizer.logic.business.dto.ticket.link.TicketLinkForEdit;
import ru.akhitev.organizer.logic.business.service.TicketLinkService;
import ru.akhitev.organizer.logic.business.service.TicketService;

@Controller
@RequestMapping(value = "/ticket/link")
public class TicketLinkController extends AbstractController {
    private final TicketLinkService ticketLinkService;

    private final TicketService ticketService;

    @Autowired
    public TicketLinkController(TicketLinkService ticketLinkService, TicketService ticketService) {
        this.ticketLinkService = ticketLinkService;
        this.ticketService = ticketService;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLink(Model model) {
        model.addAttribute("ticketLink", new TicketLinkForEdit());
        return EDIT_TICKET_LINK_PATH;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveLink(@ModelAttribute TicketLinkForEdit link, BindingResult bindingResult, Model model) {
        ticketLinkService.save(link);
        return String.format(EDIT_TICKET_WITH_ID_PATH_TEMPLATE, ticketService.getActiveTicket().getId());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteLinkt(@PathVariable("id") Integer linkId, Model model) {
        ticketLinkService.remove(linkId);
        return String.format(EDIT_TICKET_WITH_ID_PATH_TEMPLATE, ticketService.getActiveTicket().getId());
    }

}
