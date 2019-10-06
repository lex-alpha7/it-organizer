package ru.akhitev.organizer.web.controller.rest;

import org.springframework.web.bind.annotation.*;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForEdit;
import ru.akhitev.organizer.logic.business.service.TicketService;
import ru.akhitev.organizer.logic.business.vo.ticket.TicketForShow;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
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

    @GetMapping("/edit/{ticketId}")
    TicketForEdit edit(@PathVariable Integer ticketId) {
        service.activateTicket(ticketId);
        return service.giveForEdit(ticketId);
    }

    @PutMapping("/save")
    void save(@RequestBody TicketForEdit ticket) {
        service.save(ticket);
    }

    @DeleteMapping("/delete/{ticketId}")
    void delete(@PathVariable Integer ticketId) {
        service.remove(ticketId);
    }
}
