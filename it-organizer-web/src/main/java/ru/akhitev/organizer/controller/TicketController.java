package ru.akhitev.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.entity.TicketLink;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForEditor;
import ru.akhitev.organizer.logic.business.service.ProjectService;
import ru.akhitev.organizer.logic.business.service.TicketService;
import ru.akhitev.organizer.repository.TicketRepository;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController {
    @Autowired
    TicketService service;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTicket(Model model) {
        model.addAttribute("ticket", new TicketForEditor());
        return "edit_ticket";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveTicket(@ModelAttribute TicketForEditor ticket, BindingResult bindingResult, Model model) {
        service.saveTicket(ticket);
        return "redirect:/ticket/edit/" + ticket.getId();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editTicket(@PathVariable("id") Integer ticketId, Model model) {
        model.addAttribute("ticket", service.giveTicketForEdit(ticketId));
        service.activateTicket(ticketId);
        return "edit_ticket";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletTicket(@PathVariable("id") Integer ticketId, Model model) {
        service.removeTicket(ticketId);
        return "redirect:/";
    }
}
