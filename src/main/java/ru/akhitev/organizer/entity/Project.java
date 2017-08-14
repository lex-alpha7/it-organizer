package ru.akhitev.organizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Project")
@SequenceGenerator(name = "seq", initialValue = 20)
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @Column(name = "name", nullable = false)
    @NonNull
    String name;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "project")
    @Fetch(value = FetchMode.SUBSELECT)
    Set<Ticket> tickets;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "project")
    @Fetch(value = FetchMode.SUBSELECT)
    Set<ReferenceLink> links;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "project")
    @Fetch(value = FetchMode.SUBSELECT)
    Set<Note> notes;

    {
        tickets = new LinkedHashSet<>();
        links = new LinkedHashSet<>();
        notes = new LinkedHashSet<>();
    }
}
