package ru.akhitev.organizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.akhitev.organizer.enums.LinkType;

import javax.persistence.*;

@Entity
@Table(name = "Ticket_Link")
@SequenceGenerator(name = "seq", initialValue = 20)
@EqualsAndHashCode(exclude = "ticket")
public class TicketLink {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @ManyToOne @JoinColumn(name = "ticket_id", nullable = false)
    @NonNull
    private Ticket ticket;

    @Column(name = "type", nullable = false)
    @NonNull
    @Enumerated
    private LinkType type;

    @Column(name = "name")
    private String name;

    @Column(name = "link", nullable = false)
    @NonNull
    private String link;

    public TicketLink() {
    }

    public TicketLink(Integer id, Ticket ticket, LinkType type, String name, String link) {
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
