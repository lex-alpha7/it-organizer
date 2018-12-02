package ru.akhitev.organizer.logic.business.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhitev.organizer.db.entity.DataBaseEntity;
import ru.akhitev.organizer.db.entity.NodeDataBaseEntity;
import ru.akhitev.organizer.logic.business.converter.Converter;
import ru.akhitev.organizer.logic.business.dto.DataTransferObject;

import java.util.Collections;
import java.util.Set;

public abstract class AbstractNodeService<P extends DataBaseEntity,
        C extends Converter<E, VO, DTO>,
        R extends JpaRepository<E, Integer>,
        E extends NodeDataBaseEntity,
        VO, DTO extends DataTransferObject>
        extends AbstractService<C, R, E, VO, DTO> {
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
        return converter().prepareForShow(notes);
    }

    /**
     * The method saves DTO.
     * If it is a create operation and there is no entity, then a new one is created.
     * {@link ProjectService#activeProject} is set to project in the ticket.
     *
     * @param dto DTO, which will be saved.
     */
    public void save(DTO dto) {
        Integer id = dto.getId();
        E entity = null;
        if (id != null) {
            entity = repository().getOne(id);
        }
        entity = converter().merge(entity, dto);
        entity.setRoot(activeRoot());
        repository().save(entity);
    }

    abstract Set<E> queryEntitiesForActiveRoot();

    abstract P activeRoot();
}
