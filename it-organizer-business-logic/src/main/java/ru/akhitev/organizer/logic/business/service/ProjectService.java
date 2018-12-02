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
package ru.akhitev.organizer.logic.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhitev.organizer.db.entity.Project;
import ru.akhitev.organizer.logic.business.converter.ProjectConverter;
import ru.akhitev.organizer.logic.business.dto.project.ProjectForEdit;
import ru.akhitev.organizer.logic.business.vo.project.ProjectForShow;
import ru.akhitev.organizer.db.repository.ProjectRepository;

import java.util.Set;

/**
 * The aim of service is to provide give, remove and save DTOs and VOs.
 * A service uses converters, repositories and other services.
 * No one else should use data base layer.
 */
@Service
public class ProjectService extends AbstractService<ProjectConverter, ProjectRepository, Project, ProjectForShow, ProjectForEdit> {
    /** The main repository. */
    @Autowired
    private ProjectRepository repository;

    /** The main converter. */
    @Autowired
    private ProjectConverter converter;

    /** If there is an active project, then tickets, reference links, notes and other lists is shown. */
    private Project activeProject;


    /**
     * The method saves DTO.
     * If it is a create operation and there is no entity, then a new one is created.
     *
     * @param projectForEdit DTO, which will be saved.
     */
    public void saveProject(ProjectForEdit projectForEdit) {
        Integer id = projectForEdit.getId();
        Project project = null;
        if (id != null) {
            project = repository.getOne(id);
        }
        project = converter.merge(project, projectForEdit);
        repository.save(project);
    }

    /**
     * The method make a found by ID entity activated.
     * If there is an active project, then tickets, reference links, notes and other lists is shown
     *
     * @param projectID ID to find entity in data base.
     */
    public void activateProject(Integer projectID) {
        activeProject = repository.getOne(projectID);
    }

    /**
     * The method returns an activated entity.
     * If there is an active project, then tickets, reference links, notes and other lists is shown
     *
     * @return activated entity.
     */
    Project getActiveProject() {
        return activeProject;
    }

    /**
     * The method return true if there is an activated project. Otherwise it returns false.
     * @return if there is an activated project.
     */
    public boolean ifActiveProject() {
        return activeProject != null;
    }


    @Override
    ProjectConverter converter() {
        return converter;
    }

    @Override
    ProjectRepository repository() {
        return repository;
    }
}
