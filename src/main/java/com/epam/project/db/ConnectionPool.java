package com.epam.project.db;
import com.epam.project.entityes.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ConnectionPool {
    private static ConnectionPool INSTANCE = getInstance();
    private List<Connection> availableConnectionList=new ArrayList<>(Properties.getConnectionPoolSize());

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
        for (int i = 0; i < Properties.getConnectionPoolSize(); i++) {
            try(Connection connection=new ConnectionProxy(DriverManager.getConnection(Properties.getURL(),Properties.getUSER(),Properties.getPASSWORD()))){
                availableConnectionList.add(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }


}
