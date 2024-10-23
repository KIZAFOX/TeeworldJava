package fr.kiza.teeworld.mysql.dao;

import fr.kiza.teeworld.mysql.data.User;
import fr.kiza.teeworld.mysql.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String GET_TIMER_BY_ID = "SELECT timer FROM users WHERE id = ?";
    private static final String UPDATE_TIMER = "UPDATE users SET timer = ? WHERE id = ?";

    public static User getUserById(final int id) {
        try (final Connection connection = DatabaseManager.getConnection()){
            final PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);

            statement.setInt(1, id);

            try (final ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new User(resultSet.getString("name"), resultSet.getInt("timer"));
                }
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return new User("USER NOT FOUND", -1);
    }

    public static long getTimer(final int id){
        try (final Connection connection = DatabaseManager.getConnection()){
            final PreparedStatement statement = connection.prepareStatement(GET_TIMER_BY_ID);

            statement.setInt(1, id);

            try (final ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getLong("timer");
                }
            }
        } catch (final SQLException e){
            throw new RuntimeException(e);
        }
        return 0L;
    }

    public static void updateTimer(final int id, final long timer){
        try (final Connection connection = DatabaseManager.getConnection()){
            final PreparedStatement statement = connection.prepareStatement(UPDATE_TIMER);

            statement.setLong(1, timer);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
        }catch (final SQLException e){
            throw new RuntimeException(e);
        }
    }
}
