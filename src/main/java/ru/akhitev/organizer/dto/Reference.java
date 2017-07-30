package ru.akhitev.organizer.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reference {
    @Getter
    Map<String, String> links;

    @Getter
    Map<String, String> notes;

    public Reference() {
        links = new HashMap<>();
        notes = new HashMap<>();
    }

    public void addLink(String link, String description) {
        links.put(link, description);
    }

    public void addNote(String title, String note) {
        notes.put(title, note);
    }
}
