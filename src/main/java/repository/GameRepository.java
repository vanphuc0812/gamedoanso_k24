package repository;

import model.Game;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameRepository extends AbtractRepository<Game> {
    public void save(Game game) {
        executeUpdate((connection -> {
            String sql = "INSERT INTO game (gameID,username,targetNumber,startTime, isActive)" +
                    " values(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            int i = 1;
            statement.setString(i++, game.getGameID());
            statement.setString(i++, game.getUsername());
            statement.setInt(i++, game.getTargetNumber());
            statement.setTimestamp(i++, Timestamp.valueOf(game.getStartTime()));
            statement.setInt(i++, 1);
            statement.executeUpdate();
            return null;
        }));
    }

    public void deactiveAllGame(String username) {
        executeUpdate((connection -> {
            String sql = "UPDATE game SET isActive = 0 WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            statement.executeUpdate();
            return null;
        }));
    }

    public Game getActiveGameByUsername(String username) {
        return executeQuery((connection -> {
            String sql = "SELECT * FROM game where username = ? and isActive = 1";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getGameModelFromResultSet(resultSet);
            }
            return null;
        }));
    }

    public Game getGameByGameID(String gameID) {
        return executeQuery((connection -> {
            String sql = "SELECT * FROM game WHERE gameID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return getGameModelFromResultSet(resultSet);
            }
            return null;
        }));
    }

    private Game getGameModelFromResultSet(ResultSet resultSet) throws SQLException {
        String gameID = resultSet.getString("gameID");
        String username = resultSet.getString("username");
        int targetNumber = resultSet.getInt("targetNumber");
        LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
        Timestamp endTimeInTimestamp = resultSet.getTimestamp("endTime");
        /*
        LocalDateTime endTime;
        if(endTimeInTimestamp != null) endTime = endTimeInTimestamp.toLocalDateTime();
        else endTime = null
        */
        LocalDateTime endTime = endTimeInTimestamp == null ? null : endTimeInTimestamp.toLocalDateTime();
        boolean isComplete = resultSet.getInt("isComplete") == 1 ? true : false;
        boolean isActive = resultSet.getInt("isActive") == 1 ? true : false;
        return new Game(gameID, username, targetNumber, isComplete, startTime, endTime, isActive);
    }

    public void finishGame(String gameID) {
        executeUpdate((connection -> {
            String sql = "UPDATE game SET isComplete = ?, endTime = ? WHERE gameID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 1);
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(3, gameID);
            statement.executeUpdate();
            return null;
        }));
    }

    public List<Game> getTopByPlayTime(int top) {
        return executeQueryList((connection -> {
            List<Game> listRank = new ArrayList<>();
            String sql = "select game.*, TIMESTAMPDIFF(SECOND, startTime, endTime) AS PLAYTIME " +
                    "from game " +
                    "WHERE isComplete = 1 " +
                    "ORDER BY TIMESTAMPDIFF(SECOND, startTime, endTime) " +
                    "LIMIT ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, top);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Game game = getGameModelFromResultSet(resultSet);
                game.setPlayTime(resultSet.getInt("PLAYTIME"));
                listRank.add(game);
            }
            return listRank;
        }));
    }

    public List<Game> getTopByGuessTimes(int top) {
        return executeQueryList((connection -> {
            List<Game> listRank = new ArrayList<>();
            String sql = "select game.*, COUNT(guess.guessNumber) as GUESSTIMES from game " +
                    "    inner join guess ON game.gameID = guess.gameID " +
                    "    GROUP BY game.gameID " +
                    "    ORDER BY GUESSTIMES " +
                    "    LIMIT ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, top);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Game game = getGameModelFromResultSet(resultSet);
                game.setGuessTimes(resultSet.getInt("GUESSTIMES"));
                listRank.add(game);
            }
            return listRank;
        }));
    }

}
