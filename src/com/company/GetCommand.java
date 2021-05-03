package com.company;

import java.util.Scanner;

/**
 * This class is used to get appropriate commands from the user
 * It uses the singleton pattern
 *
 * @author Taha Elmi
 */
public class GetCommand {
    private static GetCommand INSTANCE = null;
    private final Scanner scanner;

    private GetCommand(){
        scanner = new Scanner(System.in);
    }

    public static GetCommand getInstance() {
        if (INSTANCE == null)
            INSTANCE = new GetCommand();
        return INSTANCE;
    }

    public int chooseIndex(Player player) {
        int index = scanner.nextInt();
        while (index < 1 || index > player.getCards().size()) {
            UI.getInstance().errorOutOfRange(player);
            index = scanner.nextInt();
        }
        return index - 1;
    }

    public String insertName() {
        return scanner.next();
    }

    public Color chooseColor() {
        String input = scanner.next();
        Color toReturn;
        switch (input) {
            case "BLACK":
                toReturn = Color.BLACK;
                break;
            case "R":
                toReturn = Color.RED;
                break;
            case "G":
                toReturn = Color.GREEN;
                break;
            case "B":
                toReturn = Color.BLUE;
                break;
            default:
                UI.getInstance().errorWrongInput();
                toReturn = chooseColor();
        }
        return toReturn;
    }

    public int choosePlayer(Game game) {
        int index = scanner.nextInt();
        while (index < 0 || index > game.getPlayers().size()) {
            UI.getInstance().errorWrongInput();
            index = scanner.nextInt();
        }
        return index - 1;
    }

    public Game.GameMode askForGameMode() {
        int answer = scanner.nextInt();
        while (answer != 1 && answer != 2) {
            UI.getInstance().errorWrongInput();
            answer = scanner.nextInt();
        }
        if (answer == 1)
            return Game.GameMode.SINGLE_PLAYER;
        else
            return Game.GameMode.MULTI_PLAYER;
    }

    public int askForNumberOfPlayers() {
        int answer = scanner.nextInt();
        while (answer < 3 || answer > 5) {
            UI.getInstance().errorWrongInput();
            answer = scanner.nextInt();
        }
        return answer;
    }

}
