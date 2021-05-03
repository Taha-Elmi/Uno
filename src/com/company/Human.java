package com.company;

/**
 * This class represents a human as a player
 *
 * @author Taha Elmi
 */
public class Human extends Player{

    /**
     * constructor
     * @param name the name of the player
     */
    public Human(String name) {
        super(name);
    }

    /**
     * It will get a move from the player
     * @return the card that has been chosen
     */
    @Override
    public Card getMove(Game game) {
        Card currentCard;
        do {
            UI.getInstance().printToChooseCard();
            int index = GetCommand.getInstance().chooseIndex(game.getPlayerOnTurn());
            currentCard = game.getPlayerOnTurn().getCards().get(index);
        } while (!game.checkValidCard(currentCard));
        return currentCard;
    }

    /**
     * It will get a 7 cart from the player
     * @param game the game
     * @return the 7 card
     */
    @Override
    public Card get7move(Game game) {
        Card currentCard;
        do {
            UI.getInstance().printToChooseCard();
            int index = GetCommand.getInstance().chooseIndex(game.getPlayerOnTurn());
            currentCard = game.getPlayerOnTurn().getCards().get(index);
        } while (!currentCard.getSign().equals("7"));
        return currentCard;
    }

    /**
     * The player will choose a color
     * @param game the game
     * @return the chosen color
     */
    @Override
    public Color chooseColor(Game game) {
        UI.getInstance().printColors();
        return GetCommand.getInstance().chooseColor();
    }

    /**
     * It chooses a player in case of the occurrence of a 2 Card
     * @param game the game
     * @return the index of the chosen player
     */
    @Override
    public int choosePlayer(Game game) {
        UI.getInstance().choosePlayer(game);
        return GetCommand.getInstance().choosePlayer(game);
    }
}
