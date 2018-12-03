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

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ProgressSpec {
    private Progress progress;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

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
