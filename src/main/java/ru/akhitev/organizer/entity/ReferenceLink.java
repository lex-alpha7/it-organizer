package ru.akhitev.organizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "Reference_Link")
@SequenceGenerator(name = "seq", initialValue = 20)
@Data
@EqualsAndHashCode(exclude = "project")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReferenceLink {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @NonNull
    Project project;

    @Column(name = "name")
    String name;

    @Column(name = "link")
    @NonNull
    String link;
}
