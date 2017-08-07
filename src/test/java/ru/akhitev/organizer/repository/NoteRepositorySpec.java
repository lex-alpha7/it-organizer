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
import ru.akhitev.organizer.entity.Note;
import ru.akhitev.organizer.entity.Project;
import ru.akhitev.organizer.entity.Reference;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBSpringTestConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NoteRepositorySpec {

    @Inject
    NoteRepository repository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenFindAllThenFindAll() {
        assertThat(repository.findAll())
                .as("Right notes count was found.").hasSize(3);
    }

    @Test
    public void whenGetExistedByIdThenReturnIt() {
        final Integer id = 0;
        Note note = repository.findOne(id);
        assertThat(note)
                .as("When we get by existing id, then we should get not null note.").isNotNull();
        assertThat(note.getTitle())
                .as("When we get by existing id, then we should get note with concrete title.")
                .isEqualTo("Environment set up");
        assertThat(note.getNote())
                .as("When we get by existing id, then we should get note with concrete note text.")
                .isEqualTo("You need to install an plugin.");
        assertThat(note.getReference().getId())
                .as("When we get by existing id, then we should get reference for which the note is connected.")
                .isEqualTo(0);
    }

    @Test
    public void whenGetNotExistedByIdThenReturnNull() {
        final Integer id = 4;
        Note note = repository.findOne(id);
        assertThat(note)
                .as("When we get by existing id, then we should get not null note.").isNull();
    }

    @Test
    public void whenRemoveExistedByIdThenItDeleted() {
        final Integer id = 1;
        repository.delete(id);
        Note note = repository.findOne(id);
        assertThat(note)
                .as("When we remove by existing id, then we should not find it again.").isNull();
    }

    @Test
    public void whenRemoveNotExistedByIdThenException() {
        final Integer id = 4;
        exception.expect(EmptyResultDataAccessException.class);
        repository.delete(id);
    }
}
