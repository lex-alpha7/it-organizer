package ru.akhitev.organizer.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.organizer.converter.ProjectConverter;
import ru.akhitev.organizer.db.entity.ProjectEntity;

import javax.inject.Inject;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
}
