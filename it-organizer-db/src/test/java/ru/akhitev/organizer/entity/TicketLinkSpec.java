package ru.akhitev.organizer.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.entity.TicketLink;
import ru.akhitev.organizer.enums.LinkType;

import static org.assertj.core.api.Assertions.assertThat;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketLinkSpec {
    TicketLink link;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        link = new TicketLink();
    }

    @Test
    public void whenIdWasSetThenReturnIt() {
        Integer id = 0;
        link.setId(id);
        assertThat(link.getId())
                .as("When an id was set, return it.")
                .isEqualTo(id);
    }

    @Test
    public void whenIdIsNullThenReturnIt() {
        link.setId(null);
        assertThat(link.getId())
                .as("When an id was set, return it.")
                .isEqualTo(null);
    }

    @Test
    public void whenTicketKeyWasSetThenReturnIt() {
        Ticket ticket = new Ticket();
        link.setTicket(ticket);
        assertThat(link.getTicket())
                .as("check getting ticket of the task")
                .isEqualTo(ticket);
    }

    @Test
    public void whenTicketIsNullThenReturnNPE() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("ticket");
        link.setTicket(null);
    }

    @Test
    public void whenNameWasSetThenReturnIt() {
        String name = "Best link";
        link.setName(name);
        assertThat(link.getName())
                .as("check getting a name of the link")
                .isEqualTo(name);

    }

    @Test
    public void whenNullNameWasSetIllegalArgumentException() {
        link.setName(null);
        assertThat(link.getName())
                .as("check getting a name of the link")
                .isEqualTo(null);
    }

    @Test
    public void whenLinkWasSetThenReturnIt() {
        String name = "www.best.link.com";
        link.setLink(name);
        assertThat(link.getLink())
                .as("check getting a name of the link")
                .isEqualTo(name);

    }

    @Test
    public void whenNullLinIsSetThenIllegalArgumentException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("link");
        link.setLink(null);
    }

    @Test
    public void whenTypeWasSetThenReturnIt() {
        link.setType(LinkType.ATTACHMENT);
        assertThat(link.getType())
                .as("check getting a type of the link")
                .isEqualTo(LinkType.ATTACHMENT);

    }

    @Test
    public void whenNullTypeIsSetThenIllegalArgumentException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("type");
        link.setType(null);
    }
}
