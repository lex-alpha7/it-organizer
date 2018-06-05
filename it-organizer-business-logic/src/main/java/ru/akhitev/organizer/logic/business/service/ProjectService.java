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
import ru.akhitev.organizer.entity.Project;
import ru.akhitev.organizer.logic.business.converter.ProjectConverter;
import ru.akhitev.organizer.logic.business.dto.project.ProjectForEditor;
import ru.akhitev.organizer.logic.business.vo.project.ProjectForList;
import ru.akhitev.organizer.repository.ProjectRepository;

import java.util.Set;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repository;

    @Autowired
    private ProjectConverter converter;

    private Project activeProject;

    public Set<ProjectForList> giveProjectsForList(Integer nameSize) {
        return converter.prepareProjectsForList(repository.findAll(), nameSize);
    }

    public ProjectForEditor giveProjectForEdit(Integer projectId, Integer nameSize) {
        return converter.prepareProjectForEditor(repository.getOne(projectId), nameSize);
    }

    public Integer saveProject(ProjectForEditor projectForEditor) {
        Integer id = projectForEditor.getId();
        Project project = null;
        if (id != null) {
            project = repository.getOne(id);
        }
        project = converter.mergeProjectForListToProject(project, projectForEditor);
        return repository.save(project).getId();
    }

    public void removeProject(Integer projectID) {
        repository.deleteById(projectID);
    }

    public void activateProject(Integer projectId) {
        activeProject = repository.getOne(projectId);
    }

    public Project getActiveProject() {
        return activeProject;
    }

    public boolean ifActiveProject() {
        return activeProject != null;
    }
}
