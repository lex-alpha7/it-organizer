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
import ru.akhitev.organizer.logic.business.vo.project.note.NoteForList;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The aim of the class is to create VO, DTO or their lists from entity. And make entity from them.
 */
@Component
public class NoteConverter {

    /**
     * This method converts notes into VOs to show in a sidebar.
     *
     * @param notes could be null. it is safe.
     * @param nameSize a note's name will be adjusted by this size.
     * @return emptyList if progresses are equal to null or a set of VOs
     */
    public Set<NoteForList> prepareNotesForList(Collection<Note> notes, Integer nameSize) {
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

    /**
     * The method prepares object for editor.
     * Data from entity is set into DTO.
     *
     * @param note entity, which is a source for DTO.
     * @return a DTO, filled with data from an entity.
     */
    public NoteForEditor prepareReferenceLinkForEditor(Note note) {
        return new NoteForEditor(note.getId(), note.getTitle(), note.getNote());
    }

    /**
     * This method prepares an entity for saving.
     * If there is no note (in case, it's a new one), a new note will be created and used. In another case an existed one will be used.
     *
     * @param note could be null. it is safe.
     * @param noteForEditor mustn't be null. It's data will be set to entity.
     * @param project will be used to link an entity to it in database.
     * @return full prepared entity will be returned. It'll be ready to store in a data base.
     */
    public Note mergeLinkForListToLink(Note note, NoteForEditor noteForEditor, Project project) {
        if (note == null) {
            note = new Note();
        }
        note.setTitle(noteForEditor.getTitle());
        note.setProject(project);
        note.setNote(noteForEditor.getNote());
        return note;
    }
}
