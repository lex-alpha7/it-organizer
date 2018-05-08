package ru.akhitev.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.akhitev.organizer.logic.business.dto.project.ProjectForEditor;
import ru.akhitev.organizer.logic.business.dto.project.ProjectForList;
import ru.akhitev.organizer.logic.business.service.ProjectService;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newProject(Model model) {
        model.addAttribute("project", new ProjectForEditor());
        return "edit_project";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editProject(@PathVariable("id") Integer projectId, Model model) {
        model.addAttribute("project", service.giveProjectForEdit(projectId, 20));
        return "edit_project";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProject(@PathVariable("id") Integer projectId, Model model) {
        service.removeProject(projectId);
        return "redirect:/";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProject(@ModelAttribute ProjectForEditor project, BindingResult bindingResult, Model model) {
        service.saveProject(project);
        return "redirect:/";
    }
}
