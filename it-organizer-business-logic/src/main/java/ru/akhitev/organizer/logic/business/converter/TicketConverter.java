package ru.akhitev.organizer.logic.business.converter;

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

    public Set<TicketForList> convertFromTicketsToTicketsForList(Collection<Ticket> tickets, Integer nameSize) {
        if (tickets == null) {
            return Collections.emptySet();
        }
        return tickets.stream()
                .map( ticket ->
                        new TicketForList(ticket.getId(),
                                ticket.getKey(),
                                "story",
                                ticket.getName(),
                                nameSize))
                .collect(Collectors.toSet());
    }

    public TicketForEditor convertFromTicketToTicketForEditor(Ticket ticket, Integer nameSize) {
        return new TicketForEditor(ticket.getId(), ticket.getProject().getId(), ticket.getKey(), ticket.getPriority(),
                ticket.getName(), ticket.getWorkspace());
    }

    public Ticket mergeProjectForListToProject(Ticket ticket, TicketForEditor ticketForEditor, Project activeProject) {
        if (ticket == null) {
            ticket = new Ticket();
        }
        ticket.setName(ticketForEditor.getName());
        ticket.setProject(activeProject);
        ticket.setKey(ticketForEditor.getKey());
        ticket.setWorkspace(ticketForEditor.getWorkspace());
        return ticket;
    }
}
