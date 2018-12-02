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
