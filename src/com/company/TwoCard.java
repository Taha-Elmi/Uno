package com.company;

import java.util.Random;

/**
 * represents the cards with the sign '2'
 */
public class TwoCard extends Card{

    /**
     * constructor
     * @param color color of the card
     * @param sign sign of the card
     */
    public TwoCard(Color color, String sign) {
        super(color, sign);
    }

    /**
     * the action of the game
     * @param game the game
     */
    @Override
    public void act(Game game) {

        Random random = new Random();
        int index = random.nextInt(game.getPlayerOnTurn().getCards().size());

        UI.getInstance().choosePlayer(game);
        int playerIndex = GetCommand.getInstance().choosePlayer(game);

        Player player = game.getPlayers().get(playerIndex);
        player.addCard(game.getPlayerOnTurn().getCards().remove(index));
        UI.getInstance().displayPlayerCard(game.getPlayerOnTurn());

        game.setOnColor(getColor());
        game.nextPerson();
    }
}
