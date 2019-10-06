package ru.akhitev.organizer.web.controller.rest;

import org.springframework.web.bind.annotation.*;
import ru.akhitev.organizer.logic.business.dto.project.note.NoteForEdit;
import ru.akhitev.organizer.logic.business.service.NoteService;
import ru.akhitev.organizer.logic.business.vo.project.note.NoteForShow;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/rest/note")
public class NoteRestController {
    private  final NoteService service;

    public NoteRestController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/list")
    Set<NoteForShow> getList() {
        return service.giveForShowForActiveRoot();
    }

    @GetMapping("/edit/{noteId}")
    NoteForEdit edit(@PathVariable Integer noteId) {
        return service.giveForEdit(noteId);
    }

    @PutMapping("/save")
    void save(@RequestBody NoteForEdit note) {
        service.save(note);
    }

    @DeleteMapping("/delete/{noteId}")
    void delete(@PathVariable Integer noteId) {
        service.remove(noteId);
    }
}
