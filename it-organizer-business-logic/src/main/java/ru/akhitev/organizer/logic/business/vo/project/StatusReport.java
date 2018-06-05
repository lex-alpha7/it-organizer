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
package ru.akhitev.organizer.logic.business.vo.project;

import ru.akhitev.organizer.logic.business.vo.ticket.TicketForList;
import ru.akhitev.organizer.logic.business.vo.ticket.progress.ProgressForShow;

import java.util.Map;
import java.util.Set;

public class StatusReport {
    private final Map<TicketForList, Set<ProgressForShow>> progress;

    public StatusReport(Map<TicketForList, Set<ProgressForShow>> progress) {
        this.progress = progress;
    }

    public Map<TicketForList, Set<ProgressForShow>> getProgress() {
        return progress;
    }
}
