package com.company;

import java.util.Random;

/**
 * This class represents a bot as a player
 *
 * @author Taha Elmi
 */
public class Bot extends Player{

    /**
     * constructor
     * @param name the name of the player
     */
    public Bot(String name) {
        super(name);
    }

    /**
     * It will get a move from the bot
     * @return the card that has been chosen
     */
    @Override
    public Card getMove(Game game) {
        for (Card card : getCards()) {
            if (game.checkValidCard(card))
                return card;
        }
        return null;
    }

    /**
     * It will get a 7 cart from the player
     * @param game the game
     * @return the 7 card
     */
    @Override
    public Card get7move(Game game) {
        for (Card card : getCards()) {
            if (card.getSign().equals("7"))
                return card;
        }
        return null;
    }

    /**
     * The bot will choose a color
     * @param game the game
     * @return the chosen color
     */
    @Override
    public Color chooseColor(Game game) {
        Random random = new Random();
        Color toReturn;
        switch (random.nextInt(4)) {
            case 0:
                toReturn = Color.BLACK;
                break;
            case 1:
                toReturn = Color.RED;
                break;
            case 2:
                toReturn = Color.GREEN;
                break;
            default:
                toReturn = Color.BLUE;
        }
        UI.getInstance().printBotChoice(toReturn);
        return toReturn;
    }

    /**
     * It chooses a player in case of the occurrence of a 2 Card
     * @param game the game
     * @return the index of the chosen player
     */
    @Override
    public int choosePlayer(Game game) {
        Random random = new Random();
        int index = random.nextInt(game.getPlayers().size());
        UI.getInstance().printBotChoice(index + 1);
        return index;
    }

}
