package com.epam.project.entityes;

public class Properties {
    private final static String URL="jdbc:mysql://localhost:3306/library";
    private final static String USER="root";
    private final static String PASSWORD="admin";
    private static final int ConnectionPoolSize=10;

    public static String getURL() {
        return URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static int getConnectionPoolSize() {
        return ConnectionPoolSize;
    }
}
