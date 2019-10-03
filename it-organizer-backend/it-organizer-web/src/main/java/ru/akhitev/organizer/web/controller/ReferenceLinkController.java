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
import ru.akhitev.organizer.logic.business.dto.project.link.ReferenceLinkForEdit;
import ru.akhitev.organizer.logic.business.service.ReferenceLinkService;

@Controller
@RequestMapping(value = "/project/reference_link/")
public class ReferenceLinkController extends AbstractController {
    private final ReferenceLinkService linkService;

    @Autowired
    public ReferenceLinkController(ReferenceLinkService linkService) {
        this.linkService = linkService;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLink(Model model) {
        model.addAttribute("referenceLink", new ReferenceLinkForEdit());
        return EDIT_REFERENCE_LINK_PATH;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editLink(@PathVariable("id") Integer linkID, Model model) {
        model.addAttribute("referenceLink", linkService.giveForEdit(linkID));
        return EDIT_REFERENCE_LINK_PATH;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveLink(@ModelAttribute ReferenceLinkForEdit link, BindingResult bindingResult, Model model) {
        linkService.save(link);
        return MAIN_REDIRECT_PATH;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteLinkt(@PathVariable("id") Integer linkId, Model model) {
        linkService.remove(linkId);
        return MAIN_REDIRECT_PATH;
    }
}
