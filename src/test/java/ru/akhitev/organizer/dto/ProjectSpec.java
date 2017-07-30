package ru.akhitev.organizer.dto;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectSpec {
    private Project project;
    private String projectName = "Test Project";
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        project = new Project(projectName);
    }

    @Test
    public void whenNameWasSetThenReturnIt() {

        assertThat(project.getName())
                .as("WHen a name was set, return it.")
                .isEqualTo(projectName);
    }

    @Test
    public void whenNullNameThanIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Name mustn't be null or empty");
        project = new Project(null);
    }

    @Test
    public void whenEmptyNamThanIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Name mustn't be null or empty");
        project = new Project("");
    }
}
