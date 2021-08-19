package com.epam.project.validator;

import java.util.regex.Pattern;

public class LoginValidator {
    private static LoginValidator instance;
    private static final int LOGIN_MIN_LENGTH = 5;
    private static final int PASSWORD_MIN_LENGTH = 4;
    private static final int LOGIN_MAX_LENGTH = 26;
    private static final int PASSWORD_MAX_LENGTH = 51;

    private LoginValidator() {
    }

    public static LoginValidator getInstance() {
        if (instance == null) {
            instance = new LoginValidator();
        }
        return instance;
    }

    private static final String REGEX_FOR_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$";

    private static final String REGEX_FOR_LOGIN = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{1,50}$";

    public boolean isValid(String login, String password) {
        return validateLength(login, LOGIN_MIN_LENGTH, LOGIN_MAX_LENGTH) && validateLength(password, PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH) && validateRegex(password, REGEX_FOR_PASSWORD) && validateRegex(login, REGEX_FOR_LOGIN);
    }


    private boolean validateRegex(String login, String regex) {
        return Pattern.matches(regex, login);
    }


    private boolean validateLength(String string, int min, int max) {
        return string.length() > min && string.length() < max;
    }

}
