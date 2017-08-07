package ru.akhitev.organizer.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.akhitev.organizer.enums.Status;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketSpec {
    Ticket ticket;
    String key = "SUP-12345";
    String name = "An error in response while sending a test request.";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUpTicketSpec() {
        ticket = new Ticket(key);
    }

    @Test
    public void whenKeyWasSetThenReturnIt() {
        assertThat(ticket.getKey())
                .as("check getting a key of the ticket")
                .isEqualTo(key);
    }

    @Test
    public void whenNameWasSetThenReturnIt() {
        ticket.setName(name);
        assertThat(ticket.getName())
                .as("check getting a name of the ticket")
                .isEqualTo(name);
    }

    @Test
    public void whenKeyWasSetAndNameWasNotThenReturnOnlyKeyAsFullName() {
        assertThat(ticket.getFullName())
                .as("check getting a full name of the ticket without setting a name")
                .isEqualTo(key);
    }

    @Test
    public void whenKeyAndNameHasMoreThen20SymbolsThenReturnCutFullName() {
        ticket.setName(name);
        assertThat(ticket.getFullName())
                .as("check getting a full name of the ticket with name more than 20 symbols")
                .isEqualTo("SUP-12345: An error in response...");
    }

    @Test
    public void whenKeyAndNameHas20SymbolsThenReturnNotCuttedFullName() {
        ticket.setName("An error in response");
        assertThat(ticket.getFullName())
                .as("check getting a full name of the ticket with name less or equals than 20 symbols")
                .isEqualTo("SUP-12345: An error in response");
    }

    @Test
    public void whenNullKeyInConstructorThenIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Key should not be empty");
        ticket = new Ticket(null);
    }

    @Test
    public void whenNoLinksWereAddedThenReturnEmptyList() {
        assertThat(ticket.getLinks())
                .as("check getting not null links if there were no link added").isNotNull()
                .as("check getting an empty list of links if there were no link added").isEqualTo(Collections.emptyList());
    }

    @Test
    public void whenLinksWereAddedThenReturnListOfThem() {
        String link1 = "https://someServer.com/SUP-12345";
        ticket.addLink(link1);
        String link2 = "https://someServer.com/COPY-OF-SUP-12345";
        ticket.addLink(link2);
        assertThat(ticket.getLinks())
                .as("check getting not null links after links were added").isNotNull()
                .as("check getting correct size after links were added").hasSize(2)
                .as("check containing list with first link of links after links were added").contains(link1)
                .as("check containing list with second link of links after links were added").contains(link2);
    }

    @Test
    public void whenNoAttachmentsWereAddedThenReturnEmptyList() {
        assertThat(ticket.getAttachments())
                .as("check getting not null links if there were no link added").isNotNull()
                .as("check getting an empty list of links if there were no link added").isEqualTo(Collections.emptyList());
    }

    @Test
    public void whenAttachmentsWereAddedThenReturnListOfThem() {
        String attachment1 = "someFileName1";
        ticket.addAttachment(attachment1);
        String attachment2 = "someFileName2";
        ticket.addAttachment(attachment2);
        assertThat(ticket.getAttachments())
                .as("check getting not null links after links were added").isNotNull()
                .as("check getting correct size after links were added").hasSize(2)
                .as("check containing list with first link of links after links were added").contains(attachment1)
                .as("check containing list with second link of links after links were added").contains(attachment2);
    }

    @Test
    public void whenNoProgressWereAddedThenReturnEmptyMap() {
        assertThat(ticket.getProgress())
                .as("check getting not null map if there were no progress added").isNotNull()
                .as("check getting an empty map of progress if no of it was added").isEqualTo(Collections.emptyMap());
    }

    @Test
    public void whenProgressWereAddedThenReturnMapOfStatuses() {
        Date progressDate1 = new Date(System.currentTimeMillis() - 1000);
        String progressText1 = "looked into logs.";
        ticket.addProgress(progressDate1, progressText1);
        Date progressDate2 = new Date();
        String progressText2 = "investigated code";
        ticket.addProgress(progressDate2, progressText2);
        assertThat(ticket.getProgress())
                .as("check getting not null map after progress was added").isNotNull()
                .as("check getting correct size after progress was added").hasSize(2)
                .as("check containing list with first status after progress was added").contains(new HashMap.SimpleEntry<>(progressDate1, progressText1))
                .as("check containing list with second status after progress was added").contains(new HashMap.SimpleEntry<>(progressDate2, progressText2));
    }

    @Test
    public void whenNoProgressWereAddedThenReturnDefaultReport() {
        String defaultReport = "Started working on it.";
        assertThat(ticket.getProgressReport())
                .as("check getting not null report if there was no progress added").isNotNull()
                .as("check getting default report if there was no progress added").isEqualTo(defaultReport);
    }

    @Test
    public void whenProgressWereAddedThenMakeReportFromThem() {
        Date progressDate1 = new Date(System.currentTimeMillis() - 1000);
        String progressText1 = "looked into logs.";
        ticket.addProgress(progressDate1, progressText1);
        Date progressDate2 = new Date();
        String progressText2 = "investigated code";
        ticket.addProgress(progressDate2, progressText2);
        String report = String.format("%s:\n%s\n\n%s:\n%s",progressDate1,progressText1,progressDate2,progressText2);
        assertThat(ticket.getProgressReport())
                .as("check getting not null report after progress was added").isNotNull()
                .as("check getting report from statuses after progress was added").isEqualTo(report);
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
                .as("check getting an empty list of links if there were no link added").isEqualTo(Collections.emptyList());
    }

    @Test
    public void whenTasksWereAddedThenReturnListOfThem() {
        Task task11 = new Task(ticket, "someFileName1");
        ticket.addTask(task11);
        Task task12 = new Task(ticket, "someFileName2");
        ticket.addTask(task12);
        assertThat(ticket.getTasks())
                .as("check getting not null tasks after they were added").isNotNull()
                .as("check getting correct size after tasks were added").hasSize(2)
                .as("check containing list with first task after tasks were added").contains(task11)
                .as("check containing list with second task after tasks were added").contains(task12);
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
}
