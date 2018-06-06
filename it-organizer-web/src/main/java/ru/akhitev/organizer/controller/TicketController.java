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
package ru.akhitev.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForEdit;
import ru.akhitev.organizer.logic.business.service.TicketService;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController extends AbstractController {
    @Autowired
    private TicketService service;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTicket(Model model) {
        model.addAttribute("ticket", new TicketForEdit());
        return EDIT_TICKET_PATH;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveTicket(@ModelAttribute TicketForEdit ticket, BindingResult bindingResult, Model model) {
        service.saveTicket(ticket);
        return String.format(EDIT_TICKET_WITH_ID_PATH_TEMPLATE, ticket.getId());
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editTicket(@PathVariable("id") Integer ticketId, Model model) {
        model.addAttribute("ticket", service.giveTicketForEdit(ticketId));
        service.activateTicket(ticketId);
        return EDIT_TICKET_PATH;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletTicket(@PathVariable("id") Integer ticketId, Model model) {
        service.removeTicket(ticketId);
        return MAIN_REDIRECT_PATH;
    }
}
