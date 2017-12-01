package ru.akhitev.organizer.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.akhitev.organizer.enums.Status;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketSpec {
    Ticket ticket;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUpTicketSpec() {
        ticket = new Ticket();
    }

    @Test
    public void whenKeyWasSetThenReturnIt() {
        String key = "SUP-12345";
        ticket.setKey(key);
        assertThat(ticket.getKey())
                .as("check getting a key of the ticket")
                .isEqualTo(key);
    }

    @Test
    public void whenNameWasSetThenReturnIt() {
        String name = "An error in response while sending a test request.";
        ticket.setName(name);
        assertThat(ticket.getName())
                .as("check getting a name of the ticket")
                .isEqualTo(name);
    }

    @Test
    public void whenNullKeyInConstructorThenIllegalArgumentException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("key");
        ticket.setKey(null);
    }

    @Test
    public void whenNoLinksWereAddedThenReturnEmptyList() {
        assertThat(ticket.getLinks())
                .as("check getting not null links if there were no link added").isNotNull()
                .as("check getting an empty list of links if there were no link added").isEqualTo(Collections.emptySet());
    }

    @Test
    public void whenLinksWereAddedThenReturnListOfThem() {
        Set<TicketLink> links = new LinkedHashSet<>();
        links.add(new TicketLink());
        ticket.setLinks(links);
        assertThat(ticket.getLinks())
                .as("check getting not null links after links were added").isNotNull()
                .as("check getting correct size after links were added").hasSize(1);
    }

    @Test
    public void whenNoProgressWereAddedThenReturnEmptyMap() {
        assertThat(ticket.getProgress())
                .as("check getting not null map if there were no progress added").isNotNull()
                .as("check getting an empty map of progress if no of it was added").isEqualTo(Collections.emptySet());
    }

    @Test
    public void whenProgressWereAddedThenReturnMapOfStatuses() {
        Set<Progress> progress = new LinkedHashSet<>();
        progress.add(new Progress());
        ticket.setProgress(progress);
        assertThat(ticket.getProgress())
                .as("check getting not null map after progress was added").isNotNull()
                .as("check getting correct size after progress was added").hasSize(1);
    }

    @Test
    public void whenNoWorkspaceSetThenReturnNotNullWorkspace() {
        assertThat(ticket.getWorkspace())
                .as("check getting a not null workspace of the ticket if there were no entry added.").isEmpty();
    }

    @Test
    public void whenWorkspaceSetThenReturnWorkspace() {
        String workspace = "It seems, that cause of this problem is located in Some class";
        ticket.setWorkspace(workspace);
        assertThat(ticket.getWorkspace())
                .as("check getting a not empty workspace of the ticket after filling it.").isNotEmpty()
                .as("check getting workspace, that equals to what was filled").isEqualTo(workspace);
    }

    @Test
    public void whenNoTasksWereAddedThenReturnEmptyList() {
        assertThat(ticket.getTasks())
                .as("check getting not null links if there were no link added").isNotNull()
                .as("check getting an empty list of links if there were no link added").isEqualTo(Collections.emptySet());
    }

    @Test
    public void whenTasksWereAddedThenReturnListOfThem() {
        Set<Task> tasks = new LinkedHashSet<>();
        tasks.add(new Task());
        ticket.setTasks(tasks);
        assertThat(ticket.getTasks())
                .as("check getting not null tasks after they were added").isNotNull()
                .as("check getting correct size after tasks were added").hasSize(1);
    }

    @Test
    public void whenNoStatusWasSetThenReturnDefault() {
        assertThat(ticket.getStatus())
                .as("check getting a not empty status when no one was set.").isNotNull()
                .as("check getting default status when no one was set.").isEqualTo(Status.OPEN);
    }

    @Test
    public void whenStatusWasSetThenReturnIt() {
        Status status = Status.CLOSED;
        ticket.setStatus(status);
        assertThat(ticket.getStatus())
                .as("check getting a not empty status.").isNotNull()
                .as("check getting status.").isEqualTo(status);
    }

    @Test
    public void whenIdWasSetThenReturnIt() {
        Integer id = 0;
        ticket.setId(id);
        assertThat(ticket.getId())
                .as("When an id was set, return it.")
                .isEqualTo(id);
    }

    @Test
    public void whenIdIsNullThenReturnIt() {
        ticket.setId(null);
        assertThat(ticket.getId())
                .as("When an id was set, return it.")
                .isEqualTo(null);
    }

    @Test
    public void whenProjectWasSetThenReturnIt() {
        Project project = new Project();
        ticket.setProject(project);
        assertThat(ticket.getProject())
                .as("When a project was set, return it.")
                .isEqualTo(project);
    }

    @Test
    public void whenProjectIsNullThenReturnNPE() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("project");
        ticket.setProject(null);
    }
}
