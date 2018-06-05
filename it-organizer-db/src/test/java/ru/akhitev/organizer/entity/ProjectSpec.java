package ru.akhitev.organizer.entity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectSpec {
    private Project project;
    private String projectName = "Test Project";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        project = new Project();
    }

    @Test
    public void whenNameWasSetThenReturnIt() {
        project.setName(projectName);
        assertThat(project.getName())
                .as("When a name was set, return it.")
                .isEqualTo(projectName);
    }

    @Test
    public void whenNullNameThanIllegalArgumentException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("name");
        project.setName(null);
    }

    @Test
    public void whenIdWasSetThenReturnIt() {
        final Integer id = 1;
        project.setId(id);
        assertThat(project.getId())
                .as("When an id was set, return it.")
                .isEqualTo(id);
    }

    @Test
    public void whenIdIsNullThenReturnIt() {
        final Integer id = null;
        project.setId(id);
        assertThat(project.getId())
                .as("When an id was set, return it.")
                .isEqualTo(id);
    }


    @Test
    public void whenTicketsWereAddedThenReturnThem() {
        Set<Ticket> tickets = new LinkedHashSet<>();
        tickets.add(new Ticket());
        project.setTickets(tickets);
        assertThat(project.getTickets())
                .as("check getting not null tickets after they were added").isNotNull()
                .as("check getting correct size after tickets were added").hasSize(1);
    }

    @Test
    public void whenNoLinksWereSetThenReturnEmptyList() {
        assertThat(project.getLinks())
                .as("when no links were set then return not null empty map")
                .isEqualTo(Collections.emptySet());
    }

    @Test
    public void whenLinksWereSetThenReturnThem() {
        Set<ReferenceLink> links = new HashSet<>();
        ReferenceLink link = new ReferenceLink();
        links.add(link);
        project.setLinks(links);
        assertThat(project.getLinks())
                .as("check getting not null links after they were added").isNotNull()
                .as("check getting correct size after links were added").hasSize(1);
    }

    @Test
    public void whenNoNotesWereSetThenReturnEmptyList() {
        assertThat(project.getNotes())
                .as("when no notes were set then return not null empty map")
                .isEqualTo(Collections.emptySet());
    }

    @Test
    public void whenNotesWereSetThenReturnThem() {
        Set<Note> notes = new HashSet<>();
        Note note = new Note();
        notes.add(note);
        project.setNotes(notes);
        assertThat(project.getNotes())
                .as("check getting not null notes after they were added").isNotNull()
                .as("check getting correct size after notes were added").hasSize(1);
    }
}
