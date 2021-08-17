package com.epam.project.validator;

import java.util.regex.Pattern;

public class СommentValidator {
    private static СommentValidator instance;
    private static final String REGEX_FOR_DESCRIPTION="^([A-Za-z0-9A-Яа-яЁё.,/…№#()!?–:;«» -]*$)";
    private static final int COMMENT_MIN_LENGTH=1;
    private static final int COMMENT_MAX_LENGTH=500;


    private СommentValidator() {
    }

    public static СommentValidator getInstance() {
        if(instance==null) {
            return new СommentValidator();
        }
            return instance;
    }

    public boolean isValid(String comment){
        return validateRegex(comment, REGEX_FOR_DESCRIPTION)&&validateLength(comment,COMMENT_MIN_LENGTH,COMMENT_MAX_LENGTH);
    }

    private boolean validateRegex(String name, String regex){
        return Pattern.matches(regex,name);
    }



    private boolean validateLength(String string, int min, int max){
        return string.length()>=min && string.length()<=max;
    }
}
