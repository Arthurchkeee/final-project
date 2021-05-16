package com.epam.project.db;
import com.epam.project.entityes.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class ConnectionPool {
    private static ConnectionPool INSTANCE = getInstance();
    private static BlockingQueue<ConnectionProxy> availableConnectionList;

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
         try {
             DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
         availableConnectionList=new LinkedBlockingQueue<>(Properties.getConnectionPoolSize());
         for (int i = 0; i < Properties.getConnectionPoolSize(); i++) {
            try{
                availableConnectionList.add(new ConnectionProxy(DriverManager.getConnection(Properties.getURL(),Properties.getUSER(),Properties.getPASSWORD())));
            } catch (SQLException  throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static Connection getConnection(){
        ConnectionProxy connection=null;
        try{
            connection=availableConnectionList.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
