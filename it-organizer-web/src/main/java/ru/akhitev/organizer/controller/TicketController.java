package ru.akhitev.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.repository.TicketRepository;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController {
    @Autowired
    TicketRepository repository;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTicket(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "fragments/ticket :: new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveTicket(@ModelAttribute Ticket ticket, BindingResult bindingResult, Model model) {
        repository.save(ticket);
        return "redirect:/";
    }
}
