package ru.akhitev.organizer.logic.business.dto.ticket;

import ru.akhitev.organizer.logic.business.dto.AdjustableNameSize;

public class TicketForList extends AbstractTicket implements AdjustableNameSize {
    private final Integer id;
    private final String displayedName;

    public TicketForList(Integer id, String key, String priority, String name, Integer nameSize) {
        this.id = id;
        this.displayedName = constructDisplayedName(key, priority, name, nameSize);
    }

    private String constructDisplayedName(String key, String priority, String name, Integer nameSize) {
        return adjustSize(String.format(DISPLAYED_NAME_TEMPLATE, key, priority, name), nameSize);
    }

    public Integer getId() {
        return id;
    }

    public String getDisplayedName() {
        return displayedName;
    }
}
