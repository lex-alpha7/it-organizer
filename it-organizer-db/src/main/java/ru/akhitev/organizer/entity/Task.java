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
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import ru.akhitev.organizer.enums.Status;

import javax.persistence.*;

@Entity
@Table(name = "Task")
@SequenceGenerator(name = "seq", initialValue = 20)
@Data
@EqualsAndHashCode(exclude = "ticket")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    @NonNull
    Ticket ticket;

    @Column(name = "name", nullable = false)
    @NonNull
    String name;

    @Column(name = "workspace")
    String workspace;

    @Column(name = "status", nullable = false)
    @NonNull
    @Enumerated
    Status status;

    {
        status = Status.OPEN;
        workspace = StringUtils.EMPTY;
    }
}
