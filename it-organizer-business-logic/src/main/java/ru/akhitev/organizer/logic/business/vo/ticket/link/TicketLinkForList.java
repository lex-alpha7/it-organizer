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
package ru.akhitev.organizer.logic.business.vo.ticket.link;

import ru.akhitev.organizer.enums.LinkType;

public class TicketLinkForList {
    private final Integer id;
    private final LinkType type;
    private final String name;
    private final String link;
    private final String displayName;

    public TicketLinkForList(Integer id, LinkType type, String name, String link) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.link = link;
        this.displayName = String.format("%s (%s)", name, type);
    }

    public Integer getId() {
        return id;
    }

    public LinkType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getDisplayName() {
        return displayName;
    }

}
