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
package ru.akhitev.organizer.logic.business.converter;

import java.util.Collection;
import java.util.Set;

/**
 * The aim of the class is to create VO, DTO or their lists from entity. And make entity from them.
 *
 * @param <E> Entity
 * @param <VO> Value Object
 * @param <DTO> Data Transfer Object
 */
public interface Converter<E, VO, DTO> {
    /**
     * This method converts entities into VOs to show them in a list or in a navigation panel.
     *
     * @param entities could be null. it is safe.
     * @return should return emptyList if progresses are equal to null or a set of VOs
     */
    Set<VO> prepareForShow(Collection<E> entities);

    /**
     * The method prepares object for editor.
     * Data from entity is set into DTO.
     *
     * @param entity entity, which is a source for DTO.
     * @return a DTO, filled with data from an entity.
     */
    DTO prepareForEdit(E entity);

    /**
     * This method prepares an entity for saving.
     * If there is no entity (in case, it's a new one), a new entity will be created and used. In another case an existed one will be used.
     *
     * @param entity could be null. it is safe.
     * @param dataTransferObject mustn't be null. It's data will be set to entity.
     * @return full prepared entity will be returned. It'll be ready to store in a data base.
     */
    E merge(E entity, DTO dataTransferObject);
}
