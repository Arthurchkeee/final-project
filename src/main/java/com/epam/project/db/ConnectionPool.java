package com.epam.project.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ConnectionPool {
    private static ConnectionPool INSTANCE = getInstance();
    private static final int ConnectionPoolSize=10;
    private List<Connection> availableConnectionList=new ArrayList<>(ConnectionPoolSize);
    private final static String URL="jdbc:mysql://localhost:3306/library";
    private final static String USER="root";
    private final static String PASSWORD="admin";
    private ConnectionPool(){
        initConnectionPool();
    }

    public static ConnectionPool getInstance(){
        if(INSTANCE==null){
            INSTANCE=new ConnectionPool();
        }
        return INSTANCE;
    }

     void initConnectionPool(){
        ConnectionPool connectionPool=new ConnectionPool();

        for (int i = 0; i < ConnectionPoolSize; i++) {
            try(Connection connection=new ConnectionProxy(DriverManager.getConnection(URL,USER,PASSWORD))){
                availableConnectionList.add(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

}
