package ru.akhitev.organizer.dto;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
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
    public void whenNoLinksWereSetThenReturnEmptyMap() {
        assertThat(reference.getLinks())
        .as("when no links were set then return not null empty map")
        .isEqualTo(Collections.emptyMap());
    }

    @Test
    public void whenLinksWereAddedThenReturnThem() {
        String link = "http://www.someviki.com/good_link";
        String description = "Very useful link!";
        reference.addLink(link, description);
        assertThat(reference.getLinks().get(link))
                .as("when a link was add then return it")
                .isEqualTo(description);
    }

    @Test
    public void whenNoNotesWereSetThenReturnEmptyMap() {
        assertThat(reference.getNotes())
                .as("when no notes were set then return not null empty map")
                .isEqualTo(Collections.emptyMap());
    }

    @Test
    public void whenNotesWereAddedThenReturnThem() {
        String title = "Wonderful static method in util!";
        String note = "Method staticSuperFactory from SomeUtil can create all you need.";
        reference.addNote(title, note);
        assertThat(reference.getNotes().get(title))
                .as("when a note was add then return it")
                .isEqualTo(note);
    }
}
