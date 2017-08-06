package ru.akhitev.organizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "Project")
@SequenceGenerator(name = "seq", initialValue = 20)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data @NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @Column(name = "name", nullable = false)
    @NonNull
    String name;

    @OneToOne(orphanRemoval = true, mappedBy = "project")
    Reference reference;
}
