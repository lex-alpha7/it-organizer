package ru.akhitev.organizer.web.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.akhitev.organizer.logic.business.dto.ticket.progress.ProgressForEdit;
import ru.akhitev.organizer.logic.business.service.ProgressService;
import ru.akhitev.organizer.logic.business.vo.ticket.progress.ProgressForShow;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/rest/progress")
public class ProgressRestController {
    private ProgressService service;

    @Autowired
    public ProgressRestController(ProgressService service) {
        this.service = service;
    }

    @GetMapping("/list")
    Set<ProgressForShow> getList() {
        return service.giveForShowForActiveRoot();
    }

    @GetMapping("/edit/{progressId}")
    ProgressForEdit edit(@PathVariable Integer progressId) {
        return service.giveForEdit(progressId);
    }

    @PutMapping("/save")
    void save(@RequestBody ProgressForEdit progress) {
        service.save(progress);
    }

    @DeleteMapping("/delete/{progressId}")
    void delete(@PathVariable Integer progressId) {
        service.remove(progressId);
    }
}
