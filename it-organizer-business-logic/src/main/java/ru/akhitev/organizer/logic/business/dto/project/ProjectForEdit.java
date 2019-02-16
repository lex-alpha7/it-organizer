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
package ru.akhitev.organizer.logic.business.dto.project;

import ru.akhitev.organizer.logic.business.dto.DataTransferObject;
import ru.akhitev.organizer.logic.business.vo.ticket.TicketForShow;

import java.util.Objects;
import java.util.Set;

public class ProjectForEdit implements DataTransferObject {
    private Integer id;
    private String name;
    private Set<TicketForShow> tickets;

    public ProjectForEdit() {
    }

    public ProjectForEdit(Integer id, String name, Set<TicketForShow> tickets) {
        this.id = id;
        this.name = name;
        this.tickets = tickets;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TicketForShow> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketForShow> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectForEdit that = (ProjectForEdit) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(tickets, that.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tickets);
    }
}
