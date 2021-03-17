import java.util.Scanner;
import java.util.ArrayList;

public class Thirteen {
    private Scanner scnr;
    private ArrayList<Player> players;
    private Hand discardPile;
    private Card threeOfSpades;
    private Player lastPlayer;

    public Thirteen() {
        threeOfSpades = new Card(3, 3);
        Deck deck = new Deck("Deck");
        discardPile = new Hand("Discards");
        deck.shuffle();

        players = new ArrayList<Player>();
        scnr = new Scanner(System.in);

        System.out.println("Please enter the name of each of your players on a seperate line");

        for (int i = 0; i < 4; i++) {
            if (scnr.hasNextLine()) {
                String playerName = scnr.nextLine();
                players.add((new Player(playerName)));
            }

        }

        for (Player player : players) {
            deck.deal(player.getHand(), 13);
        }

    }

    public void displayState() {
        for (Player player : players) {
            player.display();
        }
        discardPile.display();
    }

    public boolean isDone() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void findLowest() {
        for (int j = 0; j < players.size(); j++) {
            Hand hand = players.get(j).getHand();
            for (int i = 0; i < hand.size(); i++) {
                Card card = hand.getCard(i);
                if (card.equals(threeOfSpades)) {
                    lastPlayer = players.get(j);
                    Player temp = players.get(0);
                    players.set(0, players.get(j));
                    players.set(j, temp);

                    discardPile.addCard(card);
                    hand.popCard(i);
                    System.out.println(discardPile.getCard(0));
                }
            }
        }

    }

    public void playCard(Hand hand, Card card) {

        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.getCard(i);
            if (currentCard.equals(card)) {
                // hand.popCard(i); // removes card from hand
                System.out.println("Playing: " + currentCard.toString());
                discardPile.addCard(currentCard);
                hand.popCard(i); // adds card to discard pile
                break;
            }
        }

    }

    public void logic(Player player) {
        displayState();
        Card prev = discardPile.lastCard();
        Hand currentHand = player.getHand();
        // find first card thats higher regardless of strategy
        System.out.println(player.getName());
        for (int i = 0; i < currentHand.size(); i++) {
            if (player.getName() == lastPlayer.getName()) {
                playCard(currentHand, currentHand.getCard(i));
                prev = discardPile.lastCard();
                break;
            }
            if (currentHand.getCard(i).getRank() >= prev.getRank()) { // if card is higher than previous card
                lastPlayer = player;
                playCard(currentHand, currentHand.getCard(i));
                prev = discardPile.lastCard();

                break;
            } else {
                System.out.println("current card not played");
            }
        }

    }

    public Player nextPlayer(Player current) {

        if (players.indexOf(current) == players.size() - 1) {
            System.out.println("Reached end of array");

            return players.get(0);
        }

        else if (players.indexOf(current) < players.size()) {
            int playerIndex = players.indexOf(current) + 1;
            System.out.println("Moving from " + current.getName() + " to " + players.get(playerIndex).getName());

            return players.get(playerIndex);
        } else {
            System.out.println("Something went wrong");
            return current;
        }

        /*
         * int playerIndex = players.indexOf(current);
         * 
         * if (playerIndex != -1) { return players.get(playerIndex + 1);
         * System.out.println("Moving onto : "+players.get(playerIndex + 1).getName());
         * } else { System.out.println("Oh no, we lost a player!!"); // build better
         * default later!! return current; }
         */
    }

    public void playGame() {
        findLowest();
        displayState();
        Player playerTurn = players.get(0);
        while (!isDone()) {
            logic(playerTurn); // actual game logic
            playerTurn = nextPlayer(playerTurn);
        }
    }

    public static void main(String[] args) {
        Thirteen game = new Thirteen();
        // System.out.println(threeOfSpades);
        game.playGame();

    }
}
