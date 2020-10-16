package solution1.business;

import solution1.presentation.UserInterface;
import solution1.integration.DAO;

import java.util.Map;
import java.util.Optional;

public class GameController {
    DAO dao;
    String gameTitle;
    Map<String, GameLogic> games;
    GameLogic gameLogic;
    UserInterface ui;
    User currentUser;
    int nGuess;
    String answer;
    String guess;
    boolean continuePlaying = true;

    public GameController(DAO dao, Map<String, GameLogic> games, UserInterface ui) {
        this.games = games;
        this.dao = dao;
        this.ui = ui;
    }

    public void run() {
        chooseGame();
        while (currentUser == null) {
            login();
        }
        while (continuePlaying) {
            initiateGame();
            playGame();
            dao.postResult(nGuess, currentUser.getId());
            showTopTen();
            confirmContinue();
        }
        ui.exit();
    }

    public void chooseGame() {
        while (!games.containsKey(gameTitle)) {
            ui.addString("Pick a game from the list!\n");
            games.keySet().forEach(s -> ui.addString(s + "\n"));
            gameTitle = ui.getString();
        }
        gameLogic = games.get(gameTitle);
        dao.setGameTitle(gameTitle);
    }

    public void login() {
        ui.addString("Enter your username:\n");
        String name = ui.getString();
        Optional<User> loggedInUser = dao.loginByName(name);
        if (loggedInUser.isPresent()) {
            currentUser = loggedInUser.get();
        } else {
            ui.addString("Username not found, try again...\n");
        }
    }

    public void initiateGame() {
        answer = gameLogic.generateAnswer();
        guess = "";
        nGuess = 0;
        ui.clear();
        ui.addString("New game:\n");
//        comment out or remove next line to play real games!
        ui.addString("For practice, answer is: " + answer + "\n");
    }

    private void playGame() {
        while (!guess.equals(answer)) {
            guess();
            renderFeedback();
        }
    }

    public void guess() {
        guess = ui.getString();
        nGuess++;
    }

    public void renderFeedback() {
        String feedback = gameLogic.getFeedback(answer, guess);
        ui.addString("Your guess: " + guess + ", count: " + nGuess + "\n");
        ui.addString("Feedback: " + feedback + "\n");
    }

    public void showTopTen() {
        ui.addString("Top Ten List\n    Player     Average\n");
        for (PlayerAverage pa : dao.getTopTen()) {
            ui.addString(String.format("%-10s%5.2f%n", pa.name, pa.average));
        }
    }

    public void confirmContinue() {
        continuePlaying = ui.continuePrompt(nGuess);
    }

    public int getnGuess() {
        return nGuess;
    }

    public void setnGuess(int nGuess) {
        this.nGuess = nGuess;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
