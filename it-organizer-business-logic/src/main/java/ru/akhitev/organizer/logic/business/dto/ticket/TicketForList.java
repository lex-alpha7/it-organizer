package ru.akhitev.organizer.logic.business.dto.ticket;

public class TicketForList extends AbstractTicket {
    private final Integer id;
    private final String displayedName;

    public TicketForList(Integer id, String key, String priority, String name, Integer nameSize) {
        this.id = id;
        this.displayedName = constructDisplayedName(key, priority, name, nameSize);
    }

    private String constructDisplayedName(String key, String priority, String name, Integer nameSize) {
        return String.format(DISPLAYED_NAME_TEMPLATE, key, priority, name).substring(0, nameSize);
    }

    public Integer getId() {
        return id;
    }

    public String getDisplayedName() {
        return displayedName;
    }
}
