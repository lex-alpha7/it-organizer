package ru.akhitev.organizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.organizer.entity.ReferenceLink;

public interface ReferenceLinkRepository extends JpaRepository<ReferenceLink, Integer> {
}
