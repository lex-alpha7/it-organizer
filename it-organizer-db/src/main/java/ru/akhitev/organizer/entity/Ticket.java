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
@EqualsAndHashCode(exclude = "project")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @ManyToOne @JoinColumn(name = "project_id", nullable = false)
    @NonNull
    private Project project;

    @Column(name = "ticket_key", nullable = false)
    @NonNull
    private String key;

    @Column(name = "name")
    private String name;

    @Column(name = "priority")
    private String priority;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ticket")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<TicketLink> links;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ticket")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Progress> progress;

    @Column(name = "workspace", columnDefinition="TEXT")
    private String workspace;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ticket")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Task> tasks;

    @Column(name = "status")
    @Enumerated
    private Status status;

    {
        links = new LinkedHashSet<>();
        tasks = new LinkedHashSet<>();
        progress = new LinkedHashSet<>();
        status = Status.OPEN;
        workspace = StringUtils.EMPTY;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Set<TicketLink> getLinks() {
        return links;
    }

    public void setLinks(Set<TicketLink> links) {
        this.links = links;
    }

    public Set<Progress> getProgress() {
        return progress;
    }

    public void setProgress(Set<Progress> progress) {
        this.progress = progress;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", project=" + project +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", links=" + links +
                ", progress=" + progress +
                ", workspace='" + workspace + '\'' +
                ", tasks=" + tasks +
                ", status=" + status +
                '}';
    }
}
