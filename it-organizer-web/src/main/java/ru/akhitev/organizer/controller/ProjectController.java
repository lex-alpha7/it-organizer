package ru.akhitev.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.akhitev.organizer.entity.Project;
import ru.akhitev.organizer.repository.ProjectRepository;

import java.util.Map;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {
    @Autowired
    ProjectRepository projectRepository;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProject(Model model) {
        model.addAttribute("project", new Project());
        return "fragments/project :: new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProject(@ModelAttribute Project project, BindingResult bindingResult, Model model) {
        projectRepository.save(project);
        return "redirect:/";
    }
}
