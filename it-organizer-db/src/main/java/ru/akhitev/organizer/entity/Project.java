package ru.akhitev.organizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Project")
@SequenceGenerator(name = "seq", initialValue = 20)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "project")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Ticket> tickets;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "project")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<ReferenceLink> links;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "project")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Note> notes;

    {
        tickets = new LinkedHashSet<>();
        links = new LinkedHashSet<>();
        notes = new LinkedHashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<ReferenceLink> getLinks() {
        return links;
    }

    public void setLinks(Set<ReferenceLink> links) {
        this.links = links;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tickets=" + tickets +
                ", links=" + links +
                ", notes=" + notes +
                '}';
    }
}
