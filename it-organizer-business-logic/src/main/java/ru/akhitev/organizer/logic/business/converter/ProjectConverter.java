package ru.akhitev.organizer.logic.business.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akhitev.organizer.entity.Project;
import ru.akhitev.organizer.logic.business.dto.project.ProjectForEditor;
import ru.akhitev.organizer.logic.business.dto.project.ProjectForList;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProjectConverter {
    @Autowired
    private TicketConverter ticketConverter;

    public Set<ProjectForList> convertFromProjectsToProjectsForList(List<Project> projects, Integer nameSize) {
        return projects.stream().map((project) -> new ProjectForList(project.getId(), project.getName(), nameSize))
                .collect(Collectors.toSet());
    }

    public ProjectForEditor convertFromProjectToProjectForEditor(Project project, Integer nameSize) {
        return new ProjectForEditor(project.getId(), project.getName(),
                ticketConverter.convertFromTicketsToTicketsForList(project.getTickets(), nameSize));
    }

    public Project mergeProjectForListToProject(Project project, ProjectForEditor projectForEditor) {
        if (project == null) {
            project = new Project();
        }
        project.setName(projectForEditor.getName());
        return project;
    }
}
