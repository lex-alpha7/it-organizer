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
package ru.akhitev.organizer.db.repository;

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
import ru.akhitev.organizer.db.entity.Project;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectRepositorySpec {

    @Inject
    private ProjectRepository projectRepository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void whenFindAllThenFindAll() {
        Assertions.assertThat(projectRepository.findAll())
                .as("Right projects count was found.").hasSize(2);
    }

    @Test
    public void whenGetExistedByIdThenReturnIt() {
        final Integer id = 0;
        Project projectEntity = projectRepository.getOne(id);
        assertThat(projectEntity)
            .as("When we get by existing id, then we should get not null project.").isNotNull();
        assertThat(projectEntity.getName())
            .as("When we get by existing id, then we should get project with concrete name.")
                .isEqualTo("pro1");
        assertThat(projectEntity.getLinks().size())
                .as("When we get by existing id, then we should get correct size of links.")
                .isEqualTo(2);
        assertThat(projectEntity.getNotes().size())
                .as("When we get by existing id, then we should get correct size of notes.")
                .isEqualTo(3);
    }

    @Test
    public void whenGetNotExistedByIdThenReturnNull() {
        final Integer id = 2;
        Assertions.assertThat(projectRepository.getOne(id))
            .as("When we get by existing id, then we should get not null project.").isNotNull();
    }

    @Test
    public void whenRemoveExistedByIdThenItDeleted() {
        final Integer id = 1;
        projectRepository.deleteById(id);
        exception.expect(JpaObjectRetrievalFailureException.class);
        projectRepository.getOne(id);
     }

    @Test
    public void whenRemoveNotExistedByIdThenException() {
        final Integer id = 2;
        exception.expect(EmptyResultDataAccessException.class);
        projectRepository.deleteById(id);
    }
}
