package solution1.business;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class BCGameLogicTest {
    BCGameLogic gameLogic = new BCGameLogic();

    @Test
    void GENERATED_ANSWER_HOLDS_UNIQUE_CHARACTERS() {
        String generated = gameLogic.generateAnswer();
        HashSet<Character> characters = new HashSet<>();
        for (char c : generated.toCharArray()) {
            if (!characters.add(c)) {
                fail();
            }
        }
    }

    @Test
    void FEEDBACK_BBBB() {
        String feedback = gameLogic.getFeedback("1234", "1234");
        assertEquals("BBBB,", feedback);
    }

    @Test
    void FEEDBACK_BBCC() {
        String feedback = gameLogic.getFeedback("1234", "1243");
        assertEquals("BB,CC", feedback);
    }

    @Test
    void FEEDBACK_CCCC() {
        String feedback = gameLogic.getFeedback("1234", "4123");
        assertEquals(",CCCC", feedback);
    }

    @Test
    void FEEDBACK_() {
        String feedback = gameLogic.getFeedback("1234", "5555");
        assertEquals(",", feedback);
    }
}