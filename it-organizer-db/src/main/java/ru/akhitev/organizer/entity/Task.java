package ru.akhitev.organizer.entity;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import ru.akhitev.organizer.enums.Status;

import javax.persistence.*;

@Entity
@Table(name = "Task")
@SequenceGenerator(name = "seq", initialValue = 20)
@EqualsAndHashCode(exclude = "ticket")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "workspace")
    private String workspace;

    @Column(name = "status", nullable = false)
    @Enumerated
    private Status status;

    {
        status = Status.OPEN;
        workspace = StringUtils.EMPTY;
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

    public void setTicket(@NonNull Ticket ticket) {
        this.ticket = ticket;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(@NonNull Status status) {
        this.status = status;
    }
}
