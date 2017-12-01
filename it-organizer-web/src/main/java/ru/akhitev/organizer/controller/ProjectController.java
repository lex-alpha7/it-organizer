package ru.akhitev.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.akhitev.organizer.repository.ProjectRepository;

import java.util.Map;

@Controller
public class ProjectController {
    @Autowired
    ProjectRepository projectRepository;

    @RequestMapping("/project")
    public String welcome(@RequestParam(value="name", required=false, defaultValue="AirTransat") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("projects", projectRepository.findAll());
        return "project";
    }
}
