package fr.kiza.teeworld.mysql.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fr.kiza.teeworld.mysql.dao.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    private static HikariDataSource connection;

    private final UserDAO userDAO;

    public DatabaseManager() {
        this.userDAO = new UserDAO();
    }

    public void connect(){
        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/teeworlds");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername("root");
        config.setPassword("");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(10000);
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

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
