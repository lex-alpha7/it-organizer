package ru.akhitev.organizer.dto;

import com.google.common.base.Joiner;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {
    private static final String DEFAULT_REPORT = "Started working on it.";

    @Getter
    String key;

    @Getter @Setter
    String name;

    @Getter
    List<String> links;

    @Getter
    List<String> attachments;

    @Getter
    Map<Date, String> progress;

    @Getter @Setter
    String workspace;

    @Getter
    List<Task> tasks;

    @Getter @Setter
    Status status;

    public Ticket(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("Key should not be empty", null);
        }
        this.key = key;
        links = new ArrayList<>();
        attachments = new ArrayList<>();
        tasks = new ArrayList<>();
        progress = new TreeMap<>(); // for sorting
        status = Status.OPEN;
        workspace = StringUtils.EMPTY;
    }

    public void addLink(String link) {
        links.add(link);
    }

    public void addAttachment(String attachment) {
        attachments.add(attachment);
    }

    public String getProgressReport() {
        if (progress.size() == 0)
            return DEFAULT_REPORT;
        return Joiner.on("\n\n").withKeyValueSeparator(":\n").join(progress);
    }

    public void addProgress(Date progressDate, String progressText) {
        progress.put(progressDate, progressText);
    }

    public String getFullName() {
        if (StringUtils.isBlank(name))
            return key;
        StringBuilder fullNameBuilder = new StringBuilder(key);
        fullNameBuilder.append(": ");
        if (name.length() > 20) {
            fullNameBuilder.append(name.substring(0, 20));
            fullNameBuilder.append("...");
        } else {
            fullNameBuilder.append(name);
        }
        return fullNameBuilder.toString();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
