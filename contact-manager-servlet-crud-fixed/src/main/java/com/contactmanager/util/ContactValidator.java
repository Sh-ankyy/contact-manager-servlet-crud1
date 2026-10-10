package com.contactmanager.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public final class ContactValidator {
    private static final Pattern EMAIL =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE = Pattern.compile("^\\d{10}$");

    private ContactValidator() {}

    public static Map<String, String> validate(String name, String email, String phone) {
        Map<String, String> errors = new LinkedHashMap<>();

        if (name == null || name.isBlank() || name.trim().length() < 2
                || name.trim().length() > 50) {
            errors.put("name", "Please enter valid name (2–50 characters).");
        }

        if (email == null || email.isBlank() || !EMAIL.matcher(email.trim()).matches()) {
            errors.put("email", "Invalid email address.");
        }

        if (phone != null && !phone.isBlank() && !PHONE.matcher(phone.trim()).matches()) {
            errors.put("phone", "Use 10-digit format.");
        }

        return errors;
    }
}
