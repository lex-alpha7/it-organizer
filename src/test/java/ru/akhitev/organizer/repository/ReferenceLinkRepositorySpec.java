package ru.akhitev.organizer.repository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.akhitev.organizer.entity.ReferenceLink;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReferenceLinkRepositorySpec {
    @Inject
    ReferenceLinkRepository repository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenFindAllThenFindAll() {
        assertThat(repository.findAll())
                .as("Right notes count was found.").hasSize(2);
    }

    @Test
    public void whenGetExistedByIdThenReturnIt() {
        final Integer id = 0;
        ReferenceLink link = repository.findById(id).get();
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
        assertThat(repository.findById(id).isPresent())
                .as("When we get by existing id, then we should get not null link.").isFalse();
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
