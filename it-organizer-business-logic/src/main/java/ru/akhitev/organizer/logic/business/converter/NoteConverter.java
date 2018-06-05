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

import org.springframework.stereotype.Component;
import ru.akhitev.organizer.entity.Note;
import ru.akhitev.organizer.entity.Project;
import ru.akhitev.organizer.logic.business.dto.project.note.NoteForEditor;
import ru.akhitev.organizer.logic.business.dto.project.note.NoteForList;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NoteConverter {
    public Set<NoteForList> convertFromNotesToNotesForList(Collection<Note> notes, Integer nameSize) {
        if (notes == null) {
            return Collections.emptySet();
        }
        return notes.stream()
                .map( note ->
                        new NoteForList(note.getId(),
                                note.getTitle(),
                                note.getNote(),
                                nameSize))
                .collect(Collectors.toSet());
    }

    public Note mergeLinkForListToLink(Note note, NoteForEditor noteForEditor, Project project) {
        if (note == null) {
            note = new Note();
        }
        note.setTitle(noteForEditor.getTitle());
        note.setProject(project);
        note.setNote(noteForEditor.getNote());
        return note;
    }

    public NoteForEditor convertFromReferenceLinkToReferenceLinkForEditor(Note note) {
        return new NoteForEditor(note.getId(), note.getTitle(), note.getNote());
    }
}
