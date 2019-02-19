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
package ru.akhitev.organizer.logic.business.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.organizer.db.entity.DataBaseEntity;
import ru.akhitev.organizer.db.entity.NodeDataBaseEntity;
import ru.akhitev.organizer.logic.business.converter.Converter;
import ru.akhitev.organizer.logic.business.dto.DataTransferObject;

import java.util.Collections;
import java.util.Set;

/**
 * {@inheritDoc}
 *
 * In addition to {@link AbstractService}, it provides save functionality.
 *
 * @param <P> Parent class (for instance, project or ticket)
 * @param <C> Converter
 * @param <R> Repository
 * @param <E> Entity
 * @param <VO> Value Object, usually is named ...ForShow
 * @param <DTO> Data Transfer Object, usually is named ...ForEdit
 */
public abstract class AbstractNodeService<P extends DataBaseEntity,
        C extends Converter<E, VO, DTO>,
        R extends JpaRepository<E, Integer>,
        E extends NodeDataBaseEntity,
        VO, DTO extends DataTransferObject>
        extends AbstractService<C, R, E, VO, DTO> {

    AbstractNodeService(C converter, R repository) {
        super(converter, repository);
    }

    /**
     * Returns collection of VOs.
     * If there is no active root, then empty set is returned.
     *
     * @return collection of VOs.
     */
    public Set<VO> giveForShowForActiveRoot() {
        if (activeRoot() == null) {
            return Collections.emptySet();
        }
        Set<E> notes = queryEntitiesForActiveRoot();
        return converter.prepareForShow(notes);
    }

    /**
     * The method saves DTO.
     * If it is a create operation and there is no entity, then a new one is created.
     * Root object is set in the entity.
     *
     * @param dto DTO, which will be saved.
     */
    public void save(DTO dto) {
        Integer id = dto.getId();
        E entity = null;
        if (id != null) {
            entity = repository.getOne(id);
        }
        entity = converter.merge(entity, dto);
        entity.setRoot(activeRoot());
        entity = repository.save(entity);
        dto.setId(entity.getId());
    }

    /**
     * @return set of entities for some root object from data base.
     */
    abstract Set<E> queryEntitiesForActiveRoot();

    /**
     * @return activated in UI root object (project, ticket or others).
     */
    abstract P activeRoot();
}
