package com.company;

/**
 * represents the black cards with the sign '7'
 */
public class BlackSevenCard extends Card{

    /**
     * constructor
     * @param color color of the card
     * @param sign sign of the card
     */
    public BlackSevenCard(Color color, String sign) {
        super(color, sign);
        value += 5;
    }

    /**
     * the action of the game
     * @param game the game
     */
    @Override
    public void act(Game game) {
        game.addNextPlayerPenalty(4);
        game.nextPerson();
        game.setOnColor(getColor());
    }
}
