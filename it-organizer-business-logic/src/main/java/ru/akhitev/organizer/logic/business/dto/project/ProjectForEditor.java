package ru.akhitev.organizer.logic.business.dto.project;

import ru.akhitev.organizer.logic.business.dto.ticket.TicketForList;

import java.util.Set;

public class ProjectForEditor {
    private Integer id;
    private String name;
    private Set<TicketForList> tickets;

    public ProjectForEditor() {
    }

    public ProjectForEditor(Integer id, String name, Set<TicketForList> tickets) {
        this.id = id;
        this.name = name;
        this.tickets = tickets;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TicketForList> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketForList> tickets) {
        this.tickets = tickets;
    }
}
