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
public class ReferenceLinkService {
    /** The main repository. */
    @Autowired
    private ReferenceLinkRepository repository;

    /** The main converter. */
    @Autowired
    private ReferenceLinkConverter converter;

    /** Uses for getting {@link ProjectService#activeProject}. */
    @Autowired
    private ProjectService projectService;

    /**
     * Returns collection of VOs.
     * If there is no {@link ProjectService#activeProject}, then empty set is returned.
     *
     * @return collection of VOs.
     */
    public Set<ReferenceLinkForShow> giveReferenceLinksForShowForActiveProject() {
        if (projectService.getActiveProject() == null) {
            return Collections.emptySet();
        }
        Set<ReferenceLink> links = repository.findByProject(projectService.getActiveProject());
        return converter.prepareForShow(links);
    }

    /**
     * The method prepares DTO from entity, get by ID.
     *
     * @param linkID ID to find entity in data base.
     * @return DTO from entity.
     */
    public ReferenceLinkForEdit giveTicketForEdit(Integer linkID) {
        return converter.prepareForEdit(repository.getOne(linkID));
    }

    /**
     * The method saves DTO.
     * If it is a create operation and there is no entity, then a new one is created.
     * {@link ProjectService#activeProject} is set to project in the link.
     *
     * @param linkForEditor DTO, which will be saved.
     */
    public void saveLink(ReferenceLinkForEdit linkForEditor) {
        Integer id = linkForEditor.getId();
        ReferenceLink link = null;
        if (id != null) {
            link = repository.getOne(id);
        }
        link = converter.merge(link, linkForEditor);
        link.setProject(projectService.getActiveProject());
        repository.save(link);
    }

    /**
     * The method removes found by id entity in data base.
     * @param linkID ID to find entity in data base.
     */
    public void removeLink(Integer linkID) {
        repository.deleteById(linkID);
    }
}
