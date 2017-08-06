package ru.akhitev.organizer.entity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.akhitev.organizer.enums.Status;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskSpec {
    private Task task;
    private Ticket parentTicket;
    private String name = "Check logs";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUpTaskSpec() {
        parentTicket = new Ticket("SUP-12345");
        task = new Task(parentTicket, name);
    }

    @Test
    public void whenNameWasSetThenReturnIt() {
        assertThat(task.getName())
                .as("check getting a name of the task")
                .isEqualTo(name);

    }

    @Test
    public void whenNullNameInConstructorThenIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Name and Parent Key should not be empty");
        task = new Task(parentTicket, null);
    }

    @Test
    public void whenParentKeyWasSetThenReturnIt() {
        assertThat(task.getParentTicket())
                .as("check getting a key of parent of the task")
                .isEqualTo(parentTicket);
    }

    @Test
    public void whenNullParentKeyInConstructorThenIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Name and Parent Key should not be empty");
        task = new Task(null, name);
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
}
