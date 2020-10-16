package solution1;

import solution1.business.GameLogic;

public class GameLogicMock implements GameLogic {
    @Override
    public String generateAnswer() {
        return null;
    }

    @Override
    public String getFeedback(String answer, String guess) {
        return null;
    }
}
