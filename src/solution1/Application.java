package solution1;

import solution1.business.GameController;
import solution1.business.GameLogic;
import solution1.business.BCGameLogic;
import solution1.business.HiLoGameLogic;
import solution1.integration.DAO;
import solution1.integration.DAOImpl;
import solution1.presentation.SimpleWindow;
import solution1.presentation.UserInterface;

import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        DAO dao = new DAOImpl();
        UserInterface ui = new SimpleWindow("Game UI");

        Map<String, GameLogic> games = new HashMap<>();
        games.put("bc", new BCGameLogic());
        games.put("hilo", new HiLoGameLogic());

        GameController controller = new GameController(dao, games, ui);
        controller.run();
    }
}
