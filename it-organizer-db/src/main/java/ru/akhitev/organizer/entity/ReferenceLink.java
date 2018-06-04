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

import javax.persistence.*;

@Entity
@Table(name = "Reference_Link")
@SequenceGenerator(name = "seq", initialValue = 20)
@Data
@EqualsAndHashCode(exclude = "project")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReferenceLink {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @NonNull
    Project project;

    @Column(name = "name")
    String name;

    @Column(name = "link")
    @NonNull
    String link;
}
