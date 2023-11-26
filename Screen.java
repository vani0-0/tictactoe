import java.util.Scanner;

/**
 * The `tictactoe.Screen` class provides methods for displaying text-based
 * menus, game screens, and messages.
 */
public class Screen {
    private final Scanner scanner;
    private final String reset = "\u001B[0m";
    private final String red = "\u001B[31m";
    private final String green = "\u001B[32m";

    /**
     * Constructs a new `tictactoe.Screen` object.
     */
    public Screen() {
        scanner = new Scanner(System.in);
    }

    public void clearScreen() {
        // Clear the console screen by printing multiple newline characters
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Displays the main menu and prompts the user for a choice.
     *
     * @return The user's menu choice.
     */
    public int mainMenu() {
        while (true) {
            clearScreen();
            System.out.println("-----Welcome to Tic Tac Toe v2--------");
            System.out.println("| [1]: Start                         |");
            System.out.println("| [2]: Exit                          |");
            System.out.println("--------------------------------------");
            System.out.print(">>  ");
            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= 2) {
                scanner.nextLine();
                return choice;
            } else {
                System.out.println(red + "Invalid input, please try again. \n\n" + reset);
            }
        }
    }

    /**
     * Prompts the user to enter their name.
     *
     * @param someText The text to display before the input prompt.
     * @return The user's entered name.
     */
    public String nameInput(String someText) {
        clearScreen();
        System.out.println(someText);
        System.out.println("Enter your name: ");
        System.out.print(">>  ");
        return scanner.nextLine();
    }

    /**
     * Prompts the user to enter their preferred character.
     *
     * @return The user's entered preferred character.
     */
    public String characterInput() {
        while (true) {
            System.out.println("Enter your preferred character: ");
            System.out.print(">> ");
            String input = scanner.nextLine();
            if (input.length() == 1) {
                return input;
            } else {
                System.out.println(red + "Please enter only 1 character\n\n" + reset);
            }
        }
    }

    /**
     * Displays an exit screen with a thank-you message.
     */
    public void exitScreen() {
        clearScreen();
        System.out.println("-----------------EXIT-----------------");
        System.out.println("|       " + green + "thank you for playing" + reset + "        |");
        System.out.println("--------------------------------------");
    }

    /**
     * Displays the current player's name.
     *
     * @param player The name of the current player.
     */
    public void displayName(String player) {
        System.out.printf("Player: %s's turn \n", player);
    }

    /**
     * Displays the game board with provided rows.
     *
     * @param firstRow  The first row of the game board.
     * @param secondRow The second row of the game board.
     * @param thirdRow  The third row of the game board.
     */
    public void board(String firstRow, String secondRow, String thirdRow) {
        clearScreen();
        System.out.printf("%s     1     2     3   %s\n", green, reset);
        System.out.println("   +-----+-----+-----+");
        System.out.printf("%s 1 %s%s\n", green, reset, firstRow);
        System.out.println("   +-----+-----+-----+");
        System.out.printf("%s 2 %s%s\n", green, reset, secondRow);
        System.out.println("   +-----+-----+-----+");
        System.out.printf("%s 3 %s%s\n", green, reset, thirdRow);
        System.out.println("   +-----+-----+-----+");
    }

    /**
     * Displays a loading screen with a given message and loading animation.
     *
     * @param message The loading message to display.
     */
    public void displayLoadingScreen(String message) {
        String[] loadingAnimation = { ".", "..", "...", "....", "....->" };
        int animationIndex = 0;

        System.out.print(message + "...");

        // Simulate loading animation
        for (int i = 0; i < 4; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }

            System.out.print(loadingAnimation[animationIndex]);
            animationIndex = (animationIndex + 1) % loadingAnimation.length;

            // Move the cursor back to overwrite the previous output
            for (int j = 0; j < loadingAnimation[animationIndex].length(); j++) {
                System.out.print(".");
            }
        }

        System.out.println("\n" + green + "Loading complete!" + reset);
        System.out.println("-----------------------------------");
    }

    /**
     * Prompts the user to enter coordinates for their move.
     *
     * @return An array of two integers representing the X and Y coordinates.
     */
    public int[] getCoordinate() {
        int x, y;
        while (true) {
            System.out.print("Enter coordinates (e.g., '1, 2'): ");
            try {
                String input = scanner.nextLine();
                String[] parts = input.split("[,\\s]+");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Please enter 2 numbers separated by space.\n\n");
                }
                x = Integer.parseInt(parts[0]);
                y = Integer.parseInt(parts[1]);
                if ((x > 3 || y > 3) || (x < 0 || y < 0)) {
                    throw new IllegalArgumentException("Both numbers must be less than or equal to 3.\n\n");
                }
                return new int[] { x, y };
            } catch (NumberFormatException e) {
                System.out.println(red + "Invalid input. Please enter 2 valid numbers.\n\n" + reset);
            } catch (IllegalArgumentException e) {
                System.out.println(red + e.getMessage() + reset);
            }
        }
    }

    /**
     * Displays a message indicating that the match has ended due to a winning move
     * type.
     *
     * @param i The winning move type (0 for vertical, 1 for horizontal, 2 for
     *          diagonal).
     */
    public void displayMatchOver(int i) {
        System.out.println(i);
        // String winningMove = switch (i) {
        // case 0 -> "Vertical";
        // case 1 -> "Horizontal";
        // case 2 -> "Diagonal";
        // default -> null;
        // };
        String winningMove = "";
        switch (i) {
            case 0: {
                winningMove = "Vertical";
                break;
            }
            case 1: {
                winningMove = "Horizontal";
                break;
            }
            case 2: {
                winningMove = "Diagonal";
                break;
            }
            default: {
                break;
            }
        }
        System.out.printf("%sMatch ended by %s move!%s\n\n", green, winningMove, reset);
    }

    /**
     * Displays a message to announce the winner of the game round.
     *
     * @param winner The name of the winning player.
     */
    public void displayWinner(String winner) {
        System.out.printf("%sThe winner of this round is %s!!\nCongratulations!!%s\n\n", green, winner, reset);
    }

    /**
     * Displays a menu for restarting the game, allowing the user to choose between
     * a rematch or exiting the game.
     *
     * @return The user's choice (1 for rematch, 2 for exit).
     */
    public int restartGame() {
        while (true) {
            clearScreen();
            System.out.println("--------------------------------------");
            System.out.println("| Would you like a rematch?          |");
            System.out.println("| [1]: Yes                           |");
            System.out.println("| [2]: Exit Game                     |");
            System.out.println("--------------------------------------");
            System.out.print(">>  ");
            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= 2) {
                scanner.nextLine();
                return choice;
            } else {
                System.out.println(red + "Invalid input, please try again. \n\n" + reset);
            }
        }
    }
}
