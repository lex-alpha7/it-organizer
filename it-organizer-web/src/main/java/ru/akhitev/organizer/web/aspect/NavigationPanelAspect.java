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
package ru.akhitev.organizer.web.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import ru.akhitev.organizer.logic.business.service.NoteService;
import ru.akhitev.organizer.logic.business.service.ProjectService;
import ru.akhitev.organizer.logic.business.service.ReferenceLinkService;
import ru.akhitev.organizer.logic.business.service.TicketService;

@Aspect
@Configuration
public class NavigationPanelAspect {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ReferenceLinkService referenceLinkService;

    @Autowired
    private NoteService noteService;

    @Before("execution(* ru.akhitev.organizer.web.controller.*.*(..))  && args(..,model)")
    public void beforeImpl(Model model) {
        if (model == null) {
            return;
        }
        model.addAttribute("projects", projectService.giveForShow());
        model.addAttribute("tickets", ticketService.giveTicketsForShowForActiveProject());
        model.addAttribute("referenceLinks", referenceLinkService.giveReferenceLinksForShowForActiveProject());
        model.addAttribute("notes", noteService.giveNotesForShowForActiveProject());
        model.addAttribute("ifActiveProject", projectService.ifActiveProject());
        model.addAttribute("ifActiveTicket", ticketService.ifActiveTicket());
    }
}
