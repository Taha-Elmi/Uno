package com.company;

/**
 * represents the cards with the signs [ 3 - 4 - 5 - 6 - 9 - C - D ]
 */
public class GeneralCard extends Card{

    /**
     * constructor
     * @param color color of the card
     * @param sign sign of the card
     */
    public GeneralCard(Color color, String sign) {
        super(color, sign);
    }

    /**
     * the action of the game
     * @param game the game
     */
    @Override
    public void act(Game game) {
        game.nextPerson();
        game.setOnColor(getColor());
    }
}
