package ru.akhitev.organizer.web.controller.rest;


import org.springframework.web.bind.annotation.*;
import ru.akhitev.organizer.logic.business.dto.project.ProjectForEdit;
import ru.akhitev.organizer.logic.business.service.ProjectService;
import ru.akhitev.organizer.logic.business.vo.project.ProjectForShow;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
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

    @GetMapping("/edit/{projectId}")
    ProjectForEdit edit(@PathVariable Integer projectId) {
        return service.giveForEdit(projectId);
    }

    @PutMapping("/save")
    void save(@RequestBody ProjectForEdit project) {
        service.saveProject(project);
    }

    @DeleteMapping("/delete/{projectId}")
    void delete(@PathVariable Integer projectId) {
        service.remove(projectId);
    }
}
