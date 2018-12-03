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
import ru.akhitev.organizer.logic.business.converter.Converter;

import java.util.Set;

/**
 * The aim of service is to provide give, remove DTOs and VOs.
 * A service uses converters, repositories.
 * No one else should use data base layer.
 *
 * @param <C> Converter
 * @param <R> Repository
 * @param <E> Entity
 * @param <VO> Value Object, usually is named ...ForShow
 * @param <DTO> Data Transfer Object, usually is named ...ForEdit
 */
public abstract class AbstractService<C extends Converter<E, VO, DTO>, R extends JpaRepository<E, Integer>, E, VO, DTO> {
    /** The main converter. */
    protected C converter;

    /** The main repository. */
    R repository;

    AbstractService(C converter, R repository) {
        this.converter = converter;
        this.repository = repository;
    }

    /**
     * Returns collection of VOs.
     *
     * @return collection of VOs.
     */
    public Set<VO> giveForShow() {
        return converter.prepareForShow(repository.findAll());
    }

    /**
     * The method prepares DTO from entity, get by ID.
     *
     * @param id ID to find entity in data base.
     * @return DTO from entity.
     */
    public DTO giveForEdit(Integer id) {
        return converter.prepareForEdit(repository.getOne(id));
    }

    /**
     * The method removes found by id entity in data base.
     * @param id ID to find entity in data base.
     */
    public void remove(Integer id) {
        repository.deleteById(id);
    }


}
