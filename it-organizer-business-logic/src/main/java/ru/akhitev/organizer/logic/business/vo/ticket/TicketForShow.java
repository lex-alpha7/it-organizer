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
package ru.akhitev.organizer.logic.business.vo.ticket;

import ru.akhitev.organizer.logic.business.dto.ticket.TemplatedDisplayName;
import ru.akhitev.organizer.logic.business.vo.AdjustableNameSize;

public class TicketForShow implements AdjustableNameSize, TemplatedDisplayName {
    private final Integer id;
    private final String displayedName;

    public TicketForShow(Integer id, String key, String priority, String name, Integer nameSize) {
        this.id = id;
        this.displayedName = constructDisplayedName(key, priority, name, nameSize);
    }

    private String constructDisplayedName(String key, String priority, String name, Integer nameSize) {
        return adjustSize(String.format(DISPLAYED_NAME_TEMPLATE, key, priority, name), nameSize);
    }

    public Integer getId() {
        return id;
    }

    public String getDisplayedName() {
        return displayedName;
    }
}
