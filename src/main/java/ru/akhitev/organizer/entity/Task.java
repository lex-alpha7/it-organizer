package ru.akhitev.organizer.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import ru.akhitev.organizer.enums.Status;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Getter
    Ticket parentTicket;

    @Getter
    String name;

    @Getter @Setter
    String workspace;

    @Getter @Setter
    Status status;

    public Task(Ticket parentTicket, String name) {
        if (parentTicket == null || StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Name and Parent Key should not be empty", null);
        }
        this.parentTicket = parentTicket;
        this.name = name;
        status = Status.OPEN;
        workspace = StringUtils.EMPTY;
    }
}
