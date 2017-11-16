package ru.akhitev.organizer.repository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import ru.akhitev.organizer.entity.Note;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
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
        Note note = repository.findById(id).get();
        assertThat(note)
                .as("When we get by existing id, then we should get not null note.").isNotNull();
        assertThat(note.getTitle())
                .as("When we get by existing id, then we should get note with concrete title.")
                .isEqualTo("Environment set up");
        assertThat(note.getNote())
                .as("When we get by existing id, then we should get note with concrete note text.")
                .isEqualTo("You need to install an plugin.");
    }

    @Test
    public void whenGetNotExistedByIdThenReturnNull() {
        final Integer id = 4;
        assertThat(repository.findById(id).isPresent())
                .as("When we get by existing id, then we should get not null note.").isFalse();
    }

    @Test
    public void whenRemoveExistedByIdThenItDeleted() {
        final Integer id = 1;
        repository.deleteById(id);
        assertThat(repository.findById(id).isPresent())
                .as("When we remove by existing id, then we should not find it again.").isFalse();
    }

    @Test
    public void whenRemoveNotExistedByIdThenException() {
        final Integer id = 4;
        exception.expect(EmptyResultDataAccessException.class);
        repository.deleteById(id);
    }
}
