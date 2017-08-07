package ru.akhitev.organizer.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Progress")
@SequenceGenerator(name = "seq", initialValue = 20)
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    @NonNull
    Ticket ticket;

    @Column(name = "date", nullable = false)
    @NonNull
    Date date;

    @Column(name = "status", nullable = false)
    @NonNull
    String status;
}
