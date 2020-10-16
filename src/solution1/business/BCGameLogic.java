package solution1.business;


public class BCGameLogic implements GameLogic {

    @Override
    public String generateAnswer() {
        String generatedAnswer = "";
        for (int i = 0; i < 4; i++){
            int random = (int) (Math.random() * 10);
            String randomDigit = "" + random;
            while (generatedAnswer.contains(randomDigit)){
                random = (int) (Math.random() * 10);
                randomDigit = "" + random;
            }
            generatedAnswer = generatedAnswer + randomDigit;
        }
        return generatedAnswer;
    }

    @Override
    public String getFeedback(String answer, String guess) {
        int cows = 0, bulls = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++ ) {
                if (answer.charAt(i) == guess.charAt(j)){
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
        String feedback = "";
        for (int i = 0; i < bulls; i++){
            feedback = feedback + "B";
        }
        feedback = feedback + ",";
        for (int i = 0; i < cows; i++){
            feedback = feedback + "C";
        }
        return feedback;
    }
}
