package ru.akhitev.organizer.logic.business.converter;

import org.springframework.stereotype.Component;
import ru.akhitev.organizer.entity.Project;
import ru.akhitev.organizer.logic.business.dto.project.ProjectForList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProjectConverter {
    public Set<ProjectForList> convertFromProjectsToProjectsForList(List<Project> projects, Integer nameSize) {
        return projects.stream().map((project) -> new ProjectForList(project.getId(), project.getName(), nameSize))
                .collect(Collectors.toSet());
    }
}
