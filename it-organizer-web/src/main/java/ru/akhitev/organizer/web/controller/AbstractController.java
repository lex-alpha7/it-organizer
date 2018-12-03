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

abstract class AbstractController {
    final String MAIN_PATH = "main";
    final String MAIN_REDIRECT_PATH = "redirect:/";
    final String EDIT_PROJECT_PATH = "edit_project";
    final String EDIT_NOTE_PATH = "edit_note";
    final String EDIT_REFERENCE_LINK_PATH = "edit_reference_link";
    final String EDIT_TICKET_PATH = "edit_ticket";
    final String EDIT_TICKET_WITH_ID_PATH_TEMPLATE = "redirect:/ticket/edit/%s";
    final String EDIT_TICKET_LINK_PATH = "edit_ticket_link";
}
