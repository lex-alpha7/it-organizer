package ru.akhitev.organizer.dto;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

public class Project {
    @Getter
    private String name;

    public Project(String name) {
        if (StringUtils.isEmpty(name))
            throw new IllegalArgumentException("Name mustn't be null or empty", null);
        this.name = name;
    }
}
