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
package ru.akhitev.organizer.db.entity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.akhitev.organizer.db.enums.Status;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskSpec {
    private Task task;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

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
