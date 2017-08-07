package ru.akhitev.organizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

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

    @OneToOne(orphanRemoval = true, mappedBy = "project")
    Reference reference;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "project")
    @Fetch(value = FetchMode.SUBSELECT)
    List<Ticket> tickets;
}
