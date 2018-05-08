package ru.akhitev.organizer.logic.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.akhitev.organizer.logic.business.converter.TicketConverter;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForList;
import ru.akhitev.organizer.repository.TicketRepository;

import java.util.Set;

public class TicketService {
    @Autowired
    private TicketRepository repository;

    @Autowired
    private TicketConverter converter;

    public Set<TicketForList> giveTicketsForList(Integer nameSize) {
        return converter.convertFromTicketsToTicketsForList(repository.findAll(), nameSize);
    }
}
