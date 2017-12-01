package ru.akhitev.organizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.organizer.entity.TicketLink;

public interface TicketLinkRepository extends JpaRepository<TicketLink, Integer> {
}
