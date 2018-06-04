/*
 * IT-Organizer is an organizer for a developer and other IT-specialists.
 * Copyright (c) 2017 Aleksei Khitev (Хитёв Алексей Юрьевич).
 *
 * This file is part of IT-Organizer
 *
 * IT-Organizer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * IT-Organizer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
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
