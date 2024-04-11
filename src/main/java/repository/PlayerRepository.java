package repository;

import jdbc.MysqlConnector;
import model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerRepository {
    public Player getPlayerByUsername(String pUsername) {
        try(Connection connection = MysqlConnector.getConnection()) {
            String sql = "SELECT * FROM player WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pUsername);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                return new Player(username, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    public void savePlayer(String pUsername, String pPassword) {
        try(Connection connection = MysqlConnector.getConnection()) {
            String sql = "INSERT INTO player(username, password) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pUsername);
            statement.setString(2, pPassword);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
