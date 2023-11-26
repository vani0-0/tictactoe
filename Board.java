/**
 * The `tictactoe.Board` class represents the game board for Tic-Tac-Toe.
 */
public class Board {
    private Coordinate free;
    private final Screen gameScreen;

    /**
     * Constructs a new `tictactoe.Board` object.
     *
     * @param myScreen The tictactoe.Screen on which the board is displayed.
     */
    public Board(Screen myScreen) {
        this.gameScreen = myScreen;
        this.free = new Coordinate();
        this.initialize();
    }

    /**
     * Initializes the game board with empty (FREE) spaces.
     */
    private void initialize() {
        this.free.add(1, 1, " ");
        this.free.add(1, 2, " ");
        this.free.add(1, 3, " ");
        this.free.add(2, 1, " ");
        this.free.add(2, 2, " ");
        this.free.add(2, 3, " ");
        this.free.add(3, 1, " ");
        this.free.add(3, 2, " ");
        this.free.add(3, 3, " ");
    }

    /**
     * Resets the game board by clearing the free (unoccupied) coordinates and initializing the board.
     */
    public void resetBoard() {
        this.free = new Coordinate();
        this.initialize();
    }

    /**
     * Displays the current state of the game board on the screen.
     */
    public void displayBoard() {
        String firstRow = String.format("|  %s  |  %s  |  %s  |",
                free.get(1, 1), free.get(1, 2), free.get(1, 3));
        String secondRow = String.format("|  %s  |  %s  |  %s  |",
                free.get(2, 1), free.get(2, 2), free.get(2, 3));
        String thirdRow = String.format("|  %s  |  %s  |  %s  |",
                free.get(3, 1), free.get(3, 2), free.get(3, 3));
        this.gameScreen.board(firstRow, secondRow, thirdRow);
    }

    /**
     * Checks if the specified cell on the board is set to "FREE."
     *
     * @param x The X-coordinate of the cell (1, 2, or 3).
     * @param y The Y-coordinate of the cell (1, 2, or 3).
     * @return True if the cell is free, otherwise false.
     */
    public Boolean isFreeSet(int x, int y) {
        return this.free.get(x, y).contains(" ");
    }

    /**
     * Updates the character set in the specified cell on the board.
     *
     * @param x        The X-coordinate of the cell (1, 2, or 3).
     * @param y        The Y-coordinate of the cell (1, 2, or 3).
     * @param character The character to set in the cell.
     */
    public void updateSet(int x, int y, String character) {
        this.free.remove(x, y);
        this.free.add(x, y, character);
    }
}
