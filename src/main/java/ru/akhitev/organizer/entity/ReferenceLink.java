package ru.akhitev.organizer.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "Reference_Link")
@SequenceGenerator(name = "seq", initialValue = 20)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ReferenceLink {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "reference_id", nullable = false)
    Reference reference;

    @Column(name = "name")
    String name;

    @Column(name = "link")
    String link;
}
