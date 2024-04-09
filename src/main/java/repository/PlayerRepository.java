package repository;

import model.Player;

public class PlayerRepository {
    public Player getPlayerByUsername(String username) {
        return new Player("admin", "1234");
    }
}
