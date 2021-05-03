package com.company;

/**
 * represents the non-black cards with the sign '7'
 */
public class RGB_SevenCard extends Card{

    /**
     * constructor
     * @param color color of the card
     * @param sign sign of the card
     */
    public RGB_SevenCard(Color color, String sign) {
        super(color, sign);
    }

    /**
     * the action of the game
     * @param game the game
     */
    @Override
    public void act(Game game) {
        game.addNextPlayerPenalty(2);
        game.nextPerson();
        game.setOnColor(getColor());
    }
}
