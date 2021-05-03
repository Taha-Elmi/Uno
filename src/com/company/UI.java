package com.company;

import java.io.IOException;

/**
 * This class handles everything related to UI
 * Which means every System.out.print and such things in this application is stored here
 *
 * It uses the Singleton design pattern
 *
 * @author Taha Elmi
 */
public class UI {
    private static UI INSTANCE = null;

    public static UI getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UI();
        return INSTANCE;
    }

    public void welcome() {
        System.out.println("Hey :)");
        System.out.println("Welcome to our islamic dirty-seven or Uno.");
        System.out.println("You can either play it with bots, or with other players.");
        System.out.println("The number of players can be 3, 4 or 5.");
        System.out.println();
    }

    public void askForGameMode() {
        System.out.println("Do you want to play in single-player mode, or multiplayer?");
        System.out.println("single-player: 1");
        System.out.println("multi-player: 2");
    }

    public void askForNumberOfPlayers() {
        System.out.println("How many players do you want to attend the game? ( 3 - 4 - 5 )");
    }

    public void clearConsole() throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public void nameMessage(int index) {
        System.out.println("Player n." + index + ", please enter your name:");
    }

    public void nameMessage() {
        System.out.println("Enter your name:");
    }

    public void displayPlayers(Game game) {

        if (game.getDirection() == Game.Direction.CLOCKWISE)
            System.out.println("\u2193");
        else
            System.out.println("\u2191");

        for (Player player : game.getPlayers()) {
            if (game.getPlayerOnTurn().equals(player))
                System.out.print("* ");
            System.out.println(player.getName() + " with " + player.getCards().size() + " cards");
        }
        System.out.println();
    }

    public void displayCard(Card card) {
        String displayColor = switch (card.getColor()) {
            case RED -> ConsoleColors.RED;
            case BLUE -> ConsoleColors.BLUE;
            case GREEN -> ConsoleColors.GREEN;
            case BLACK -> ConsoleColors.WHITE;
        };

        System.out.println(ConsoleColors.WHITE_BACKGROUND + displayColor + "\u250D\u2501\u2501\u2501\u2501\u2501\u2511");
        System.out.println("\u2502     \u2502");

        if (!card.getSign().equals("10"))
            System.out.println("\u2502  " + card.getSign() + "  \u2502");
        else
            System.out.println("\u2502 " + card.getSign() + "  \u2502");

        System.out.println("\u2502     \u2502");
        System.out.println("\u2515\u2501\u2501\u2501\u2501\u2501\u2519" + ConsoleColors.RESET);

    }

    public void displayPlayerCard(Player player) {
        for (Card card : player.getCards()) {
            String displayColor = switch (card.getColor()) {
                case RED -> ConsoleColors.RED;
                case BLUE -> ConsoleColors.BLUE;
                case GREEN -> ConsoleColors.GREEN;
                case BLACK -> ConsoleColors.WHITE;
            };
            System.out.print(displayColor + "\u250D" + "\u2501\u2501\u2501\u2501");
        }
        System.out.println("\u2501" + "\u2511");

        for (Card card : player.getCards()) {
            String displayColor = switch (card.getColor()) {
                case RED -> ConsoleColors.RED;
                case BLUE -> ConsoleColors.BLUE;
                case GREEN -> ConsoleColors.GREEN;
                case BLACK -> ConsoleColors.WHITE;
            };
            System.out.print(displayColor + "\u2502" + "    ");
        }
        System.out.println(" " + "\u2502");

        for (Card card : player.getCards()) {
            String displayColor = switch (card.getColor()) {
                case RED -> ConsoleColors.RED;
                case BLUE -> ConsoleColors.BLUE;
                case GREEN -> ConsoleColors.GREEN;
                case BLACK -> ConsoleColors.WHITE;
            };
            if (!card.getSign().equals("10"))
                System.out.print(displayColor + "\u2502" + "  " + card.getSign() + " ");
            else
                System.out.print(displayColor + "\u2502" + " " + card.getSign() + " ");
        }
        System.out.println(" " + "\u2502");

        for (Card card : player.getCards()) {
            String displayColor = switch (card.getColor()) {
                case RED -> ConsoleColors.RED;
                case BLUE -> ConsoleColors.BLUE;
                case GREEN -> ConsoleColors.GREEN;
                case BLACK -> ConsoleColors.WHITE;
            };
            System.out.print(displayColor + "\u2502" + "    ");
        }
        System.out.println(" " + "\u2502");

        for (Card card : player.getCards()) {
            String displayColor = switch (card.getColor()) {
                case RED -> ConsoleColors.RED;
                case BLUE -> ConsoleColors.BLUE;
                case GREEN -> ConsoleColors.GREEN;
                case BLACK -> ConsoleColors.WHITE;
            };
            System.out.print(displayColor + "\u2515" + "\u2501\u2501\u2501\u2501");
        }
        System.out.println("\u2501" + "\u2519" + ConsoleColors.RESET);

        for (int i = 1; i <= player.getCards().size(); i++)
            System.out.printf("  %d  ", i);
        System.out.println();
    }

    public void declareWhoseTurn(Game game) {
        System.out.printf("It's %s's turn.\n", game.getPlayerOnTurn().getName());
    }

    public void printToChooseCard() {
        System.out.println("Choose one of the above cards by its index, written below it.");
    }

    public void errorOutOfRange(Player player) {
        System.out.println("The inserted index is out of range.");
        System.out.println("Please choose a card with the index between 1 and " + player.getCards().size() + ".");
    }

    public void errorWrongInput() {
        System.out.println("Wrong input. Try again.");
    }

    public void printColors() {
        System.out.println("Choose a color by typing one of these: BLACK - R - G - B");
    }

    public void printBotChoice(Color color) {
        System.out.println("I choose " + color.toString());
    }

    public void printBotChoice(int index) {
        System.out.println("I choose player number " + index);
    }

    public void choosePlayer(Game game) {
        System.out.println("Choose a player with his index to give a random card to him.");
        System.out.println("Insert a number between 1 and " + game.getPlayers().size());
    }

    public void printLine() {
        System.out.println("_________________________________________");
    }

    public void gameOver(Game game) {
        System.out.println("Well well well...");
        System.out.println(game.getPlayers().get(0).getName() + " won the game!");
        System.out.println("The scoreboard is here:");
        int index = 1;
        for(Player player : game.getPlayers()) {
            System.out.println(index + "- " + player.getName() + " : " + player.calculateScore());
            index++;
        }
        System.out.println();
        System.out.println("We wish you had a good time :)");
        System.out.println("Bye...");
    }
}
