package ru.akhitev.organizer.logic.business.converter;

import org.springframework.stereotype.Component;
import ru.akhitev.organizer.entity.Project;
import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.entity.TicketLink;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForEditor;
import ru.akhitev.organizer.logic.business.dto.ticket.link.TicketLinkForEditor;
import ru.akhitev.organizer.logic.business.dto.ticket.link.TicketLinkForList;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TicketLinkConverter {

    public Set<TicketLinkForList> convertFromLinksToLinksForList(Collection<TicketLink> links) {
        if (links == null) {
            return Collections.emptySet();
        }
        return links.stream()
                .map( link ->
                        new TicketLinkForList(link.getId(),
                                link.getType(),
                                link.getName(),
                                link.getLink()))
                .collect(Collectors.toSet());
    }

    public TicketLink mergeLinkForListToLink(TicketLink link, TicketLinkForEditor linkForEditor, Ticket ticket) {
        if (link == null) {
            link = new TicketLink();
        }
        link.setName(linkForEditor.getName());
        link.setTicket(ticket);
        link.setType(linkForEditor.getType());
        link.setLink(linkForEditor.getLink());
        return link;
    }
}
