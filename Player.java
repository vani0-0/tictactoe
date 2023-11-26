/**
 * The `tictactoe.Player` class represents a player in a game.
 */
public class Player {
    private final String name;
    private final String character;
    private Coordinate mySet;

    /**
     * Constructs a new player with the given name and character.
     *
     * @param name      The name of the player.
     * @param character The character associated with the player (e.g., "X" or "O").
     */
    Player(String name, String character) {
        this.name = name;
        this.character = character;
        this.mySet = new Coordinate();
    }

    /**
     * Retrieves the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the set of coordinates where the player has made moves on the game board.
     *
     * @return The set of coordinates representing the player's moves on the game board.
     */
    public Coordinate getMySet() {
        return this.mySet;
    }

    /**
     * Retrieves the character associated with the player.
     *
     * @return The character of the player.
     */
    public String getCharacter() {
        return this.character;
    }

    /**
     * Returns the number of coordinates where the player has made a move on the game board.
     *
     * @return The number of coordinates with the player's moves.
     */
    public int getSizeSet() {
        return this.mySet.size();
    }

    /**
     * Checks if the player has taken a move at the specified coordinate on the game board.
     *
     * @param x The X-coordinate on the game board.
     * @param y The Y-coordinate on the game board.
     * @return True if the player has taken a move at the specified coordinate, otherwise false.
     */
    public Boolean isSetTaken(int x, int y) {
        String set = this.mySet.get(x, y);
        if (set == null) {
            return false;
        }
        return set.contains(this.character);
    }

    /**
     * Records the player's move at the specified coordinate on the game board.
     *
     * @param x The X-coordinate on the game board.
     * @param y The Y-coordinate on the game board.
     */
    public void setMySet(int x, int y) {
        this.mySet.add(x, y, this.character);
    }

    /**
     * Removes the player's move from the specified coordinate on the game board.
     *
     * @param x The X-coordinate on the game board.
     * @param y The Y-coordinate on the game board.
     */
    public void removeMySet(int x, int y) {
        this.mySet.remove(x, y);
    }

    /**
     * Resets the player's set of moves on the game board to an empty state.
     */
    public void resetPlayer() {
        this.mySet = new Coordinate();
    }
}
