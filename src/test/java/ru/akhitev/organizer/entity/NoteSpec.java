package ru.akhitev.organizer.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class NoteSpec {
    Note note;
    final Integer id = 0;
    final String title = "The best note";
    final String noteText = "You need to read the Clean Code book.";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        note = new Note();
    }

    @Test
    public void whenTitleWasSetThenReturnIt() {
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
        note.setId(id);
        assertThat(note.getId())
                .as("When an id was set, return it.")
                .isEqualTo(id);
    }

    @Test
    public void whenIdIsNullThenReturnIt() {
        note.setId(null);
        assertThat(note.getId())
                .as("WHen an id was set, return it.")
                .isEqualTo(null);
    }

    @Test
    public void whenReferenceWasSetThenReturnIt() {
        Reference reference = new Reference();
        note.setReference(reference);
        assertThat(note.getReference())
                .as("WHen a reference was set, return it.")
                .isEqualTo(reference);
    }

    @Test
    public void whenReferenceIsNullThenReturnNPE() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("reference");
        note.setReference(null);
    }

}
