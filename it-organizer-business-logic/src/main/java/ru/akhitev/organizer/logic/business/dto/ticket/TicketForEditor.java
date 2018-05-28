package ru.akhitev.organizer.logic.business.dto.ticket;

public class TicketForEditor extends AbstractTicket {
    private Integer id;
    private Integer projectId;
    private String key;
    private String priority;
    private String name;
    private String workspace;
    private String displayedName;

    public TicketForEditor() {}

    public TicketForEditor(Integer id, Integer projectId, String key, String priority, String name, String workspace) {
        this.id = id;
        this.projectId = projectId;
        this.key = key;
        this.priority = priority;
        this.name = name;
        this.workspace = workspace;
        this.displayedName = String.format(DISPLAYED_NAME_TEMPLATE, key, priority, name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplayedName() {return displayedName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }
}
