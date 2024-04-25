package service;

import model.Game;
import repository.GameRepository;

import java.util.List;

public class RankService {
    GameRepository gameRepository = new GameRepository();

    public List<Game> getTopByPlayTime(int top) {
        return gameRepository.getTopByPlayTime(top);
    }

    // write function getTopByGuessTimes
    public List<Game> getTopByGuessTimes(int top) {
        return gameRepository.getTopByGuessTimes(top);
    }
}
