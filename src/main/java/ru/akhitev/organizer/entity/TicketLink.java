package ru.akhitev.organizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.akhitev.organizer.enums.LinkType;

import javax.persistence.*;

@Entity
@Table(name = "Ticket_Link")
@SequenceGenerator(name = "seq", initialValue = 20)
@Data
@EqualsAndHashCode(exclude = "ticket")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketLink {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @ManyToOne @JoinColumn(name = "ticket_id", nullable = false)
    @NonNull
    Ticket ticket;

    @Column(name = "type", nullable = false)
    @NonNull
    @Enumerated
    LinkType type;

    @Column(name = "name")
    String name;

    @Column(name = "link", nullable = false)
    @NonNull
    String link;
}
