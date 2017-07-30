package ru.akhitev.organizer.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.akhitev.organizer.config.DBSpringTestConfig;
import ru.akhitev.organizer.dto.Project;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBSpringTestConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProjectManagerSpec {
    @Inject
    ProjectManager manager;

    @Test
    public void whenFindAllProjectsThenGetThem() {
        List<Project> projects = manager.findAllProjects();
        assertThat(projects)
                .as("When find projects then not null list").isNotNull()
                .as("When find projects then list with size like at the DB").hasSize(2);
        assertThat(projects.stream().map(Project::getName))
                .as("When find projects then list must contain project from DB 1").contains("pro1")
                .as("When find projects then list must contain project from DB 2").contains("pro2");
    }
}
