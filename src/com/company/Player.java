package com.company;

import java.util.LinkedList;

/**
 * This class represents a player
 *
 * @author Taha Elmi
 */
public abstract class Player {
    private String name;
    private LinkedList<Card> cards;

    public Player(String name) {
        this.name = name;
        cards = new LinkedList<>();
    }

    /**
     * getter of the cards
     * @return the cards list
     */
    public LinkedList<Card> getCards() {
        return cards;
    }

    /**
     * add a specific card to the players deck
     * @param card the card to be added
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * getter of the name field
     * @return the name field
     */
    public String getName() {
        return name;
    }

    /**
     * It will check if the player can do anything or not
     * @param game the game
     * @return true if he can and false otherwise
     */
    public boolean checkIfCanMove(Game game) {
        for (Card card : cards) {
            if (game.checkValidCard(card))
                return true;
        }
        return false;
    }

    /**
     * It adds the first card of the out cards deck to the player's deck
     * @param game the game
     */
    public void addOneCard(Game game) {
        cards.add(game.getDeck().pop());
    }

    /**
     * It will check if the player has any 7 card or not
     * to see if he must pay the penalty or make it heavier
     * @return true if he has a 7 card and false otherwise
     */
    public boolean hasSeven() {
        for (Card card : cards) {
            if (card.getSign().equals("7"))
                return true;
        }
        return false;
    }

    /**
     * It will calculate the score of the player
     * @return the score of the player
     */
    public int calculateScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.value;
        }
        return score;
    }

    /**
     * It will get a move from the player
     * @return the card that has been chosen
     */
    public abstract Card getMove(Game game);

    /**
     * It will get a 7 cart from the player
     * @param game the game
     * @return the 7 card
     */
    public abstract Card get7move(Game game);

    /**
     * The player will choose a color
     * @param game the game
     * @return the chosen color
     */
    public abstract Color chooseColor(Game game);

    /**
     * It chooses a player in case of the occurrence of a 2 Card
     * @param game the game
     * @return the index of the chosen player
     */
    public abstract int choosePlayer(Game game);

}
