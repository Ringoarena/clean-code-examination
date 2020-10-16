package solution1.business;

public class HiLoGameLogic implements GameLogic {

    @Override
    public String generateAnswer() {
        String generatedAnswer = "";
        int answer = (int) (Math.random() * 100) + 1;
        generatedAnswer = String.valueOf(answer);
        return generatedAnswer;
    }

    @Override
    public String getFeedback(String answer, String guess) {
        int answerInt = Integer.parseInt(answer);
        int guessInt = Integer.parseInt(guess);
        String feedback;
        if (guessInt == answerInt) {
            feedback = "Correct, the answer is " + answer;
        } else if (guessInt < answerInt) {
            feedback = "Too low!";
        } else {
            feedback = "Too high!";
        }
        return feedback;
    }
}
