package com.br.igorsily.webfluxcourse.util;

public class ExceptionUtil {

    private static final String DUPLICATE_KEY_ERROR_MESSAGE = "E11000 duplicate key error collection";
    private static final String DUPLICATE_EMAIL_KEY_ERROR_MESSAGE = "E-mail duplicate key";
    private static final String DUPLICATE_EMAIL_KEY = "email_index dup key";

    private ExceptionUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getErrorDuplicateMessage(String message) {

        if (message.contains(DUPLICATE_EMAIL_KEY)) {
            return DUPLICATE_EMAIL_KEY_ERROR_MESSAGE;
        }

        return DUPLICATE_KEY_ERROR_MESSAGE;
    }
}
