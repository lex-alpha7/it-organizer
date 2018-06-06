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
import ru.akhitev.organizer.entity.Note;
import ru.akhitev.organizer.logic.business.converter.NoteConverter;
import ru.akhitev.organizer.logic.business.dto.project.note.NoteForEdit;
import ru.akhitev.organizer.logic.business.vo.project.note.NoteForShow;
import ru.akhitev.organizer.repository.NoteRepository;

import java.util.Collections;
import java.util.Set;

/**
 * The aim of service is to provide give, remove and save DTOs and VOs.
 * A service uses converters, repositories and other services.
 * No one else should use data base layer.
 */
@Service
public class NoteService {
    /** The main repository. */
    @Autowired
    private NoteRepository repository;

    /** The main converter. */
    @Autowired
    private NoteConverter converter;

    /** Uses for getting {@link ProjectService#activeProject}. */
    @Autowired
    private ProjectService projectService;

    /**
     * Returns collection of VOs.
     * If there is no {@link ProjectService#activeProject}, then empty set is returned.
     *
     * @param nameSize name of a VO is adjusted by this size.
     * @return collection of VOs.
     */
    public Set<NoteForShow> giveNotesForShowForActiveProject(Integer nameSize) {
        if (projectService.getActiveProject() == null) {
            return Collections.emptySet();
        }
        Set<Note> notes = repository.findByProject(projectService.getActiveProject());
        return converter.prepareNotesForShow(notes, nameSize);
    }

    /**
     * The method prepares DTO from entity, get by ID.
     *
     * @param noteID ID to find entity in data base.
     * @return DTO from entity.
     */
    public NoteForEdit giveNoteForEdit(Integer noteID) {
        return converter.prepareReferenceLinkForEdit(repository.getOne(noteID));
    }

    /**
     * The method saves DTO.
     * If it is a create operation and there is no entity, then a new one is created.
     * {@link ProjectService#activeProject} is set to project in the ticket.
     *
     * @param noteForEdit DTO, which will be saved.
     * @return ID of saved entity.
     */
    public Integer saveNote(NoteForEdit noteForEdit) {
        Integer id = noteForEdit.getId();
        Note note = null;
        if (id != null) {
            note = repository.getOne(id);
        }
        note = converter.mergeLinkForEditToLink(note, noteForEdit, projectService.getActiveProject());
        return repository.save(note).getId();
    }

    /**
     * The method removes found by id entity in data base.
     * @param noteID ID to find entity in data base.
     */
    public void removeLink(Integer noteID) {
        repository.deleteById(noteID);
    }
}
