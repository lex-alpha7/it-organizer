package ru.akhitev.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.akhitev.organizer.repository.ProjectRepository;

import java.util.Map;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {
    @Autowired
    ProjectRepository projectRepository;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProject(Model model) {
        return "fragments/project :: new";
    }
}
