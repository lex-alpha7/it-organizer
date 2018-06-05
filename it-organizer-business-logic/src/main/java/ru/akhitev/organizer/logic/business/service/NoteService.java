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
import ru.akhitev.organizer.logic.business.dto.project.note.NoteForEditor;
import ru.akhitev.organizer.logic.business.dto.project.note.NoteForList;
import ru.akhitev.organizer.repository.NoteRepository;

import java.util.Collections;
import java.util.Set;

@Service
public class NoteService {
    @Autowired
    private NoteRepository repository;

    @Autowired
    private NoteConverter converter;

    @Autowired
    private ProjectService projectService;

    public Set<NoteForList> giveNotesForListByProject(Integer nameSize) {
        if (projectService.getActiveProject() == null) {
            return Collections.emptySet();
        }
        Set<Note> notes = repository.findByProject(projectService.getActiveProject());
        return converter.convertFromNotesToNotesForList(notes, nameSize);
    }

    public Integer saveNote(NoteForEditor noteForEditor) {
        Integer id = noteForEditor.getId();
        Note note = null;
        if (id != null) {
            note = repository.getOne(id);
        }
        note = converter.mergeLinkForListToLink(note, noteForEditor, projectService.getActiveProject());
        return repository.save(note).getId();
    }

    public NoteForEditor giveNoteForEdit(Integer linkID) {
        return converter.convertFromReferenceLinkToReferenceLinkForEditor(repository.getOne(linkID));
    }

    public void removeLink(Integer linkID) {
        repository.deleteById(linkID);
    }
}
