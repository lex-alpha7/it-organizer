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
import ru.akhitev.organizer.logic.business.dto.project.ProjectForEdit;
import ru.akhitev.organizer.logic.business.vo.project.ProjectForShow;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The aim of the class is to create VO, DTO or their lists from entity. And make entity from them.
 */
@Component
public class ProjectConverter {
    /** Uses to prepare tickets during preparation for editor. */
    @Autowired
    private TicketConverter ticketConverter;

    /**
     * This method converts notes into VOs to show in a sidebar.
     *
     * @param projects could be null. it is safe.
     * @param nameSize a note's name will be adjusted by this size.
     * @return emptyList if progresses are equal to null or a set of VOs
     */
    public Set<ProjectForShow> prepareProjectsForShow(List<Project> projects, Integer nameSize) {
        return projects.stream().map((project) -> new ProjectForShow(project.getId(), project.getName(), nameSize))
                .collect(Collectors.toSet());
    }

    /**
     * The method prepares object for editor.
     * Data from entity is set into DTO.
     *
     * @param project entity, which is a source for DTO.
     * @param nameSize is used for preparing ticket list.
     * @return a DTO, filled with data from an entity.
     */
    public ProjectForEdit prepareProjectForEdit(Project project, Integer nameSize) {
        return new ProjectForEdit(project.getId(), project.getName(),
                ticketConverter.prepareTicketsForShow(project.getTickets(), nameSize));
    }

    /**
     * This method prepares an entity for saving.
     * If there is no entity (in case, it's a new one), a new note will be created and used. In another case an existed one will be used.
     *
     * @param project could be null. it is safe.
     * @param projectForEdit mustn't be null. It's data will be set to entity.
     * @return full prepared entity will be returned. It'll be ready to store in a data base.
     */
    public Project mergeProjectForEditToProject(Project project, ProjectForEdit projectForEdit) {
        if (project == null) {
            project = new Project();
        }
        project.setName(projectForEdit.getName());
        return project;
    }
}
