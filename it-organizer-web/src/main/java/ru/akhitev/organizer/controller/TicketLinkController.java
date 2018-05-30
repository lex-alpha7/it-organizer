package ru.akhitev.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.akhitev.organizer.logic.business.dto.ticket.link.TicketLinkForEditor;
import ru.akhitev.organizer.logic.business.service.TicketLinkService;
import ru.akhitev.organizer.logic.business.service.TicketService;

@Controller
@RequestMapping(value = "/ticket/link")
public class TicketLinkController {
    @Autowired
    private TicketLinkService ticketLinkService;

    @Autowired
    private TicketService ticketService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLink(Model model) {
        model.addAttribute("ticketLink", new TicketLinkForEditor());
        return "edit_ticket_link";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveLink(@ModelAttribute TicketLinkForEditor link, BindingResult bindingResult, Model model) {
        ticketLinkService.saveLink(link);
        return "redirect:/ticket/edit/" + ticketService.getActiveTicket().getId();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteLinkt(@PathVariable("id") Integer linkId, Model model) {
        ticketLinkService.removeLink(linkId);
        return "redirect:/ticket/edit/" + ticketService.getActiveTicket().getId();
    }
}
