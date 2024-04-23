CREATE TABLE player(
                       username		varchar(50) NOT NULL,
                       password		varchar(50) NOT NULL,
                       primary key (username)
);

CREATE TABLE game(
                     gameID			varchar(10) NOT NULL,
                     username		varchar(50) NOT NULL,
                     targetNumber	int(4) NOT NULL,
                     startTime		timestamp NOT NULL,
                     endTime			timestamp,
                     isComplete		int(1) DEFAULT 0,
                     isActive		int(1) DEFAULT 0,
                     primary key (gameID)
);

CREATE TABLE guess(
                      gameID			varchar(10) NOT NULL,
                      guessNumber		int(4) NOT NULL,
                      guessResult		int(1) NOT NULL,
                      guessTime		timestamp NOT NULL
);

ALTER TABLE game
    ADD CONSTRAINT game_player_fk
        FOREIGN KEY game(username) REFERENCES player(username);

ALTER TABLE guess
    ADD CONSTRAINT guess_game_fk
        FOREIGN KEY guess(gameID) REFERENCES game(gameID);

