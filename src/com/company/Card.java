package com.company;

/**
 * This class represents a card
 *
 * @author Taha Elmi
 */
public abstract class Card {
    private Color color;
    private String sign;
    protected int value;

    public static final String[] signs = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "B", "C", "D"};

    /**
     * constructor
     * @param color color of the card
     * @param sign sign of the card
     */
    public Card(Color color, String sign) {
        this.color = color;
        this.sign = sign;
        this.value = switch (sign){
            case "A" -> 11;
            case "B", "C" -> 12;
            case "D" -> 13;
            case "7" -> 10;
            default -> Integer.parseInt(sign);
        };
    }

    /**
     * the action of the card
     * It will be implemented by the subclasses
     * @param game the game
     */
    public abstract void act(Game game);

    /**
     * getter of the Color field
     * @return the color field
     */
    public Color getColor() {
        return color;
    }

    /**
     * getter of the sign field
     * @return the sign field
     */
    public String getSign() {
        return sign;
    }
}
