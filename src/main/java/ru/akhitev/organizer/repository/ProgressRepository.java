package ru.akhitev.organizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.organizer.entity.Progress;

public interface ProgressRepository extends JpaRepository<Progress, Integer> {
}
