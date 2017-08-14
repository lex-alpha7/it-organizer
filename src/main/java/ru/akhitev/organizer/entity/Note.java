package ru.akhitev.organizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "note")
@SequenceGenerator(name = "seq", initialValue = 20)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@EqualsAndHashCode(exclude = "project")
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @NonNull
    Project project;

    @Column(name = "title")
    String title;

    @Column(name = "note")
    @NonNull
    String note;
}
