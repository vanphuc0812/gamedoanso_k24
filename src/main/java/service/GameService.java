package service;

import model.Game;
import model.Guess;
import repository.GameRepository;
import repository.GuessRepository;

import java.time.LocalDateTime;
import java.util.List;

public class GameService {
    GameRepository gameRepository = new GameRepository();
    GuessRepository guessRepository = new GuessRepository();

    public Game newGame(String username) {
        Game newGame = new Game(username);
        gameRepository.deactiveAllGame(username);
        gameRepository.save(newGame);

        return newGame;
    }

    public Game loadGame(String username) {
        Game game = gameRepository.getActiveGameByUsername(username);
        if (game != null) {
            List<Guess> list = guessRepository.getGuessListByGameID(game.getGameID());
            game.getGuessList().addAll(list);
            return game;
        } else {
            return newGame(username);
        }
    }

    public Game processGame(String gameID, int guessNumber) throws Exception {
        Game game = gameRepository.getGameByGameID(gameID);
        if (game == null) throw new Exception("Game is not exist");
        game.getGuessList().addAll(guessRepository.getGuessListByGameID(gameID));

        int guessResult;
        if (guessNumber > game.getTargetNumber()) {
            guessResult = 1;
        } else if (guessNumber < game.getTargetNumber()) {
            guessResult = -1;
        } else {
            guessResult = 0;
            game.setComplete(true);
            game.setEndTime(LocalDateTime.now());
            gameRepository.finishGame(gameID);
        }
        Guess guess = new Guess(guessNumber, gameID, guessResult);
        System.out.println(guess.getGuessNumber());
        guessRepository.save(guess);
        game.getGuessList().add(guess);
        return game;
    }
}
