package ru.akhitev.organizer.logic.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.logic.business.converter.TicketConverter;
import ru.akhitev.organizer.logic.business.dto.project.ProjectForEditor;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForList;
import ru.akhitev.organizer.logic.business.dto.ticket.TicketForEditor;
import ru.akhitev.organizer.repository.TicketRepository;

import java.util.Collections;
import java.util.Set;

@Service
public class TicketService {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private TicketRepository repository;

    @Autowired
    private TicketConverter converter;

    public Set<TicketForList> giveTicketsForListByProject(Integer nameSize) {
        if (projectService.getActiveProject() == null) {
            return Collections.emptySet();
        }
        Set<Ticket> tickets = repository.findByProject(projectService.getActiveProject());
        return converter.convertFromTicketsToTicketsForList(tickets, nameSize);
    }

    public TicketForEditor giveTicketForEdit(Integer ticketId, Integer nameSize) {
        return converter.convertFromTicketToTicketForEditor(repository.getOne(ticketId), nameSize);
    }

    public Integer saveTicket(TicketForEditor ticketForEditor) {
        Integer id = ticketForEditor.getId();
        Ticket ticket = null;
        if (id != null) {
            ticket = repository.getOne(id);
        }
        ticket = converter.mergeProjectForListToProject(ticket, ticketForEditor, projectService.getActiveProject());
        return repository.save(ticket).getId();
    }

    public void removeTicket(Integer ticketId) {
        repository.delete(ticketId);
    }
}
