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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.akhitev.organizer.logic.business.dto.DataTransferObject;

import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractConverterSpec<E, VO, DTO extends DataTransferObject, C extends Converter<E, VO, DTO>> {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void whenEntitiesExistThenReturnValueObjects() {
        Set<VO> results = converter().prepareForShow(entitiesExists().entities);
        assertThat(results).containsAll(entitiesExists().expectedValueObjects);
    }

    @Test
    public void whenEmptyEntitiesThenReturnEmptySet() {
        Set<VO> results = converter().prepareForShow(entitiesNotExists().entities);
        assertThat(results).isEmpty();
    }

    @Test
    public void whenNullEntitiesThenReturnEmptySet() {
        Set<VO> results = converter().prepareForShow(entitiesIsNull().entities);
        assertThat(results).isEmpty();
    }

    @Test
    public void whenNullEntitiesAreInCollectionThenIgnoreNulls() {
        Set<VO> results = converter().prepareForShow(entitiesContainsNull().entities);
        assertThat(results).isEmpty();
    }

    abstract TestPairOfEntitiesAndValueObjects<E,VO> entitiesExists();

    abstract TestPairOfEntitiesAndValueObjects<E,VO> entitiesNotExists();

    abstract TestPairOfEntitiesAndValueObjects<E,VO> entitiesIsNull();

    abstract TestPairOfEntitiesAndValueObjects<E,VO> entitiesContainsNull();

    @Test
    public void whenEntityExistsThenReturnDataTransferObject() {
        DTO result = converter().prepareForEdit(entityExist().entity);
        assertThat(result).isEqualTo(entityExist().expectedDataTransferObjectObject);
    }

    @Test
    public void whenEntityIsNotExistsThenThrowIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        converter().prepareForEdit(entityNotExist().entity);
    }

    abstract TestPairOfEntityAndDataTransferObject<E, DTO> entityExist();

    abstract TestPairOfEntityAndDataTransferObject<E, DTO> entityNotExist();

    @Test
    public void whenEntityNotExistsAndDataTransferObjectExistsThenReturnNewEntity() {
        E result = converter().merge(newEntityAndNotNullDTO().entityBefore, newEntityAndNotNullDTO().dataTransferObject);
        assertThat(result).isEqualTo(newEntityAndNotNullDTO().entityAfter);
    }

    @Test
    public void whenEntityNotExistsAndDataTransferObjectIsNullThenThrowIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        converter().merge(newEntityAndNullDTO().entityBefore, newEntityAndNullDTO().dataTransferObject);
    }

    @Test
    public void whenEntityExistsAndDataTransferObjectExistsThenReturnUpdatedEntity() {
        E result = converter().merge(existedEntityAndNotNullDTO().entityBefore, existedEntityAndNotNullDTO().dataTransferObject);
        assertThat(result).isEqualTo(existedEntityAndNotNullDTO().entityAfter);
    }

    @Test
    public void whenEntityExistsAndDataTransferObjectIsNullThenThrowIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        converter().merge(existedEntityAndNullDTO().entityBefore, existedEntityAndNullDTO().dataTransferObject);
    }

    abstract TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<E, DTO> newEntityAndNotNullDTO();

    abstract TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<E, DTO> newEntityAndNullDTO();

    abstract TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<E, DTO> existedEntityAndNotNullDTO();

    abstract TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<E, DTO> existedEntityAndNullDTO();

    abstract C converter();
    
    static class TestPairOfEntitiesAndValueObjects<E,VO> {
        final Collection<E> entities;
        final Set<VO> expectedValueObjects;

        public TestPairOfEntitiesAndValueObjects(Collection<E> entities, Set<VO> expectedValueObjects) {
            this.entities = entities;
            this.expectedValueObjects = expectedValueObjects;
        }
    }

    static class TestPairOfEntityAndDataTransferObject<E,DTO> {
        final E entity;
        final DTO expectedDataTransferObjectObject;

        public TestPairOfEntityAndDataTransferObject(E entity, DTO expectedDataTransferObjectObject) {
            this.entity = entity;
            this.expectedDataTransferObjectObject = expectedDataTransferObjectObject;
        }
    }

    static class TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter<E,DTO> {
        final E entityBefore;
        final DTO dataTransferObject;
        final E entityAfter;

        public TestPairOfDataTransferObjectAndEntitiesBeforeAndAfter(E entityBefore, DTO dataTransferObject, E entityAfter) {
            this.entityBefore = entityBefore;
            this.dataTransferObject = dataTransferObject;
            this.entityAfter = entityAfter;
        }
    }
}
