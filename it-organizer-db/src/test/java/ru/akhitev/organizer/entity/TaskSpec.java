package ru.akhitev.organizer.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.akhitev.organizer.entity.Task;
import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.enums.Status;

import static org.assertj.core.api.Assertions.assertThat;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskSpec {
    Task task;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUpTaskSpec() {
        task = new Task();
    }

    @Test
    public void whenNameWasSetThenReturnIt() {
        String name = "Check logs";
        task.setName(name);
        assertThat(task.getName())
                .as("check getting a name of the task")
                .isEqualTo(name);

    }

    @Test
    public void whenNullNameInConstructorThenIllegalArgumentException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("name");
        task.setName(null);
    }

    @Test
    public void whenTicketKeyWasSetThenReturnIt() {
        Ticket ticket = new Ticket();
        task.setTicket(ticket);
        assertThat(task.getTicket())
                .as("check getting a ticket of the task")
                .isEqualTo(ticket);
    }

    @Test
    public void whenTicketIsNullThenReturnNPE() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("ticket");
        task.setTicket(null);
    }

    @Test
    public void whenNoWorkspaceSetThenReturnNotNullWorkspace() {
        assertThat(task.getWorkspace())
                .as("check getting a not null workspace of the task if there were no entry added.").isEmpty();
    }

    @Test
    public void whenWorkspaceSetThenReturnWorkspace() {
        String workspace = "It seems, that cause of this problem is located in Some class";
        task.setWorkspace(workspace);
        assertThat(task.getWorkspace())
                .as("check getting a not empty workspace of the task after filling it.").isNotEmpty()
                .as("check getting workspace, that equals to what was filled").isEqualTo(workspace);
    }

    @Test
    public void whenNoStatusWasSetThenReturnDefault() {
        assertThat(task.getStatus())
                .as("check getting a not empty status when no one was set.").isNotNull()
                .as("check getting default status when no one was set.").isEqualTo(Status.OPEN);
    }

    @Test
    public void whenStatusWasSetThenReturnIt() {
        Status status = Status.CLOSED;
        task.setStatus(status);
        assertThat(task.getStatus())
                .as("check getting a not empty status.").isNotNull()
                .as("check getting status.").isEqualTo(status);
    }

    @Test
    public void whenIdWasSetThenReturnIt() {
        Integer id = 0;
        task.setId(id);
        assertThat(task.getId())
                .as("When an id was set, return it.")
                .isEqualTo(id);
    }

    @Test
    public void whenIdIsNullThenReturnIt() {
        task.setId(null);
        assertThat(task.getId())
                .as("When an id was set, return it.")
                .isEqualTo(null);
    }
}
