package ru.akhitev.organizer.manager;


import ru.akhitev.organizer.converter.ProjectConverter;
import ru.akhitev.organizer.db.entity.ProjectEntity;
import ru.akhitev.organizer.db.repository.ProjectRepository;
import ru.akhitev.organizer.dto.Project;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
public class ProjectManager {

    @Inject
    private ProjectConverter converter;

    @Inject
    private ProjectRepository repository;

    public List<Project> findAllProjects() {
        return converter.entitiesToDTOs(repository.findAll());
    }


}
