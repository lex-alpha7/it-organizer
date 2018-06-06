/*
 * IT-Organizer is an organizer for a developer and other IT-specialists.
 * Copyright (c) 2017 Aleksei Khitev (Хитёв Алексей Юрьевич).
 *
 * This file is part of IT-Organizer
 *
 * IT-Organizer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * IT-Organizer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.akhitev.organizer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.akhitev.organizer.logic.business.dto.project.note.NoteForEdit;
import ru.akhitev.organizer.logic.business.service.NoteService;

@Controller
@RequestMapping(value = "/project/note/")
public class NoteController extends AbstractController {
    @Autowired
    private NoteService noteService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newNote(Model model) {
        model.addAttribute("note", new NoteForEdit());
        return EDIT_NOTE_PATH;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editNote(@PathVariable("id") Integer noteID, Model model) {
        model.addAttribute("note", noteService.giveNoteForEdit(noteID));
        return EDIT_NOTE_PATH;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNote(@ModelAttribute NoteForEdit not, BindingResult bindingResult, Model model) {
        noteService.saveNote(not);
        return MAIN_REDIRECT_PATH;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteNote(@PathVariable("id") Integer noteID, Model model) {
        noteService.removeLink(noteID);
        return MAIN_REDIRECT_PATH;
    }
}
