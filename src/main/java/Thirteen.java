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

                    playCard(hand, card);

                }
            }
        }

    }

    public void playCard(Hand hand, Card card) {

        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.getCard(i);
            if (currentCard.equals(card)) {
                System.out.println(lastPlayer.getName() + " is playing: " + currentCard.toString());
                discardPile.addCard(currentCard);
                hand.popCard(i); // adds card to discard pile
                break;
            }
        }

    }

    public void logic(Player player) {

        Card prev = discardPile.lastCard();
        Hand currentHand = player.getHand();
        // find first card thats higher regardless of strategy

        for (int i = 0; i < currentHand.size(); i++) {
            Card currentCard = currentHand.getCard(i);
            if (player.getName() == lastPlayer.getName()) {
                playCard(currentHand, currentCard);
                prev = discardPile.lastCard();
                break;
            }
            if (currentCard.getRank() == 2) {
                lastPlayer = player;
                playCard(currentHand, currentHand.getCard(i));
                prev = discardPile.lastCard();

                break;
            }
            if (currentCard.getRank() == 1 && prev.getRank() >= 3) {
                lastPlayer = player;
                playCard(currentHand, currentHand.getCard(i));
                prev = discardPile.lastCard();

                break;
            }
            if (currentCard.getRank() == prev.getRank() && prev.getRank() < 3) {

                lastPlayer = player;
                playCard(currentHand, currentCard);
                prev = discardPile.lastCard();

                break;
            }
            if (currentCard.getRank() >= prev.getRank() && prev.getRank() >= 3) {

                lastPlayer = player;
                playCard(currentHand, currentCard);
                prev = discardPile.lastCard();

                break;
            }
        }

    }

    public Player nextPlayer(Player current) {

        if (players.indexOf(current) == players.size() - 1) {
            return players.get(0);
        }

        else if (players.indexOf(current) < players.size()) {
            int playerIndex = players.indexOf(current) + 1;
            return players.get(playerIndex);
        } else {
            System.out.println("Something went wrong!");
            return current;
        }
    }

    public void playGame() {
        findLowest();
        Player playerTurn = players.get(1);
        while (!isDone()) {
            logic(playerTurn); // actual game logic
            playerTurn = nextPlayer(playerTurn);
        }
        System.out.println("Winner is: " + lastPlayer.getName());
        // displayState();
    }

    public static void main(String[] args) {
        Thirteen game = new Thirteen();
        game.playGame();
    }
}
