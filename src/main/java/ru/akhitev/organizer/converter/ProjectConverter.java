package ru.akhitev.organizer.converter;

import ru.akhitev.organizer.dto.Project;
import ru.akhitev.organizer.db.entity.ProjectEntity;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class ProjectConverter {
    Project entityToDTO(ProjectEntity entity) {
        return new Project(entity.getName());
    }

    public List<Project> entitiesToDTOs(List<ProjectEntity> entities) {
        if (entities == null || entities.size() == 0) {
            return Collections.emptyList();
        }
        List<Project> projects = new ArrayList<>();
        for (ProjectEntity entity : entities) {
            projects.add(entityToDTO(entity));
        }
        return projects;
    }

    ProjectEntity dtoToEntity(Project dto) {
        ProjectEntity entity = new ProjectEntity();
        entity.setName(dto.getName());
        return entity;
    }

    public List<ProjectEntity> dtosToEntities(List<Project> dtos) {
        if (dtos == null || dtos.size() == 0) {
            return Collections.emptyList();
        }
        List<ProjectEntity> entities = new ArrayList<>();
        for (Project dto : dtos) {
            entities.add(dtoToEntity(dto));
        }
        return entities;
    }
}
