package ru.akhitev.organizer.logic.business.converter;

import org.springframework.stereotype.Component;
import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForList;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TicketConverter {

    public Set<TicketForList> convertFromTicketsToTicketsForList(Collection<Ticket> tickets, Integer nameSize) {
        return tickets.stream()
                .map( ticket ->
                        new TicketForList(ticket.getId(),
                                ticket.getKey(),
                                "story",
                                ticket.getName(),
                                nameSize))
                .collect(Collectors.toSet());
    }
}
