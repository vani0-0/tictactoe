/**
 * The `tictactoe.Main` class serves as the entry point for the Tic-Tac-Toe game application.
 */
public class Main {
    /**
     * Default constructor for the Main class. This constructor does nothing.
     */
    public Main() {}

    /**
     * The main method that initializes the game and starts it.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Game game = new Game();
        int action = game.load();

        while (action == 1) {
            game.start();
            game.gameOver();
            action = game.restart();
        }
    }
}
