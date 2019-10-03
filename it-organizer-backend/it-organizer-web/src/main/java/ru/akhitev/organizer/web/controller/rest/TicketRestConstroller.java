package ru.akhitev.organizer.web.controller.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.akhitev.organizer.logic.business.service.TicketService;
import ru.akhitev.organizer.logic.business.vo.ticket.TicketForShow;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/ticket")
public class TicketRestConstroller {
    private final TicketService service;

    public TicketRestConstroller(TicketService service) {
        this.service = service;
    }

    @GetMapping("/list")
    Set<TicketForShow> getList() {
        return service.giveForShowForActiveRoot();
    }
}
