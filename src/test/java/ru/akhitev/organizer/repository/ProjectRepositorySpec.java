package ru.akhitev.organizer.repository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.akhitev.organizer.Application;
import ru.akhitev.organizer.entity.Project;

import javax.inject.Inject;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectRepositorySpec {

    @Inject
    ProjectRepository projectRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenFindAllThenFindAll() {
        assertThat(projectRepository.findAll())
                .as("Right projects count was found.").hasSize(2);
    }

    @Test
    public void whenGetExistedByIdThenReturnIt() {
        final Integer id = 0;
        Project projectEntity = projectRepository.findById(id).get();
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
        assertThat(projectRepository.findById(id).isPresent())
            .as("When we get by existing id, then we should get not null project.").isFalse();
    }

    @Test
    public void whenRemoveExistedByIdThenItDeleted() {
        final Integer id = 1;
        projectRepository.deleteById(id);
        assertThat(projectRepository.findById(id).isPresent())
                .as("When we remove by existing id, then we should not find it again.").isFalse();
     }

    @Test
    public void whenRemoveNotExistedByIdThenException() {
        final Integer id = 2;
        exception.expect(EmptyResultDataAccessException.class);
        projectRepository.deleteById(id);
    }
}
