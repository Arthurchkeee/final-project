package com.epam.project.validator;

import java.util.regex.Pattern;

public class LoginValidator {
    private static LoginValidator instance;
    private static final int LOGIN_MIN_LENGTH=5;
    private static final int PASSWORD_MIN_LENGTH=4;
    private static final int LOGIN_MAX_LENGTH=26;
    private static final int PASSWORD_MAX_LENGTH=51;
    private LoginValidator(){}

    public static LoginValidator getInstance() {
        if (instance == null) {
            instance = new LoginValidator();
        }
        return instance;
    }

    private static final String REGEX_FOR_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$";

    private static final String REGEX_FOR_LOGIN = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{1,50}$";

    public boolean isValidate(String login,String password){
        if(validateLoginLength(login) && validatePasswordLength(password)){
            return validatePassword(password)&&validateLogin(login);
        }
        else return false;
    }

    private boolean validatePassword(String password){
        return Pattern.matches(REGEX_FOR_PASSWORD,password);
    }

    private boolean validateLogin(String login){
        return Pattern.matches(REGEX_FOR_LOGIN,login);
    }

    private boolean validateLoginLength(String login){
        return login.length()>LOGIN_MIN_LENGTH&&login.length()<LOGIN_MAX_LENGTH;
    }

    private boolean validatePasswordLength(String password){
        return password.length()>PASSWORD_MIN_LENGTH&&password.length()<PASSWORD_MAX_LENGTH;
    }

}
