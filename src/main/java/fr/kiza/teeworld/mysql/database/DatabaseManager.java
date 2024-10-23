package fr.kiza.teeworld.mysql.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    public static HikariDataSource connection;

    public void connect(){
        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/teeworlds");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername("root");
        config.setPassword("");

        config.setMaximumPoolSize(10); // Nombre maximum de connexions dans le pool
        config.setMinimumIdle(5);      // Nombre minimum de connexions inactives dans le pool
        config.setIdleTimeout(30000);  // Temps (ms) avant de fermer une connexion inactive
        config.setConnectionTimeout(10000); // Temps maximum d'attente pour obtenir une connexion (ms)
        config.setMaxLifetime(1800000);

        connection = new HikariDataSource(config);
        System.out.println("--> DATABASE CONNECTED USING HIKARI <--");
    }

    public static Connection getConnection() throws SQLException {
        if (connection != null) {
            return connection.getConnection();
        } else {
            throw new IllegalStateException("DataSource is not initialized. Call connect() first.");
        }
    }
}
