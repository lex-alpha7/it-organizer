package ru.akhitev.organizer.logic.business.service.project;

import org.springframework.beans.factory.annotation.Autowired;
import ru.akhitev.organizer.logic.business.converter.ProjectConverter;
import ru.akhitev.organizer.logic.business.dto.project.ProjectForList;
import ru.akhitev.organizer.repository.ProjectRepository;

import java.util.Set;

public class ProjectService {
    @Autowired
    private ProjectRepository repository;

    @Autowired
    private ProjectConverter converter;

    public Set<ProjectForList> giveProjectsForList(Integer nameSize) {
        return converter.convertFromProjectsToProjectsForList(repository.findAll(), nameSize);
    }

}
