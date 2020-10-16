package solution1;

import solution1.presentation.UserInterface;

public class UserInterfaceMock implements UserInterface {
    String mock_user_input = "mockstring";

    @Override
    public String getString() {
        return mock_user_input;
    }

    @Override
    public void addString(String s) {
    }

    @Override
    public void clear() {
    }

    @Override
    public void exit() {
    }

    @Override
    public boolean continuePrompt(int nGuess) {
        return false;
    }

    public void setMock_user_input(String mock_user_input) {
        this.mock_user_input = mock_user_input;
    }
}
