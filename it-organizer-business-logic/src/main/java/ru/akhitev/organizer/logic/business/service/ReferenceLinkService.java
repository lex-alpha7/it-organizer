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
import ru.akhitev.organizer.entity.ReferenceLink;
import ru.akhitev.organizer.logic.business.converter.ReferenceLinkConverter;
import ru.akhitev.organizer.logic.business.dto.project.link.ReferenceLinkForEditor;
import ru.akhitev.organizer.logic.business.vo.project.link.ReferenceLinkForList;
import ru.akhitev.organizer.repository.ReferenceLinkRepository;

import java.util.Collections;
import java.util.Set;

@Service
public class ReferenceLinkService {
    @Autowired
    private ReferenceLinkRepository repository;

    @Autowired
    private ReferenceLinkConverter converter;

    @Autowired
    private ProjectService projectService;

    public Set<ReferenceLinkForList> giveReferenceLinksForListByProject(Integer nameSize) {
        if (projectService.getActiveProject() == null) {
            return Collections.emptySet();
        }
        Set<ReferenceLink> links = repository.findByProject(projectService.getActiveProject());
        return converter.prepareLinksForList(links, nameSize);
    }

    public Integer saveLink(ReferenceLinkForEditor linkForEditor) {
        Integer id = linkForEditor.getId();
        ReferenceLink link = null;
        if (id != null) {
            link = repository.getOne(id);
        }
        link = converter.mergeLinkForListToLink(link, linkForEditor, projectService.getActiveProject());
        return repository.save(link).getId();
    }

    public ReferenceLinkForEditor giveTicketForEdit(Integer linkID) {
        return converter.prepareReferenceLinkForEditor(repository.getOne(linkID));
    }

    public void removeLink(Integer linkID) {
        repository.deleteById(linkID);
    }
}
