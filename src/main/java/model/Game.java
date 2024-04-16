package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {
    private Random random;
    private String gameID;
    private String username;
    private int targetNumber;
    private List<Guess> guessList;
    private boolean isComplete;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isActive;

    public Game(String username) {
        this.username = username;
        this.targetNumber = getRandomInt(1000);
        this.gameID = "GAME" + String.format("%05d", getRandomInt(99999));// AMEXXXXX
        this.isComplete = false;
        this.startTime = LocalDateTime.now();
        this.guessList = new ArrayList<>();
    }

    public Game(String gameID, String username, int targetNumber, boolean isComplete, LocalDateTime startTime, LocalDateTime endTime, boolean isActive) {
        this.gameID = gameID;
        this.username = username;
        this.targetNumber = targetNumber;
        this.isComplete = isComplete;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isActive = isActive;
        this.guessList = new ArrayList<>();
    }

    public String getGameID() {


        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(int targetNumber) {
        this.targetNumber = targetNumber;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Guess> getGuessList() {
        return guessList;
    }

    public void setGuessList(List<Guess> guessList) {
        this.guessList = guessList;
    }

    public List<Guess> getGuessListReversed() {
        List<Guess> tempList = new ArrayList<>();
        tempList.addAll(guessList);
        Collections.reverse(tempList);
        return tempList;
    }

    private int getRandomInt(int max) {
        if (random == null)
            random = new Random();
        return random.nextInt(max);
    }
}
