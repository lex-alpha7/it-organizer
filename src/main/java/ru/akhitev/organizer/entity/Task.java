package ru.akhitev.organizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import ru.akhitev.organizer.enums.Status;

import javax.persistence.*;

@Entity
@Table(name = "Task")
@SequenceGenerator(name = "seq", initialValue = 20)
@Data
@EqualsAndHashCode(exclude = "ticket")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    @NonNull
    Ticket ticket;

    @Column(name = "name", nullable = false)
    @NonNull
    String name;

    @Column(name = "workspace")
    String workspace;

    @Column(name = "status", nullable = false)
    @NonNull
    @Enumerated
    Status status;

    {
        status = Status.OPEN;
        workspace = StringUtils.EMPTY;
    }
}
