package ru.akhitev.organizer.logic.business.dto.ticket.link;

import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.enums.LinkType;

public class TicketLinkForEditor {
    private Integer id;
    private Ticket ticket;
    private LinkType type;
    private String name;
    private String link;

    public TicketLinkForEditor() {
    }

    public TicketLinkForEditor(Integer id, Ticket ticket, LinkType type, String name, String link) {
        this.id = id;
        this.ticket = ticket;
        this.type = type;
        this.name = name;
        this.link = link;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public LinkType getType() {
        return type;
    }

    public void setType(LinkType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
