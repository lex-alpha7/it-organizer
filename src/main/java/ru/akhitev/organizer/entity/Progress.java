package ru.akhitev.organizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Progress")
@SequenceGenerator(name = "seq", initialValue = 20)
@Data
@EqualsAndHashCode(exclude = "ticket")
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

    @Column(name = "status_date", nullable = false)
    @NonNull
    Date date;

    @Column(name = "status", nullable = false)
    @NonNull
    String status;
}
