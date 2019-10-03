package ru.akhitev.organizer.web.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.akhitev.organizer.logic.business.service.ProjectService;
import ru.akhitev.organizer.logic.business.vo.project.ProjectForShow;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/project")
public class ProjectRestController {
    private final ProjectService service;

    public ProjectRestController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("/list")
    Set<ProjectForShow> getList() {
        return service.giveForShow();
    }

    @GetMapping("/activate/{projectId}")
    void activate(@PathVariable Integer projectId) {
        service.activateProject(projectId);
    }
}
