package ru.akhitev.organizer.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "Note")
@SequenceGenerator(name = "seq", initialValue = 20)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "reference_id", nullable = false)
    Reference reference;

    @Column(name = "title")
    String title;

    @Column(name = "note")
    String note;
}
