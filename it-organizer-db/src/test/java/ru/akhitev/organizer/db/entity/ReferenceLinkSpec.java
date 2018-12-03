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

import static org.assertj.core.api.Assertions.assertThat;

public class ReferenceLinkSpec {
    private ReferenceLink link;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        link = new ReferenceLink();
    }

    @Test
    public void whenNameWasSetThenReturnIt() {
        String name = "The best link";
        link.setName(name);
        assertThat(link.getName())
                .as("When a name was set, return it.")
                .isEqualTo(name);
    }

    @Test
    public void whenNameIsNullThenReturnIt() {
        link.setName(null);
        assertThat(link.getName())
                .as("When a name is null, return it.")
                .isEqualTo(null);
    }

    @Test
    public void whenLinkWasSetThenReturnIt() {
        String linkText = "http://www.best.link.com";
        link.setLink(linkText);
        assertThat(link.getLink())
                .as("When a link was set, return it.")
                .isEqualTo(linkText);
    }

    @Test
    public void whenLinkIsNullThenReturnNPE() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("link");
        link.setLink(null);
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
    public void whenReferenceWasSetThenReturnIt() {
        Project project = new Project();
        link.setProject(project);
        assertThat(link.getProject())
                .as("When a project was set, return it.")
                .isEqualTo(project);
    }

    @Test
    public void whenReferenceIsNullThenReturnNPE() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("project");
        link.setProject(null);
    }
}
