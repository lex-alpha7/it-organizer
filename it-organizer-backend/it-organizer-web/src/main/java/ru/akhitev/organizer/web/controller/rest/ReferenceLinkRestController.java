package ru.akhitev.organizer.web.controller.rest;

import org.springframework.web.bind.annotation.*;
import ru.akhitev.organizer.logic.business.dto.project.link.ReferenceLinkForEdit;
import ru.akhitev.organizer.logic.business.service.ReferenceLinkService;
import ru.akhitev.organizer.logic.business.vo.project.link.ReferenceLinkForShow;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/rest/reference_link")
public class ReferenceLinkRestController {
    private final ReferenceLinkService service;

    public ReferenceLinkRestController(ReferenceLinkService service) {
        this.service = service;
    }

    @GetMapping("/list")
    Set<ReferenceLinkForShow> getList() {
        return service.giveForShowForActiveRoot();
    }

    @GetMapping("/edit/{referenceLinkId}")
    ReferenceLinkForEdit edit(@PathVariable Integer referenceLinkId) {
        return service.giveForEdit(referenceLinkId);
    }

    @PutMapping("/save")
    void save(@RequestBody ReferenceLinkForEdit note) {
        service.save(note);
    }

    @DeleteMapping("/delete/{referenceLinkId}")
    void delete(@PathVariable Integer referenceLinkId) {
        service.remove(referenceLinkId);
    }
}
