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
import ru.akhitev.organizer.db.entity.ReferenceLink;
import ru.akhitev.organizer.logic.business.converter.ReferenceLinkConverter;
import ru.akhitev.organizer.logic.business.dto.project.link.ReferenceLinkForEdit;
import ru.akhitev.organizer.logic.business.vo.project.link.ReferenceLinkForShow;
import ru.akhitev.organizer.db.repository.ReferenceLinkRepository;

import java.util.Collections;
import java.util.Set;

/**
 * The aim of service is to provide give, remove and save DTOs and VOs.
 * A service uses converters, repositories and other services.
 * No one else should use data base layer.
 */
@Service
public class ReferenceLinkService extends AbstractNodeService<Project, ReferenceLinkConverter, ReferenceLinkRepository, ReferenceLink, ReferenceLinkForShow, ReferenceLinkForEdit> {
    /** The main repository. */
    private final ReferenceLinkRepository repository;

    /** The main converter. */
    private final ReferenceLinkConverter converter;

    /** Uses for getting {@link ProjectService#activeProject}. */
    private final ProjectService projectService;

    @Autowired
    public ReferenceLinkService(ReferenceLinkRepository repository, ReferenceLinkConverter converter, ProjectService projectService) {
        this.repository = repository;
        this.converter = converter;
        this.projectService = projectService;
    }

    @Override
    Set<ReferenceLink> queryEntitiesForActiveRoot() {
        return repository.findByProject(projectService.getActiveProject());
    }

    @Override
    Project activeRoot() {
        return projectService.getActiveProject();
    }

    @Override
    ReferenceLinkConverter converter() {
        return converter;
    }

    @Override
    ReferenceLinkRepository repository() {
        return repository;
    }
}
