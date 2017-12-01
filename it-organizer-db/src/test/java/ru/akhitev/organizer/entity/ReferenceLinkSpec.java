package ru.akhitev.organizer.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReferenceLinkSpec {
    ReferenceLink link;
    final Integer id = 0;
    final String name = "The best link";
    final String linkText = "http://www.best.link.com";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        link = new ReferenceLink();
    }

    @Test
    public void whenNameWasSetThenReturnIt() {
        link.setName(name);
        assertThat(link.getName())
                .as("When a name was set, return it.")
                .isEqualTo(name);
    }

    @Test
    public void whenNameIsNullThenReturnIt() {
        link.setName(null);
        assertThat(link.getName())
                .as("When a name is null, return it.")
                .isEqualTo(null);
    }

    @Test
    public void whenLinkWasSetThenReturnIt() {
        link.setLink(linkText);
        assertThat(link.getLink())
                .as("When a link was set, return it.")
                .isEqualTo(linkText);
    }

    @Test
    public void whenLinkIsNullThenReturnNPE() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("link");
        link.setLink(null);
    }

    @Test
    public void whenIdWasSetThenReturnIt() {
        link.setId(id);
        assertThat(link.getId())
                .as("When an id was set, return it.")
                .isEqualTo(id);
    }

    @Test
    public void whenIdIsNullThenReturnIt() {
        link.setId(null);
        assertThat(link.getId())
                .as("When an id was set, return it.")
                .isEqualTo(null);
    }

    @Test
    public void whenReferenceWasSetThenReturnIt() {
        Project project = new Project();
        link.setProject(project);
        assertThat(link.getProject())
                .as("When a project was set, return it.")
                .isEqualTo(project);
    }

    @Test
    public void whenReferenceIsNullThenReturnNPE() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("project");
        link.setProject(null);
    }
}
