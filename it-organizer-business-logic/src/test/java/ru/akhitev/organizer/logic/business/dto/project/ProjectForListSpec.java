package ru.akhitev.organizer.logic.business.dto.project;

import org.junit.Test;

public class ProjectForListSpec {

    @Test
    public void whenProjectForListHasIdAndNameThenValidationOk() {
        ProjectForList projectForList = ProjectForList.newInstance(1, "Test Project");
    }

    @Test
    public void whenProjectForListHasOnlyIdThenValidationFailes() {
        ProjectForList projectForList = ProjectForList.newInstance(1, null);

    }
}
