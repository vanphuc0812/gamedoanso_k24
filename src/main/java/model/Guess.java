package model;

import java.time.LocalDateTime;

public class Guess {
    private int guessNumber;
    private LocalDateTime guessTime;
    private String gameID;
    private int guessResult;

    public int getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber(int guessNumber) {
        this.guessNumber = guessNumber;
    }

    public LocalDateTime getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(LocalDateTime guessTime) {
        this.guessTime = guessTime;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public int getGuessResult() {
        return guessResult;
    }

    public void setGuessResult(int guessResult) {
        this.guessResult = guessResult;
    }
}
