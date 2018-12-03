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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.akhitev.organizer.db.entity.Project;
import ru.akhitev.organizer.logic.business.dto.project.ProjectForEdit;
import ru.akhitev.organizer.logic.business.vo.project.ProjectForShow;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/** {@inheritDoc} */
@Component
public class ProjectConverter implements Converter<Project, ProjectForShow, ProjectForEdit> {
    /** Uses to prepare tickets during preparation for editor. */
    private final TicketConverter ticketConverter;

    /** size for adjustment too long names */
    @Value("${name.size}")
    private Integer nameSize;

    @Autowired
    public ProjectConverter(TicketConverter ticketConverter) {
        this.ticketConverter = ticketConverter;
    }

    /** {@inheritDoc} */
    @Override
    public Set<ProjectForShow> prepareForShow(Collection<Project> projects) {
        return projects.stream().map((project) -> new ProjectForShow(project.getId(), project.getName(), nameSize))
                .collect(Collectors.toSet());
    }

    /** {@inheritDoc} */
    @Override
    public ProjectForEdit prepareForEdit(Project project) {
        return new ProjectForEdit(project.getId(), project.getName(),
                ticketConverter.prepareForShow(project.getTickets()));
    }

    /** {@inheritDoc} */
    @Override
    public Project merge(Project project, ProjectForEdit projectForEdit) {
        if (project == null) {
            project = new Project();
        }
        project.setName(projectForEdit.getName());
        return project;
    }
}
