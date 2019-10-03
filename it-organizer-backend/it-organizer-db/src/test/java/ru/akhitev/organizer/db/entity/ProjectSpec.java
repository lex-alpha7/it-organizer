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

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectSpec {
    private Project project;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        project = new Project();
    }

    @Test
    public void whenNameWasSetThenReturnIt() {
        String projectName = "Test Project";
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
    public void whenTicketsWereAddedThenReturnThem() {
        Set<Ticket> tickets = new LinkedHashSet<>();
        tickets.add(new Ticket());
        project.setTickets(tickets);
        assertThat(project.getTickets())
                .as("check getting not null tickets after they were added").isNotNull()
                .as("check getting correct size after tickets were added").hasSize(1);
    }

    @Test
    public void whenNoLinksWereSetThenReturnEmptyList() {
        assertThat(project.getLinks())
                .as("when no links were set then return not null empty map")
                .isEqualTo(Collections.emptySet());
    }

    @Test
    public void whenLinksWereSetThenReturnThem() {
        Set<ReferenceLink> links = new HashSet<>();
        ReferenceLink link = new ReferenceLink();
        links.add(link);
        project.setLinks(links);
        assertThat(project.getLinks())
                .as("check getting not null links after they were added").isNotNull()
                .as("check getting correct size after links were added").hasSize(1);
    }

    @Test
    public void whenNoNotesWereSetThenReturnEmptyList() {
        assertThat(project.getNotes())
                .as("when no notes were set then return not null empty map")
                .isEqualTo(Collections.emptySet());
    }

    @Test
    public void whenNotesWereSetThenReturnThem() {
        Set<Note> notes = new HashSet<>();
        Note note = new Note();
        notes.add(note);
        project.setNotes(notes);
        assertThat(project.getNotes())
                .as("check getting not null notes after they were added").isNotNull()
                .as("check getting correct size after notes were added").hasSize(1);
    }
}
