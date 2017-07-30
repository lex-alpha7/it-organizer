package ru.akhitev.organizer.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Project")
@SequenceGenerator(name = "seq", initialValue = 20)
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Getter @Setter
    private Integer id;

    @Column(name = "name", nullable = false)
    @Getter @Setter
    private String name;
}
