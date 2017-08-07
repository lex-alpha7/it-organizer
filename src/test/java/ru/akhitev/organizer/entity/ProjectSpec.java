package ru.akhitev.organizer.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectSpec {
    Project project;
    String projectName = "Test Project";
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
    public void whenReferenceWasSetThenReturnIt() {
        Reference reference = new Reference();
        project.setReference(reference);
        assertThat(project.getReference())
                .as("When a reference was set, return it.")
                .isEqualTo(reference);
    }

    @Test
    public void whenReferenceIsNullThenReturnNPE() {
        project.setReference(null);
        assertThat(project.getReference())
                .as("When a reference was set, return it.")
                .isEqualTo(null);
    }

    @Test
    public void whenTicketsWereAddedThenReturnThem() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket());
        tickets.add(new Ticket());
        project.setTickets(tickets);
        assertThat(project.getTickets())
                .as("check getting not null tickets after they were added").isNotNull()
                .as("check getting correct size after tickets were added").hasSize(2);
    }
}
