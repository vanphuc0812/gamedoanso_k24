package repository;

import jdbc.MysqlConnector;
import model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerRepository extends AbtractRepository<Player>{
    public Player getPlayerByUsername(String pUsername) {
        return executeQuery((connection -> {
            String sql = "SELECT * FROM player WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pUsername);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                return new Player(username, password);
            }
            return null;
        }));
    }
    public void savePlayer(String pUsername, String pPassword) {
        executeUpdate((connection -> {
            String sql = "INSERT INTO player(username, password) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pUsername);
            statement.setString(2, pPassword);
            statement.executeUpdate();
            return null;
        }));
    }
}
