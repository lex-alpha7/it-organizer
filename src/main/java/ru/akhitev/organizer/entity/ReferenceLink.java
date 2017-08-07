package ru.akhitev.organizer.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "Reference_Link")
@SequenceGenerator(name = "seq", initialValue = 20)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data @NoArgsConstructor
public class ReferenceLink {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "reference_id", nullable = false)
    @NonNull
    Reference reference;

    @Column(name = "name")
    String name;

    @Column(name = "link")
    @NonNull
    String link;
}
