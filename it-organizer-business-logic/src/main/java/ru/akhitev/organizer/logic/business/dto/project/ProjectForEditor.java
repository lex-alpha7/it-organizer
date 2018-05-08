package ru.akhitev.organizer.logic.business.dto.project;

import ru.akhitev.organizer.logic.business.dto.ticket.TicketForList;

import java.util.Set;

public class ProjectForEditor {
    private Integer id;
    private String name;
    private Set<TicketForList> tickets;

    public ProjectForEditor(Integer id, String name, Set<TicketForList> tickets) {
        this.id = id;
        this.name = name;
        this.tickets = tickets;
    }


}
