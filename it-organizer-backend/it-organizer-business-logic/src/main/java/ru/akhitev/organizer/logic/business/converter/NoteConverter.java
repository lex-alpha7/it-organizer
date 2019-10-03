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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.akhitev.organizer.db.entity.Note;
import ru.akhitev.organizer.db.entity.Project;
import ru.akhitev.organizer.logic.business.dto.project.note.NoteForEdit;
import ru.akhitev.organizer.logic.business.vo.project.note.NoteForShow;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/** {@inheritDoc} */
@Component
public class NoteConverter implements Converter<Note, NoteForShow, NoteForEdit> {

    /** size for adjustment too long names */
    @Value("${name.size}")
    private Integer nameSize;

    /** {@inheritDoc} */
    @Override
    public Set<NoteForShow> prepareForShow(Collection<Note> notes) {
        if (notes == null) {
            return Collections.emptySet();
        }
        return notes.stream()
                .map( note ->
                        new NoteForShow(note.getId(),
                                note.getTitle(),
                                note.getNote(),
                                nameSize))
                .collect(Collectors.toSet());
    }

    /** {@inheritDoc} */
    @Override
    public NoteForEdit prepareForEdit(Note note) {
        return new NoteForEdit(note.getId(), note.getTitle(), note.getNote());
    }

    /** {@inheritDoc} */
    @Override
    public Note merge(Note note, NoteForEdit noteForEdit) {
        if (note == null) {
            note = new Note();
        }
        note.setTitle(noteForEdit.getTitle());
        note.setNote(noteForEdit.getNote());
        return note;
    }
}
