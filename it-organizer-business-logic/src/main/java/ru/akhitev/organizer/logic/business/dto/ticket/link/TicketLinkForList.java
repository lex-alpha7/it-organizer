package ru.akhitev.organizer.logic.business.dto.ticket.link;

import ru.akhitev.organizer.entity.Ticket;
import ru.akhitev.organizer.enums.LinkType;

public class TicketLinkForList {
    private Integer id;
    private LinkType type;
    private String name;
    private String link;
    private String displayName;

    public TicketLinkForList(Integer id, LinkType type, String name, String link) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.link = link;
        this.displayName = String.format("%s (%s)", name, type);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
