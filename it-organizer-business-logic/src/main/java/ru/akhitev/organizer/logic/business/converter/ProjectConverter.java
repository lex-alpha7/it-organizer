/*
 * IT-Organizer is an organizer for a developer and other IT-specialists.
 * Copyright (c) 2017 Aleksei Khitev (Хитёв Алексей Юрьевич).
 *
 * This file is part of IT-Organizer
 *
 * IT-Organizer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * IT-Organizer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
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
