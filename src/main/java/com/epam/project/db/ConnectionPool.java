package com.epam.project.db;
import com.epam.project.entities.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class ConnectionPool {
    private static ConnectionPool instance;
    private static BlockingQueue<ConnectionProxy> availableConnectionList;
    private static BlockingQueue<ConnectionProxy> usedConnectionList;

    private ConnectionPool(){
        initConnectionPool();
    }

    public static ConnectionPool getInstance(){
        if(instance ==null){
            instance =new ConnectionPool();
        }
        return instance;
    }

     void initConnectionPool(){
         try {
             DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
         availableConnectionList=new LinkedBlockingQueue<>(Properties.CONNECTION_POOL_SIZE);
         usedConnectionList=new LinkedBlockingQueue<>(Properties.CONNECTION_POOL_SIZE);
         for (int i = 0; i < Properties.CONNECTION_POOL_SIZE; i++) {
            try{
                availableConnectionList.add(new ConnectionProxy(DriverManager.getConnection(Properties.URL,Properties.USER,Properties.PASSWORD)));
            } catch (SQLException  throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public Connection getConnection(){
        ConnectionProxy connection=null;
        try{
            if(availableConnectionList.size()==0)
                updateConnections();
            System.out.println(availableConnectionList.size());
            connection=availableConnectionList.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void returnConnection(ConnectionProxy connection){
        usedConnectionList.add(connection);
    }

    public void updateConnections() throws InterruptedException {
        for (int i = 0; i < Properties.CONNECTION_POOL_SIZE; i++) {
            availableConnectionList.add(usedConnectionList.take());
        }
    }

}
