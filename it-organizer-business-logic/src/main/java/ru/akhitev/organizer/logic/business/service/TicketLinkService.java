package ru.akhitev.organizer.logic.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhitev.organizer.entity.TicketLink;
import ru.akhitev.organizer.logic.business.converter.TicketLinkConverter;
import ru.akhitev.organizer.logic.business.dto.ticket.link.TicketLinkForEditor;
import ru.akhitev.organizer.repository.TicketLinkRepository;

@Service
public class TicketLinkService {
    @Autowired
    private TicketLinkRepository linkRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketLinkConverter converter;

    public Integer saveLink(TicketLinkForEditor linkForEditor) {
        Integer id = linkForEditor.getId();
        TicketLink link = null;
        if (id != null) {
            link = linkRepository.getOne(id);
        }
        link = converter.mergeLinkForListToLink(link, linkForEditor, ticketService.getActiveTicket());
        return linkRepository.save(link).getId();
    }

    public void removeLink(Integer linkID) {
        linkRepository.deleteById(linkID);
    }
}
