package ru.akhitev.organizer.logic.business.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akhitev.organizer.entity.Project;
import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForEditor;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForList;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TicketConverter {
    @Autowired
    TicketLinkConverter linkConverter;

    public Set<TicketForList> convertFromTicketsToTicketsForList(Collection<Ticket> tickets, Integer nameSize) {
        if (tickets == null) {
            return Collections.emptySet();
        }
        return tickets.stream()
                .map( ticket ->
                        new TicketForList(ticket.getId(),
                                ticket.getKey(),
                                ticket.getPriority(),
                                ticket.getName(),
                                nameSize))
                .collect(Collectors.toSet());
    }

    public TicketForEditor convertFromTicketToTicketForEditor(Ticket ticket) {
        return new TicketForEditor(ticket.getId(), ticket.getProject().getId(), ticket.getKey(), ticket.getPriority(),
                ticket.getName(), ticket.getWorkspace(), ticket.getStatus(), ticket.getStepsToReproduce(),
                linkConverter.convertFromLinksToLinksForList(ticket.getLinks()));
    }

    public Ticket mergeProjectForListToProject(Ticket ticket, TicketForEditor ticketForEditor, Project activeProject) {
        if (ticket == null) {
            ticket = new Ticket();
        }
        ticket.setName(ticketForEditor.getName());
        ticket.setProject(activeProject);
        ticket.setKey(ticketForEditor.getKey());
        ticket.setWorkspace(ticketForEditor.getWorkspace());
        ticket.setStatus(ticketForEditor.getStatus());
        ticket.setPriority(ticketForEditor.getPriority());
        ticket.setStepsToReproduce(ticketForEditor.getStepsToReproduce());
        return ticket;
    }
}
