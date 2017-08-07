package ru.akhitev.organizer.repository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.akhitev.organizer.config.DBSpringTestConfig;
import ru.akhitev.organizer.entity.Reference;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBSpringTestConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReferenceRepositorySpec {

    @Inject
    ReferenceRepository repository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenFindAllThenFindAll() {
        assertThat(repository.findAll())
                .as("Wrong projects count was found.").hasSize(1);
    }

    @Test
    public void whenGetExistedByIdThenReturnIt() {
        final Integer id = 0;
        Reference referenceEntity = repository.findOne(id);
        assertThat(referenceEntity)
                .as("When we get by existing id, then we should get not null reference.").isNotNull();
        assertThat(referenceEntity.getProject().getName())
                .as("When we get by existing id, then we should get project with concrete name.")
                .isEqualTo("pro1");
        assertThat(referenceEntity.getLinks().size())
                .as("When we get by existing id, then we should get correct size of links.")
                .isEqualTo(2);
        assertThat(referenceEntity.getNotes().size())
                .as("When we get by existing id, then we should get correct size of notes.")
                .isEqualTo(3);
    }

    @Test
    public void whenGetNotExistedByIdThenReturnNull() {
        final Integer id = 2;
        Reference referenceEntity = repository.findOne(id);
        assertThat(referenceEntity)
                .as("When we get by existing id, then we should get not null reference.").isNull();
    }

    @Test
    public void whenRemoveExistedByIdThenDoNotDeleted() {
        final Integer id = 0;
        repository.delete(id);
        Reference referenceEntity = repository.findOne(id);
        assertThat(referenceEntity)
            .as("When we remove by existing id, then we shouldn't find it again.")
            .isNull();
    }

    @Test
    public void whenRemoveNotExistedByIdThenException() {
        final Integer id = 2;
        exception.expect(EmptyResultDataAccessException.class);
        repository.delete(id);
    }
}
