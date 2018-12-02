package ru.akhitev.organizer.logic.business.converter;

import java.util.Collection;
import java.util.Set;

public interface Converter<E, VO, DTO> {
    Set<VO> prepareForShow(Collection<E> entities);
    DTO prepareForEdit(E entity);
    E merge(E entity, DTO dataTransferObject);
}
