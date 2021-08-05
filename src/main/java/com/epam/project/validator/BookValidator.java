package com.epam.project.validator;

import java.util.regex.Pattern;

public class BookValidator {
    private static BookValidator instance;
    private static final int NAME_MIN_LENGTH=1;
    private static final int NAME_MAX_LENGTH=100;
    private static final int AUTHOR_MIN_LENGTH=1;
    private static final int AUTHOR_MAX_LENGTH=100;
    private static final int IMAGE_MIN_LENGTH=5;
    private static final int IMAGE_MAX_LENGTH=100;
    private static final String REGEX_FOR_NAME="^(?=.*[A-Za-z0-9А-Яа-яЁё.-–()]$){1,100}$";
    private static final String REGEX_FOR_AUTHOR="^(?=.*[A-Za-z0-9А-Яа-яЁё-–]$){1,100}$";
    private static final String REGEX_FOR_DESCRIPTION="^(?=.*[A-Za-z0-9А-Яа-яЁё.,–-?!/«»]$)";
    private static final String REGEX_FOR_IMAGE="^(?=.*[A-Za-z0-9А-Яа-яЁё/.-–-()]$){5,100}$";

    private BookValidator(){}
    public static BookValidator getInstance(){
        if (instance == null) {
            instance = new BookValidator();
        }
        return instance;
    }

    public boolean isValid(String name, String author, String description, String image){
        if(validateLength(name,NAME_MIN_LENGTH,NAME_MAX_LENGTH)&&validateRegex(name,REGEX_FOR_NAME)&&validateLength(author,AUTHOR_MIN_LENGTH,AUTHOR_MAX_LENGTH)&&validateRegex(author,REGEX_FOR_AUTHOR)
                &&validateLength(image,IMAGE_MIN_LENGTH,IMAGE_MAX_LENGTH)&&validateRegex(image,REGEX_FOR_IMAGE)&&validateRegex(description,REGEX_FOR_DESCRIPTION))
            return true;
        else
            return false;
    }

    private boolean validateRegex(String name, String regex){
        return Pattern.matches(regex,name);
    }



    private boolean validateLength(String string, int min, int max){
        return string.length()>=min && string.length()<=max;
    }

}
