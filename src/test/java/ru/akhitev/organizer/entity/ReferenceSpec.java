package ru.akhitev.organizer.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ReferenceSpec {
    private Reference reference;

    @Before
    public void setTest() {
        reference = new Reference();
    }

    @Test
    public void whenNoLinksWereSetThenReturnEmptyList() {
        assertThat(reference.getLinks())
        .as("when no links were set then return not null empty map")
        .isEqualTo(Collections.emptyList());
    }

    @Test
    public void whenNoNotesWereSetThenReturnEmptyList() {
        assertThat(reference.getNotes())
                .as("when no notes were set then return not null empty map")
                .isEqualTo(Collections.emptyList());
    }

    @Test
    public void whenWithIdThenReturnIt() {
        final Integer id = 1;
        reference.setId(id);
        assertThat(reference.getId())
                .as("when an id was set then return it")
                .isEqualTo(id);
    }

    @Test
    public void whenIdThenReturnIt() {
        final Integer id = null;
        reference.setId(id);
        assertThat(reference.getId())
                .as("when no id was set then return it")
                .isEqualTo(id);
    }
}
