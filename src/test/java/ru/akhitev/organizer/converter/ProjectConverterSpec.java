package ru.akhitev.organizer.converter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.akhitev.organizer.dto.Project;
import ru.akhitev.organizer.db.entity.ProjectEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectConverterSpec {
    private ProjectConverter converter;
    private List<ProjectEntity> entities;
    private List<Project> projects;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void prepareSpec() {
        converter = new ProjectConverter();
        entities = prepareEntities();
        projects = prepareProjects();
    }

    @Test
    public void whenConvertEntityThenReturnDTO() {
        ProjectEntity entity = new ProjectEntity();
        entity.setId(1);
        entity.setName("some project");
        Project dto = converter.entityToDTO(entity);
        assertThat(dto)
                .as("When convert entity then not null dto").isNotNull();
        assertThat(dto.getName())
                .as("When convert entity then the same name in dto").isEqualTo(entity.getName());
    }

    @Test
    public void whenConvertEntityWithNullNameThenException() {
        ProjectEntity entity = new ProjectEntity();
        entity.setId(1);
        exception.expect(IllegalArgumentException.class);
        Project dto = converter.entityToDTO(entity);
    }

    @Test
    public void whenConvertEntityWithEmptyNameThenException() {
        ProjectEntity entity = new ProjectEntity();
        entity.setId(1);
        entity.setName("");
        exception.expect(IllegalArgumentException.class);
        Project dto = converter.entityToDTO(entity);
    }

    @Test
    public void whenConvertDtoThenReturnEntity() {
        Project dto = new Project("some project");
        ProjectEntity entity = converter.dtoToEntity(dto);
        assertThat(entity)
                .as("When convert dto then not null entity").isNotNull();
        assertThat(entity.getName())
                .as("When convert dto then the same name in entity").isEqualTo(dto.getName());
    }

    @Test
    public void whenConvertEntitiesThenReturnProjets() {
        List<Project> projects = converter.entitiesToDTOs(entities);
        assertThat(projects)
            .as("When convert not null entities then not null DTOs").isNotNull()
            .as("When convert not null entities then DTOs with size like at the entities").hasSize(entities.size());
        assertThat(projects.stream().map(Project::getName))
            .as("When convert not null entities then DTOs must contain entity 1").contains(entities.get(0).getName())
            .as("When convert not null entities then DTOs must contain entity 2").contains(entities.get(1).getName());
    }

    @Test
    public void whenConvertNullEntitiesThenReturnProjets() {
        List<Project> projects = converter.entitiesToDTOs(null);
        assertThat(projects)
                .as("When convert null entities then not null DTOs").isNotNull()
                .as("When convert null entities then empty list DTOs").isEqualTo(Collections.emptyList());
    }

    @Test
    public void whenConvertEmptyEntitiesThenReturnProjets() {
        List<Project> projects = converter.entitiesToDTOs(Collections.emptyList());
        assertThat(projects)
                .as("When convert empty entities then not null DTOs").isNotNull()
                .as("When convert empty entities then empty list DTOs").isEqualTo(Collections.emptyList());
    }

    @Test
    public void whenConvertDTOsThenReturnProjectEntities() {
        List<ProjectEntity> entities = converter.dtosToEntities(projects);
        assertThat(entities)
                .as("When convert not null DTOs then not null entities").isNotNull()
                .as("When convert not null DTOs then entities with size like at the DTOs").hasSize(entities.size());
        assertThat(entities.stream().map(ProjectEntity::getName))
                .as("When convert not null DTOs then entities must contain DTO 1").contains(projects.get(0).getName())
                .as("When convert not null DTOs then entities must contain DTO 2").contains(projects.get(1).getName());
    }

    @Test
    public void whenConvertNullDTOsThenReturnEmptyProjectEntities() {
        List<ProjectEntity> entities = converter.dtosToEntities(null);
        assertThat(entities)
                .as("When convert null DTOs then not null entities").isNotNull()
                .as("When convert null DTOs then empty list entities").isEqualTo(Collections.emptyList());
    }

    @Test
    public void whenConvertEmptyDTOsThenReturnEmptyProjectEntities() {
        List<ProjectEntity> entities = converter.dtosToEntities(Collections.emptyList());
        assertThat(entities)
                .as("When convert empty DTOs then not null entities").isNotNull()
                .as("When convert empty DTOs then empty list entities").isEqualTo(Collections.emptyList());
    }

    private List<ProjectEntity> prepareEntities() {
        List<ProjectEntity> entities = new ArrayList<>();
        ProjectEntity entity1 = new ProjectEntity();
        entity1.setId(0);
        entity1.setName("proj1");
        entities.add(entity1);
        ProjectEntity entity2 = new ProjectEntity();
        entity2.setId(1);
        entity2.setName("proj2");
        entities.add(entity2);
        return entities;
    }

    private List<Project> prepareProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("proj1"));
        projects.add(new Project("proj2"));
        return projects;
    }
}
