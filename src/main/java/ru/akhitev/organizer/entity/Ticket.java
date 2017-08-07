package ru.akhitev.organizer.entity;

import com.google.common.base.Joiner;
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
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    Integer id;

    @ManyToOne @JoinColumn(name = "project_id", nullable = false)
    @NonNull
    Project project;

    @Column(name = "key", nullable = false)
    @NonNull
    String key;

    @Column(name = "name")
    String name;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ticket")
    @Fetch(value = FetchMode.SUBSELECT)
    List<TicketLink> links;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ticket")
    @Fetch(value = FetchMode.SUBSELECT)
    List<Progress> progress;

    @Column(name = "workspace")
    String workspace;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ticket")
    @Fetch(value = FetchMode.SUBSELECT)
    @Enumerated
    List<Task> tasks;

    @Column(name = "status")
    Status status;

    {
        links = new ArrayList<>();
        tasks = new ArrayList<>();
        progress = new ArrayList<>();
        status = Status.OPEN;
        workspace = StringUtils.EMPTY;
    }
}
