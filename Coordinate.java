import java.util.ArrayList;
import java.util.List;

/**
 * The `tictactoe.Coordinate` class represents a collection of coordinate points along with associated data.
 * It allows adding and retrieving data for specific coordinates.
 */
public class Coordinate {
    private static class CoordinateData {
        int x;
        int y;
        String data;

        CoordinateData(int x, int y, String data) {
            this.x = x;
            this.y = y;
            this.data = data;
        }
    }

    private final List<CoordinateData> dataList;

    /**
     * Constructs a new `tictactoe.Coordinate` object, initializing an empty list of coordinate data.
     */
    public Coordinate() {
        dataList = new ArrayList<>();
    }

    /**
     * Adds data for a specific coordinate.
     *
     * @param x         The X-coordinate.
     * @param y         The Y-coordinate.
     * @param character The data associated with the coordinate.
     */
    public void add(int x, int y, String character) {
        CoordinateData coordinateData = new CoordinateData(x, y, character);
        dataList.add(coordinateData);
    }

    /**
     * Retrieves the index of data for a specific coordinate.
     *
     * @param x The X-coordinate.
     * @param y The Y-coordinate.
     * @return The index of the specified coordinate's data or -1 if not found.
     */
    public int getIndex(int x, int y) {
        for (int i = 0; i < dataList.size(); i++) {
            CoordinateData coordinateData = dataList.get(i);
            if (coordinateData.x == x && coordinateData.y == y) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes data for a specific coordinate.
     *
     * @param x The X-coordinate.
     * @param y The Y-coordinate.
     */
    public void remove(int x, int y) {
        int index = this.getIndex(x, y);
        dataList.remove(index);
    }

    /**
     * Retrieves data for a specific coordinate.
     *
     * @param x The X-coordinate.
     * @param y The Y-coordinate.
     * @return The data associated with the specified coordinate or null if not found.
     */
    public String get(int x, int y) {
        for (CoordinateData coordinateData : dataList) {
            if (coordinateData.x == x && coordinateData.y == y) {
                return coordinateData.data;
            }
        }
        return null;
    }

    /**
     * Returns the number of coordinate data entries in the collection.
     *
     * @return The size of the coordinate data collection.
     */
    public int size() {
        return this.dataList.size();
    }

    /**
     * Counts the number of matching coordinates between this `Coordinate` and a player's set of coordinates.
     *
     * @param playerSet The set of coordinates representing a player's moves on the game board.
     * @return The count of matching coordinates between this `Coordinate` and the player's set.
     */
    public int countMatching(Coordinate playerSet) {
        int matchCount = 0;
        for (CoordinateData coordinateData : dataList) {
            for (CoordinateData playerData : playerSet.dataList) {
                if (coordinateData.x == playerData.x && coordinateData.y == playerData.y) {
                    matchCount += 1;
                }
            }
        }
        return matchCount;
    }
}
