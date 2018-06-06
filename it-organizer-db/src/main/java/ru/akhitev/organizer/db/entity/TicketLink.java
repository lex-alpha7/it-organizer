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

import lombok.*;
import ru.akhitev.organizer.db.enums.LinkType;

import javax.persistence.*;

@Entity
@Table(name = "Ticket_Link")
@SequenceGenerator(name = "seq", initialValue = 20)
@EqualsAndHashCode(exclude = "ticket")
public class TicketLink {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @ManyToOne @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @Column(name = "type", nullable = false)
    @Enumerated
    private LinkType type;

    @Column(name = "name")
    private String name;

    @Column(name = "link", nullable = false)
    private String link;

    public TicketLink() {
    }

    public TicketLink(Integer id, @NonNull Ticket ticket, @NonNull LinkType type, String name, @NonNull String link) {
        this.id = id;
        this.ticket = ticket;
        this.type = type;
        this.name = name;
        this.link = link;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(@NonNull Ticket ticket) {
        this.ticket = ticket;
    }

    public LinkType getType() {
        return type;
    }

    public void setType(@NonNull LinkType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(@NonNull String link) {
        this.link = link;
    }
}
