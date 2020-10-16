package solution1.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solution1.DAOMock;
import solution1.GameLogicMock;
import solution1.UserInterfaceMock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    UserInterfaceMock ui = new UserInterfaceMock();
    DAOMock daoMock = new DAOMock();
    GameLogicMock gameLogicMock = new GameLogicMock();
    Map<String, GameLogic> games = new HashMap<>();
    GameController controller;

    @BeforeEach
    void setUp() {
        games.put("bc", gameLogicMock);
        controller = new GameController(daoMock, games, ui);
        ui.setMock_user_input("bc");
        controller.chooseGame();
        controller.initiateGame();
    }

    @Test
    void LOGIN_TO_EXISTING_USER_POPULATES_USER_FIELD() {
        ui.setMock_user_input("existing_user");
        controller.login();
        assertNotNull(controller.getCurrentUser());
    }

    @Test
    void LOGIN_TO_NON_EXISTING_USER_DOES_NOT_POPULATE_USER_FIELD() {
        ui.setMock_user_input("non_existing_user");
        controller.login();
        assertNull(controller.getCurrentUser());
    }

    @Test
    void GUESSING_INCREMENTS_GUESS_COUNT() {
        controller.guess();
        assertEquals(1, controller.getnGuess());
    }

    @Test
    void GUESSING_POPULATES_GUESS_FIELD() {
        ui.setMock_user_input("1234");
        controller.guess();
        assertNotNull(controller.getGuess());
    }

    @Test
    void GAME_INITIATION_RESETS_GUESS_COUNT() {
        controller.guess();
        controller.initiateGame();
        assertEquals(0, controller.getnGuess());

    }

    @Test
    void GAME_INITIATION_CLEARS_GUESS_FIELD() {
        ui.setMock_user_input("guess_string");
        controller.guess();
        assertEquals("guess_string", controller.getGuess());
        controller.initiateGame();
        assertEquals("", controller.getGuess());
    }

}