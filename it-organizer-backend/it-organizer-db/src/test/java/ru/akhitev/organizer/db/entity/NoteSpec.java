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
package ru.akhitev.organizer.db.entity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class NoteSpec {
    private Note note;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        note = new Note();
    }

    @Test
    public void whenTitleWasSetThenReturnIt() {
        String title = "The best note";
        note.setTitle(title);
        assertThat(note.getTitle())
                .as("When a title was set, return it.")
                .isEqualTo(title);
    }

    @Test
    public void whenTitleIsNullThenReturnIt() {
        note.setTitle(null);
        assertThat(note.getTitle())
                .as("When a title is null, return it.")
                .isEqualTo(null);
    }

    @Test
    public void whenNoteWasSetThenReturnIt() {
        String noteText = "You need to read the Clean Code book.";
        note.setNote(noteText);
        assertThat(note.getNote())
                .as("WHen a note was set, return it.")
                .isEqualTo(noteText);
    }

    @Test
    public void whenNoteIsNullThenReturnNPE() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("note");
        note.setNote(null);
    }

    @Test
    public void whenIdWasSetThenReturnIt() {
        Integer id = 0;
        note.setId(id);
        assertThat(note.getId())
                .as("When an id was set, return it.")
                .isEqualTo(id);
    }

    @Test
    public void whenIdIsNullThenReturnIt() {
        note.setId(null);
        assertThat(note.getId())
                .as("When an id was set, return it.")
                .isEqualTo(null);
    }

    @Test
    public void whenReferenceWasSetThenReturnIt() {
        Project project = new Project();
        note.setProject(project);
        assertThat(note.getProject())
                .as("When a project was set, return it.")
                .isEqualTo(project);
    }

    @Test
    public void whenReferenceIsNullThenReturnNPE() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("project");
        note.setProject(null);
    }

}
