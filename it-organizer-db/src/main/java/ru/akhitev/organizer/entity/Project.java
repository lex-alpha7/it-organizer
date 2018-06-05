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

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Project")
@SequenceGenerator(name = "seq", initialValue = 20)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "project")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Ticket> tickets;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "project")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<ReferenceLink> links;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "project")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Note> notes;

    {
        tickets = new LinkedHashSet<>();
        links = new LinkedHashSet<>();
        notes = new LinkedHashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<ReferenceLink> getLinks() {
        return links;
    }

    public void setLinks(Set<ReferenceLink> links) {
        this.links = links;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tickets=" + tickets +
                ", links=" + links +
                ", notes=" + notes +
                '}';
    }
}
