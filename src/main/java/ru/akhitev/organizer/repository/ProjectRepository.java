package ru.akhitev.organizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.akhitev.organizer.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
