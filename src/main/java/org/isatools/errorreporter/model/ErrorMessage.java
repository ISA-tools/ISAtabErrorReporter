package org.isatools.errorreporter.model;

public class ErrorMessage {
    private ErrorLevel errorLevel;
    private String message;

    public ErrorMessage(ErrorLevel errorLevel, String message) {
        this.errorLevel = errorLevel;
        this.message = message;
    }

    public ErrorLevel getErrorLevel() {
        return errorLevel;
    }

    public String getMessage() {
        return message;
    }
}
