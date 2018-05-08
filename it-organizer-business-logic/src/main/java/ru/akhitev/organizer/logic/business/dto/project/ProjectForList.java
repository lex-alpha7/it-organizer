package ru.akhitev.organizer.logic.business.dto.project;

import ru.akhitev.organizer.logic.business.dto.AdjustableNameSize;

public class ProjectForList implements AdjustableNameSize {
    private Integer id;
    private String name;

    public ProjectForList() {
    }

    public ProjectForList(Integer id, String name, Integer nameSize) {
        this.id = id;
        this.name = adjustSize(name, nameSize);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
