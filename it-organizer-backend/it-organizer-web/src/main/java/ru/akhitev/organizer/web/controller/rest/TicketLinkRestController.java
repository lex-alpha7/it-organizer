package ru.akhitev.organizer.web.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.akhitev.organizer.logic.business.dto.ticket.link.TicketLinkForEdit;
import ru.akhitev.organizer.logic.business.service.TicketLinkService;
import ru.akhitev.organizer.logic.business.vo.ticket.link.TicketLinkForShow;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/rest/ticket_link")
public class TicketLinkRestController {
    private TicketLinkService service;

    @Autowired
    public TicketLinkRestController(TicketLinkService service) {
        this.service = service;
    }

    @GetMapping("/list")
    Set<TicketLinkForShow> getList() {
        return service.giveForShowForActiveRoot();
    }

    @GetMapping("/edit/{ticketLinkId}")
    TicketLinkForEdit edit(@PathVariable Integer ticketLinkId) {
        return service.giveForEdit(ticketLinkId);
    }

    @PutMapping("/save")
    void save(@RequestBody TicketLinkForEdit ticketLink) {
        service.save(ticketLink);
    }

    @DeleteMapping("/delete/{ticketLinkId}")
    void delete(@PathVariable Integer ticketLinkId) {
        service.remove(ticketLinkId);
    }
}
