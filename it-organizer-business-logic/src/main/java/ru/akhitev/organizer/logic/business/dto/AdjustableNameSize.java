package ru.akhitev.organizer.logic.business.dto;

public interface AdjustableNameSize {
     default String adjustSize(String input, Integer size) {
        String output;
        if (input.length() <= size) {
            output = input;
        } else {
            output = input.substring(0, size);
        }
        return output;
    }
}
