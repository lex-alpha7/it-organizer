package ru.akhitev.organizer.repository;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import ru.akhitev.organizer.entity.Note;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class NoteRepositorySpec {

    @Inject
    private NoteRepository repository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenFindAllThenFindAll() {
        Assertions.assertThat(repository.findAll())
                .as("Right notes count was found.").hasSize(3);
    }

    @Test
    public void whenGetExistedByIdThenReturnIt() {
        final Integer id = 0;
        Note note = repository.getOne(id);
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
        Assertions.assertThat(repository.getOne(id))
                .as("When we get by existing id, then we should get not null note.").isNotNull();
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
