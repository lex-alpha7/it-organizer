package ru.akhitev.organizer.logic.business.exception;

public class OrganizerValidationException extends RuntimeException {
    private static final String TEMPLATE = "Validation of an object of the class [%s] has failed with message [%s]";

    public OrganizerValidationException(String className, String message) {
        super(String.format(TEMPLATE, className, message), null);
    }
}
