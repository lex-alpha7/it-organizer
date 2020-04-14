package ru.akhitev.organizer.logic.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhitev.organizer.db.entity.Progress;
import ru.akhitev.organizer.db.entity.Ticket;
import ru.akhitev.organizer.db.repository.ProgressRepository;
import ru.akhitev.organizer.logic.business.converter.ProgressConverter;
import ru.akhitev.organizer.logic.business.dto.ticket.progress.ProgressForEdit;
import ru.akhitev.organizer.logic.business.vo.ticket.progress.ProgressForShow;

import java.util.Set;

@Service
public class ProgressService extends AbstractNodeService<Ticket, ProgressConverter, ProgressRepository, Progress, ProgressForShow, ProgressForEdit> {
    private final TicketService ticketService;

    @Autowired
    public ProgressService(ProgressRepository repository, ProgressConverter converter, TicketService ticketService) {
        super(converter, repository);
        this.ticketService = ticketService;
    }

    @Override
    Set<Progress> queryEntitiesForActiveRoot() {
        return repository.findByTicket(ticketService.getActiveTicket());
    }

    @Override
    Ticket activeRoot() {
        return ticketService.getActiveTicket();
    }
}
