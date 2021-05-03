package com.company;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

/**
 * This class represents a single game
 *
 * @author Taha Elmi
 */
public class Game {
    private int numberOfPlayers;
    private LinkedList<Player> players;
    private Player playerOnTurn;

    private Direction direction;
    private GameMode gameMode;

    private LinkedList<Card> deck;
    private Card onCard;
    private Color onColor;

    private int nextPlayerPenalty;


    public enum Direction {
        CLOCKWISE, ANTICLOCKWISE
    }

    public enum GameMode {
        SINGLE_PLAYER, MULTI_PLAYER
    }

    /**
     * constructor
     * @param numberOfPlayers defines number of the players, should be between 3 and 5
     * @param gameMode defines game mode
     */
    public Game(int numberOfPlayers, GameMode gameMode) {
        this.numberOfPlayers = numberOfPlayers;
        this.gameMode = gameMode;
        direction = Direction.CLOCKWISE;
        nextPlayerPenalty = 0;
        players = new LinkedList<>();
        initializePlayers();
        playerOnTurn = players.get(0);
        deck = new LinkedList<>();
        createDeck();
        distributeCards();
    }

    /**
     * It will greet with the users and determine the game mode and make a game object
     * @return the game object
     */
    public static Game welcome() {
        UI.getInstance().welcome();
        UI.getInstance().askForGameMode();
        GameMode gameMode = GetCommand.getInstance().askForGameMode();
        UI.getInstance().askForNumberOfPlayers();
        int number = GetCommand.getInstance().askForNumberOfPlayers();
        return new Game(number, gameMode);
    }

    /**
     * It will initialize the players to the game
     */
    public void initializePlayers() {
        if (gameMode == GameMode.SINGLE_PLAYER) {
            UI.getInstance().nameMessage();
            addPlayer(new Human(GetCommand.getInstance().insertName()));
            for (int i = 1; i < numberOfPlayers; i++)
                addPlayer(new Bot("Bot" + i));
        } else {
            for (int i = 1; i <= numberOfPlayers; i++) {
                UI.getInstance().nameMessage(i);
                addPlayer(new Human(GetCommand.getInstance().insertName()));
            }
        }
    }

    /**
     * add a player to the game
     * @param player the player to be added
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * It will create all the cards for the first time
     */
    public void createDeck() {
        for (Color color : Color.values()) {
            for (String sign : Card.signs) {

                if (sign.equals("2"))
                    deck.add(new TwoCard(color, sign));
                else if (sign.equals("7") && color == Color.BLACK)
                    deck.add(new BlackSevenCard(color, sign));
                else if (sign.equals("7"))
                    deck.add(new RGB_SevenCard(color, sign));
                else if (sign.equals("8"))
                    deck.add(new EightCard(color, sign));
                else if (sign.equals("10"))
                    deck.add(new TenCard(color, sign));
                else if (sign.equals("A"))
                    deck.add(new A_Card(color, sign));
                else if (sign.equals("B"))
                    deck.add(new B_Card(color, sign));
                else
                    deck.add(new GeneralCard(color, sign));

            }
        }
        randomizeDeck();
    }

    /**
     * It will randomize the cards
     */
    private void randomizeDeck() {
        LinkedList<Card> temp = new LinkedList<>();
        Random random = new Random();
        while (deck.size() != 0) {
            temp.add(deck.remove(random.nextInt(deck.size())));
        }
        deck = temp;
    }

    /**
     * It will distribute the cards between players
     * and choose a card for the middle card
     */
    public void distributeCards() {
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.addCard(deck.pop());
            }
        }
        onCard = deck.pop();
        onColor = onCard.getColor();
    }

    /**
     * It will change the direction of the game
     */
    public void changeDirection() {
        direction = (direction == Direction.CLOCKWISE) ? Direction.ANTICLOCKWISE : Direction.CLOCKWISE;
    }

    /**
     * It will give the turn to the next player
     * based on the direction of the game
     */
    public void nextPerson() {
        int currentIndex = players.indexOf(playerOnTurn);

        playerOnTurn = switch (direction) {
            case CLOCKWISE -> ((currentIndex < players.size() - 1) ? players.get(currentIndex + 1) : players.get(0));
            case ANTICLOCKWISE -> ((currentIndex != 0) ? players.get(currentIndex - 1) : players.get(players.size() - 1));
        };
    }

    /**
     * It will check if the chosen card is valid or not
     * @param card the chosen card
     * @return true if it's valid and false otherwise
     */
    public boolean checkValidCard(Card card) {
        return (card.getSign().equals(onCard.getSign()) || card.getColor() == onColor);
    }

    /**
     * It will check if the game is finished or not
     * @return true if finished and false otherwise
     */
    public boolean checkIfFinished() {
        for (Player player : players) {
            if (player.getCards().size() == 0)
                return true;
        }
        return false;
    }

    /**
     * getter of the playerOnTurn field
     * @return the playerOnTurn field
     */
    public Player getPlayerOnTurn() {
        return playerOnTurn;
    }

    /**
     * getter of the players list
     * @return the list of the players
     */
    public LinkedList<Player> getPlayers() {
        return players;
    }

    /**
     * getter of the direction of the game
     * @return the direction of the game
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * getter of the deck of the out cards
     * @return the list of the deck
     */
    public LinkedList<Card> getDeck() {
        return deck;
    }

    /**
     * It will add to the penalty that next players will pay
     * @param nextPlayerPenalty the number of cards the next player will get
     */
    public void addNextPlayerPenalty(int nextPlayerPenalty) {
        this.nextPlayerPenalty += nextPlayerPenalty;
    }

    /**
     * setter of the nextPlayerPenalty field
     * @param nextPlayerPenalty the nextPlayerPenalty field
     */
    public void setNextPlayerPenalty(int nextPlayerPenalty) {
        this.nextPlayerPenalty = nextPlayerPenalty;
    }

    /**
     * setter of the onColor
     * @param onColor the new Color
     */
    public void setOnColor(Color onColor) {
        this.onColor = onColor;
    }

    /**
     * checks if the next player has any penalty or not
     * @return true if there is any penalty and false otherwise
     */
    public boolean checkPenalty() {
        return nextPlayerPenalty != 0;
    }

    /**
     * The penalty operation
     */
    public void penalty() {
        Card currentCard;

        if (playerOnTurn.hasSeven()) {

            currentCard = playerOnTurn.get7move(this);

        } else {

            for (int i = 0; i < nextPlayerPenalty; i++) {
                playerOnTurn.addOneCard(this);
            }
            setNextPlayerPenalty(0);

            UI.getInstance().displayPlayerCard(playerOnTurn);
            if (playerOnTurn.checkIfCanMove(this)) {
                currentCard = playerOnTurn.getMove(this);
            } else {
                nextPerson();
                return;
            }

        }
        updateCards(currentCard);
        currentCard.act(this);
    }

    /**
     * It will update the deck of the player and the middle card
     * @param card the card that has been chosen by the player
     */
    public void updateCards(Card card) {
        deck.addLast(onCard);
        onCard = card;
        playerOnTurn.getCards().remove(card);
    }

    /**
     *The game loop
     */
    public void play() throws InterruptedException, IOException {
        while (!checkIfFinished()) {
            //UI.getInstance().clearConsole();
            UI.getInstance().displayPlayers(this);
            UI.getInstance().declareWhoseTurn(this);
            UI.getInstance().displayCard(onCard);
            UI.getInstance().displayPlayerCard(playerOnTurn);

            if (checkPenalty()) {
                penalty();
                continue;
            }

            Card currentCard;
            if (playerOnTurn.checkIfCanMove(this)) {
                currentCard = playerOnTurn.getMove(this);
            } else {
                playerOnTurn.addOneCard(this);
                UI.getInstance().displayPlayerCard(playerOnTurn);
                if (playerOnTurn.checkIfCanMove(this)) {
                    currentCard = playerOnTurn.getMove(this);
                } else {
                    nextPerson();
                    UI.getInstance().printLine();
                    continue;
                }
            }
            updateCards(currentCard);
            currentCard.act(this);
            UI.getInstance().printLine();
        }
        gameOver();
    }

    /**
     * It will change one player with its next player in the players list
     * @param players the list
     * @param index the player to be changed with its next one
     */
    public void changePlayers(LinkedList<Player> players, int index) {
        Player player = players.get(index);
        players.set(index, players.get(index + 1));
        players.set(index + 1, player);
    }

    /**
     * It will sort the players
     * It uses Bubble Sort
     */
    public void sortPlayers() {
        for (int i = players.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (players.get(j).calculateScore() > players.get(j + 1).calculateScore())
                    changePlayers(players, j);
            }
        }
    }

    /**
     * Declaring the winner and the scoreboard
     */
    public void gameOver() {
        sortPlayers();
        UI.getInstance().gameOver(this);
    }
}
