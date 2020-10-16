package solution1.business;

public interface GameLogic {
    String generateAnswer();

    String getFeedback(String answer, String guess);
}
