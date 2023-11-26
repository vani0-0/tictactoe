import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The `tictactoe.Game` class represents the main game logic for a Tic-Tac-Toe game.
 */
public class Game {
    private Player player1; // tictactoe.Player 1
    private Player player2; // tictactoe.Player 2
    private final Screen myScreen;
    private final Board myBoard;
    private Boolean turn;
    private Boolean over;
    private final List<Coordinate> winningSets;

    /**
     * Constructs a new `tictactoe.Game` object and initializes the game's screen and board.
     */
    public Game() {
        this.myScreen = new Screen();
        this.myBoard = new Board(myScreen);
        this.winningSets = new ArrayList<>();
        this.turn = true;
        this.over = false;
    }

    /**
     * Loads the game, displaying the main menu and handling user choices.
     *
     * @return The user's choice as an integer.
     */
    public int load() {
        int action = this.myScreen.mainMenu();
        switch (action) {
            case 1:
                this.initialize();
                break;
            case 2:
                this.myScreen.exitScreen();
                break;
            default:
                break;
        }
        return action;
    }

    /**
     * Starts the game, displaying the game board and the first player's turn.
     */
    public void start() {
        this.myScreen.displayLoadingScreen("Starting Game");
        while (!over) {
            this.myBoard.displayBoard();
            int[] pos = this.displayCurrentPlayerTurn();
            if (this.turn) {
                this.nextPlayerMove(player2, pos[0], pos[1]);
                this.setChecker(player2);

            } else {
                this.nextPlayerMove(player1, pos[0], pos[1]);
                this.setChecker(player1);
            }
        }
    }

    /**
     * Performs the next move for a player in the game.
     * <p>
     *      This method checks the player's current state and the availability of a set at the specified coordinates
     *      (x, y) on the game board and takes appropriate action based on the following conditions:
     * </p>
     *
     * <p>
     *      - If the player has fewer than three sets and the specified location (x, y) is unoccupied, a set is added.
     *      - If the player already has three sets and the specified location (x, y) is occupied, a set is taken.
     * </p>
     * <p>
     *     Original function is:
     *     <pre>
     *       if(!this.turn &amp;&amp; this.player1.getSizeSet() &lt; 3 &amp;&amp; this.myBoard.isFreeSet(x, y)) {
     *            addSet(player1, x, y);
     *        } else if (this.turn &amp;&amp; this.player2.getSizeSet() &lt; 3 &amp;&amp; this.myBoard.isFreeSet(x, y)) {
     *           addSet(player2, x, y);
     *        } else if (!this.turn &amp;&amp; this.player1.getSizeSet() == 3 &amp;&amp; this.player.isSetTaken(x, y)) {
     *            takeSet(player1, x, y);
     *         } else if(this.turn &amp;&amp; this.player2.getSizeSet() == 3 &amp;&amp; this.player.isSetTaken(x, y)) {
     *             takeSet(player2, x, y);
     *         }
     *     </pre>
     *     Just like what we see in the PDF, But I changed it so that It takes shorter and reduces redundancy.
     * @param player The player making the move.
     * @param x The X-coordinate of the set on the game board.
     * @param y The Y-coordinate of the set on the game board.
     */

    public void nextPlayerMove(Player player, int x, int y) {
        if(player.getSizeSet() < 3) {
            if(this.myBoard.isFreeSet(x, y)) {
                addSet(player, x, y);
            } else {
                System.out.println("Selected coordinate is taken!.");
            }
        } else if(player.getSizeSet() == 3) {
            if(!this.myBoard.isFreeSet(x, y) && player.isSetTaken(x, y)) {
                takeSet(player, x, y);
            } else {
                System.out.println("You already placed 3 pieces. \nTake 1 of your pieces.");
            }
        }
    }

    /**
     * Handles the restart functionality of the game based on the user's input.
     *
     * @return The user's choice after the restart (1 for rematch, 2 for exit).
     */
    public int restart() {
        int action = this.myScreen.restartGame();
        if (action == 1) {
            this.rematch();
        } else if (action == 2) {
            this.myScreen.exitScreen();
        }
        return action;
    }

    /**
     * Handles the end of the game, displays the winner's name (if any), the final game board, and informs the user.
     */
    public void gameOver() {
        String winner;
        if (this.turn) {
            winner = player2.getName();
        } else {
            winner = player1.getName();
        }
        this.myBoard.displayBoard();
        this.myScreen.displayWinner(winner);
    }

    /**
     * Displays the name of the current player's turn on the screen and retrieves their chosen coordinate.
     *
     * @return The selected coordinate from the current player.
     */
    private int[] displayCurrentPlayerTurn() {
        if (this.turn) {
            this.myScreen.displayName(player2.getName());
        } else {
            this.myScreen.displayName(player1.getName());
        }
        return this.myScreen.getCoordinate();
    }

    /**
     * Adds a player's character to the specified coordinate on the game board and updates the turn to the next player.
     *
     * @param player The player making the move.
     * @param x      The X-coordinate on the game board.
     * @param y      The Y-coordinate on the game board.
     */
    private void addSet(Player player, int x, int y) {
        player.setMySet(x, y);
        this.myBoard.updateSet(x, y, player.getCharacter());
        this.turn = !this.turn;
    }

    /**
     * Takes back a player's character from the specified coordinate on the game board.
     *
     * @param player The player taking back their move.
     * @param x      The X-coordinate on the game board.
     * @param y      The Y-coordinate on the game board.
     */
    private void takeSet(Player player, int x, int y) {
        player.removeMySet(x, y);
        this.myBoard.updateSet(x, y, " ");
    }

    /**
     * Sets the checker for a player based on the matching sets they have.
     *
     * @param player The player for whom to set the checker.
     */
    private void setChecker(Player player) {
        int[] count = {0, 0, 0};
        for (int i = 0; i < winningSets.size(); i++) {
            Coordinate winningSet = winningSets.get(i);
            count[i] = winningSet.countMatching((player.getMySet()));
        }
        System.out.println("Matching Sets: " + Arrays.toString(count));
        for (int i = 0; i < 3; i++) {
            if (count[i] == 2) {
                this.myScreen.displayMatchOver(i);
                this.over = !this.over;
            }
        }
    }

    /**
     * Initializes the game by prompting players to enter their names.
     */
    private void initialize() {
        String name, character;
        name = this.myScreen.nameInput("Player 1:");
        character = this.myScreen.characterInput();
        this.player1 = new Player(name, character);

        name = this.myScreen.nameInput("Player 2:");
        character = this.myScreen.characterInput();
        this.player2 = new Player(name, character);

        this.initializeWinningSets();
        this.myScreen.displayLoadingScreen("Loading");
    }

    /**
     * Initializes the winning sets for the Tic-Tac-Toe game.
     */
    private void initializeWinningSets() {
        Coordinate verticalSet = new Coordinate();
        Coordinate horizontalSet = new Coordinate();
        Coordinate diagonalSet = new Coordinate();

        verticalSet.add(1, 2, "L");
        verticalSet.add(3, 2, "L");
        horizontalSet.add(2, 1, "L");
        horizontalSet.add(2, 3, "L");
        diagonalSet.add(1, 3, "L");
        diagonalSet.add(3, 1, "L");

        winningSets.add(verticalSet);
        winningSets.add(horizontalSet);
        winningSets.add(diagonalSet);
    }

    /**
     * Restarts the game, allowing players to have a rematch by resetting the game state.
     * This method resets the game board, players' states, and sets the game state to not over.
     */
    private void rematch() {
        this.myBoard.resetBoard();
        this.player1.resetPlayer();
        this.player2.resetPlayer();
        this.over = false;
        this.turn = true;
    }

}