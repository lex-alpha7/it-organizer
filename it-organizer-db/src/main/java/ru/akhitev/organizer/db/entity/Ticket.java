/*
 * IT-Organizer is an organizer for a developer and other IT-specialists.
 * Copyright (c) 2017 Aleksei Khitev (Хитёв Алексей Юрьевич).
 *
 * This file is part of IT-Organizer
 *
 * IT-Organizer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * IT-Organizer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.akhitev.organizer.db.entity;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.akhitev.organizer.db.enums.Status;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Ticket")
@SequenceGenerator(name = "seq", initialValue = 20)
@EqualsAndHashCode(exclude = "project")
public class Ticket implements NodeDataBaseEntity<Project> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @ManyToOne @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "ticket_key", nullable = false)
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

    @Column(name = "steps_to_reproduce", columnDefinition="TEXT")
    private String stepsToReproduce;

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

    public void setProject(@NonNull Project project) {
        this.project = project;
    }

    public String getKey() {
        return key;
    }

    public void setKey(@NonNull String key) {
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

    public String getStepsToReproduce() {
        return stepsToReproduce;
    }

    public void setStepsToReproduce(String stepsToReproduce) {
        this.stepsToReproduce = stepsToReproduce;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", project=" + project +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", priority='" + priority + '\'' +
                ", links=" + links +
                ", progress=" + progress +
                ", workspace='" + workspace + '\'' +
                ", tasks=" + tasks +
                ", status=" + status +
                ", stepsToReproduce='" + stepsToReproduce + '\'' +
                '}';
    }

    @Override
    public void setRoot(Project root) {
        setProject(root);
    }
}
