package ru.akhitev.organizer.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.akhitev.organizer.entity.Progress;
import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.enums.Status;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProgressSpec {
    Progress progress;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp(){
        progress = new Progress();
    }

    @Test
    public void whenIdWasSetThenReturnIt() {
        Integer id = 0;
        progress.setId(id);
        assertThat(progress.getId())
                .as("When an id was set, return it.")
                .isEqualTo(id);
    }

    @Test
    public void whenIdIsNullThenReturnIt() {
        progress.setId(null);
        assertThat(progress.getId())
                .as("When an id was set, return it.")
                .isEqualTo(null);
    }

    @Test
    public void whenTicketKeyWasSetThenReturnIt() {
        Ticket ticket = new Ticket();
        progress.setTicket(ticket);
        assertThat(progress.getTicket())
                .as("check getting ticket of the task")
                .isEqualTo(ticket);
    }

    @Test
    public void whenTicketIsNullThenReturnNPE() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("ticket");
        progress.setTicket(null);
    }

    @Test
    public void whenStatusWasSetThenReturnIt() {
        String status = "continue working";
        progress.setStatus(status);
        assertThat(progress.getStatus())
                .as("check getting a status")
                .isEqualTo(status);

    }

    @Test
    public void whenNullStatusIsSetThenIllegalArgumentException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("status");
        progress.setStatus(null);
    }

    @Test
    public void whenDateWasSetThenReturnIt() {
        Date date = new Date();
        progress.setDate(date);
        assertThat(progress.getDate())
                .as("check getting a status")
                .isEqualTo(date);

    }

    @Test
    public void whenNullDateIsSetThenIllegalArgumentException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("date");
        progress.setDate(null);
    }
}
