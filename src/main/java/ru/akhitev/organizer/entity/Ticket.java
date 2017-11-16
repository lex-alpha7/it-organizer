package ru.akhitev.organizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.akhitev.organizer.enums.Status;

import javax.persistence.*;
import java.util.*;

@Entity @Table(name = "Ticket")
@SequenceGenerator(name = "seq", initialValue = 20)
@Data
@EqualsAndHashCode(exclude = "project")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @ManyToOne @JoinColumn(name = "project_id", nullable = false)
    @NonNull
    Project project;

    @Column(name = "ticket_key", nullable = false)
    @NonNull
    String key;

    @Column(name = "name")
    String name;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ticket")
    @Fetch(value = FetchMode.SUBSELECT)
    Set<TicketLink> links;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ticket")
    @Fetch(value = FetchMode.SUBSELECT)
    Set<Progress> progress;

    @Column(name = "workspace")
    String workspace;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ticket")
    @Fetch(value = FetchMode.SUBSELECT)
    Set<Task> tasks;

    @Column(name = "status")
    @Enumerated
    Status status;

    {
        links = new LinkedHashSet<>();
        tasks = new LinkedHashSet<>();
        progress = new LinkedHashSet<>();
        status = Status.OPEN;
        workspace = StringUtils.EMPTY;
    }
}
