package com.company;

/**
 * represents the cards with the sign 'B'
 */
public class B_Card extends Card{

    /**
     * constructor
     * @param color color of the card
     * @param sign sign of the card
     */
    public B_Card(Color color, String sign) {
        super(color, sign);
    }

    /**
     * the action of the game
     * @param game the game
     */
    @Override
    public void act(Game game) {

        Color chosen = game.getPlayerOnTurn().chooseColor(game);
        game.setOnColor(chosen);

        game.nextPerson();
    }
}
