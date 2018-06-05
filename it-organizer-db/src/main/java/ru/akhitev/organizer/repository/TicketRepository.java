package ru.akhitev.organizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.organizer.entity.Project;
import ru.akhitev.organizer.entity.Ticket;

import java.util.Set;

public interface TicketRepository  extends JpaRepository<Ticket, Integer> {
    Set<Ticket> findByProject(Project project);
}
