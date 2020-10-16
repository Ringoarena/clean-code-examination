package solution1.presentation;

public interface UserInterface {
    String getString();

    void addString(String s);

    void clear();

    void exit();

    boolean continuePrompt(int nGuess);
}
