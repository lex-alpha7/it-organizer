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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.akhitev.organizer.db.entity.Project;
import ru.akhitev.organizer.db.entity.ReferenceLink;
import ru.akhitev.organizer.logic.business.dto.project.link.ReferenceLinkForEdit;
import ru.akhitev.organizer.logic.business.vo.project.link.ReferenceLinkForShow;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/** {@inheritDoc} */
@Component
public class ReferenceLinkConverter implements Converter<ReferenceLink, ReferenceLinkForShow, ReferenceLinkForEdit> {

    /** size for adjustment too long names */
    @Value("${name.size}")
    private Integer nameSize;

    /** {@inheritDoc} */
    @Override
    public Set<ReferenceLinkForShow> prepareForShow(Collection<ReferenceLink> links) {
        if (links == null) {
            return Collections.emptySet();
        }
        return links.stream()
                .map( link ->
                        new ReferenceLinkForShow(link.getId(),
                                link.getName(),
                                link.getLink(),
                                nameSize))
                .collect(Collectors.toSet());
    }

    /** {@inheritDoc} */
    @Override
    public ReferenceLinkForEdit prepareForEdit(ReferenceLink link) {
        return new ReferenceLinkForEdit(link.getId(), link.getName(), link.getLink());
    }

    /** {@inheritDoc} */
    @Override
    public ReferenceLink merge(ReferenceLink link, ReferenceLinkForEdit linkForEditor) {
        if (link == null) {
            link = new ReferenceLink();
        }
        link.setName(linkForEditor.getName());
        link.setLink(linkForEditor.getLink());
        return link;
    }
}
