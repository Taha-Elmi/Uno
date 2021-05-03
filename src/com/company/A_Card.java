package com.company;

/**
 * represents the cards with the sign 'A'
 */
public class A_Card extends Card{

    /**
     * constructor
     * @param color color of the card
     * @param sign sign of the card
     */
    public A_Card(Color color, String sign) {
        super(color, sign);
    }

    /**
     * the action of the game
     * @param game the game
     */
    @Override
    public void act(Game game) {
        game.nextPerson();
        game.nextPerson();
        game.setOnColor(getColor());
    }
}
