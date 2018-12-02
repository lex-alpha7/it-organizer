package ru.akhitev.organizer.logic.business.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.organizer.logic.business.converter.Converter;

import java.util.Set;

public abstract class AbstractService<C extends Converter<E, VO, DTO>, R extends JpaRepository<E, Integer>, E, VO, DTO> {

    /**
     * Returns collection of VOs.
     *
     * @return collection of VOs.
     */
    public Set<VO> giveForShow() {
        return converter().prepareForShow(repository().findAll());
    }

    /**
     * The method prepares DTO from entity, get by ID.
     *
     * @param id ID to find entity in data base.
     * @return DTO from entity.
     */
    public DTO giveForEdit(Integer id) {
        return converter().prepareForEdit(repository().getOne(id));
    }

    /**
     * The method removes found by id entity in data base.
     * @param id ID to find entity in data base.
     */
    public void remove(Integer id) {
        repository().deleteById(id);
    }

    abstract C converter();

    abstract R repository();
}
