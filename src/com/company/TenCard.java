package com.company;

/**
 * represents the cards with the sign '10'
 */
public class TenCard extends Card{

    /**
     * constructor
     * @param color color of the card
     * @param sign sign of the card
     */
    public TenCard(Color color, String sign) {
        super(color, sign);
    }

    /**
     * the action of the game
     * @param game the game
     */
    @Override
    public void act(Game game) {
        game.changeDirection();
        game.nextPerson();
        game.setOnColor(getColor());
    }
}
