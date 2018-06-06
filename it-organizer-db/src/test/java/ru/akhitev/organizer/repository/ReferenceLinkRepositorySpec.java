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
package ru.akhitev.organizer.repository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import ru.akhitev.organizer.entity.ReferenceLink;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReferenceLinkRepositorySpec {
    @Inject
    private ReferenceLinkRepository repository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void whenFindAllThenFindAll() {
        Assertions.assertThat(repository.findAll())
                .as("Right notes count was found.").hasSize(2);
    }

    @Test
    public void whenGetExistedByIdThenReturnIt() {
        final Integer id = 0;
        ReferenceLink link = repository.getOne(id);
        assertThat(link)
                .as("When we get by existing id, then we should get not null link.").isNotNull();
        assertThat(link.getName())
                .as("When we get by existing id, then we should get link with concrete name.")
                .isEqualTo("wiki");
        assertThat(link.getLink())
                .as("When we get by existing id, then we should get link with concrete link text.")
                .isEqualTo("http://www.confluence.com");
    }

    @Test
    public void whenGetNotExistedByIdThenReturnNull() {
        final Integer id = 4;
        Assertions.assertThat(repository.getOne(id))
                .as("When we get by existing id, then we should get not null link.").isNotNull();
    }

    @Test
    public void whenRemoveExistedByIdThenItDeleted() {
        final Integer id = 1;
        repository.deleteById(id);
        exception.expect(JpaObjectRetrievalFailureException.class);
        repository.getOne(id);
    }

    @Test
    public void whenRemoveNotExistedByIdThenException() {
        final Integer id = 4;
        exception.expect(EmptyResultDataAccessException.class);
        repository.deleteById(id);
    }
}
