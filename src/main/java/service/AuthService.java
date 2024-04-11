package service;

import model.Player;
import repository.PlayerRepository;

public class AuthService {
    PlayerRepository playerRepository = new PlayerRepository();
    public Player login(String username, String password) {
        Player player = playerRepository.getPlayerByUsername(username);
        if(player != null && password.equals(player.getPassword())) {
            return player;
        } else {
            return null;
        }
    }

    public String register(String username, String password) {
        Player player = playerRepository.getPlayerByUsername(username);
        if(player != null) {
            return "Username is existed";
        }
        playerRepository.savePlayer(username, password);
        return "Success";
    }
}
