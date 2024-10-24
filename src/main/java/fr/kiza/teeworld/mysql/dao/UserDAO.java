package fr.kiza.teeworld.mysql.dao;

import fr.kiza.teeworld.mysql.data.User;
import fr.kiza.teeworld.mysql.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private final String TABLE = "users";

    public void createAccount(final String username){
        try (final Connection connection = DatabaseManager.getConnection()){
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO " + TABLE + " (`name`, `timer`) VALUES (?, ?)");

            statement.setString(1, username);
            statement.setLong(2, 0L);
            statement.executeUpdate();
        } catch(final SQLException e){
            throw new RuntimeException(e);
        }
    }

    public User getUser(final int id) {
        try (final Connection connection = DatabaseManager.getConnection()){
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE + " WHERE id = ?");

            statement.setInt(1, id);

            try (final ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new User(resultSet.getString("name"), resultSet.getInt("timer"));
                }
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public long getTimer(final int id){
        try (final Connection connection = DatabaseManager.getConnection()){
            final PreparedStatement statement = connection.prepareStatement("SELECT timer FROM " + TABLE + " WHERE id = ?");

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

    public void setTimer(final int id, final long timer){
        try (final Connection connection = DatabaseManager.getConnection()){
            final PreparedStatement statement = connection.prepareStatement("UPDATE " + TABLE + " SET timer = ? WHERE id = ?");

            statement.setLong(1, timer);
            statement.setInt(2, id);
            statement.executeUpdate();
        }catch (final SQLException e){
            throw new RuntimeException(e);
        }
    }
}
