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
import org.springframework.web.bind.annotation.*;
import ru.akhitev.organizer.logic.business.dto.project.ProjectForEdit;
import ru.akhitev.organizer.logic.business.service.ProjectService;

@Controller
@RequestMapping(value = "/project")
public class ProjectController extends AbstractController {
    private final ProjectService service;

    @Autowired
    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProject(Model model) {
        model.addAttribute("project", new ProjectForEdit());
        return EDIT_PROJECT_PATH;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editProject(@PathVariable("id") Integer projectId, Model model) {
        model.addAttribute("project", service.giveForEdit(projectId));
        return EDIT_PROJECT_PATH;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProject(@PathVariable("id") Integer projectId, Model model) {
        service.remove(projectId);
        return MAIN_REDIRECT_PATH;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProject(@ModelAttribute ProjectForEdit project, BindingResult bindingResult, Model model) {
        service.saveProject(project);
        return MAIN_REDIRECT_PATH;
    }

    @RequestMapping(value = "/activate/{id}", method = RequestMethod.GET)
    public String activateProject(@PathVariable("id") Integer projectId, Model model) {
        service.activateProject(projectId);
        return MAIN_REDIRECT_PATH;
    }
}
