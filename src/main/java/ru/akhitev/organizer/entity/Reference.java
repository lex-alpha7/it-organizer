package ru.akhitev.organizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Reference")
@SequenceGenerator(name = "seq", initialValue = 20)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data @ToString(exclude = {"notes", "links", "project"})
public class Reference {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @OneToOne
    @PrimaryKeyJoinColumn
    Project project;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "reference")
    @Fetch(value = FetchMode.SUBSELECT)
    List<ReferenceLink> links;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "reference")
    @Fetch(value = FetchMode.SUBSELECT)
    List<Note> notes;

    {
        links = new ArrayList<>();
        notes = new ArrayList<>();
    }
}
