package ru.akhitev.organizer.entity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class NoteSpec {
    private Note note;
    private final Integer id = 0;
    private final String title = "The best note";
    private final String noteText = "You need to read the Clean Code book.";

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
