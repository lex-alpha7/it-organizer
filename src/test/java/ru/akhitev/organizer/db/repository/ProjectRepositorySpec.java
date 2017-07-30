package ru.akhitev.organizer.db.repository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.akhitev.organizer.config.DBSpringTestConfig;
import ru.akhitev.organizer.db.entity.ProjectEntity;

import javax.inject.Inject;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBSpringTestConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProjectRepositorySpec {

    @Inject
    private ProjectRepository projectRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenFindAllThenFindAll() {
        assertThat(projectRepository.findAll())
            .as("Wrong projects count was found.").hasSize(2);
    }

    @Test
    public void whenGetExistedByIdThenReturnIt() {
        final Integer id = 0;
        ProjectEntity projectEntity = projectRepository.findOne(id);
        assertThat(projectEntity)
            .as("When we get by existing id, then we should get not null project.").isNotNull();
        assertThat(projectEntity.getName())
            .as("When we get by existing id, then we should get project with concrete name.")
                .isEqualTo("pro1");
    }

    @Test
    public void whenGetNotExistedByIdThenReturnNull() {
        final Integer id = 2;
        ProjectEntity projectEntity = projectRepository.findOne(id);
        assertThat(projectEntity)
            .as("When we get by existing id, then we should get not null project.").isNull();
    }

    @Test
    public void whenRemoveExistedByIdThenItDeleted() {
        final Integer id = 1;
        projectRepository.delete(id);
        ProjectEntity projectEntity = projectRepository.findOne(id);
        assertThat(projectEntity)
                .as("When we remove by existing id, then we should not find it again.").isNull();
    }

    @Test
    public void whenRemoveNotExistedByIdThenException() {
        final Integer id = 2;
        exception.expect(EmptyResultDataAccessException.class);
        projectRepository.delete(id);
    }
}
