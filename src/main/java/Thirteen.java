import java.util.Scanner;
import java.util.ArrayList;

public class Thirteen {
    private Scanner scnr;
    private ArrayList<Player> players;
    private Hand discardPile;
    private Card threeOfSpades;

    public Thirteen() {
        threeOfSpades = new Card(3, 3);
        Deck deck = new Deck("Deck");
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
    }

    public boolean isDone() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public String whosFirst(ArrayList<Player> players) {
        String firstPlayer = "";
        for (Player player : players) {
            Hand currentPlayerHand = player.getHand();
            for (int i = 0; i < 12; i++) {
                if (currentPlayerHand.getCard(i).equals(threeOfSpades)) {
                    firstPlayer = player.getName();
                }
            }
        }
        return firstPlayer;
    }

    public Player nextPlayer(Player current) {

        if (players.indexOf(current) == players.size() - 1) {
            return players.get(0);
        }

        int playerIndex = players.indexOf(current);

        if (playerIndex != -1) {
            return players.get(playerIndex + 1);
        } else {
            System.out.println("Oh no, we lost a player!!");
            // build better default later!!
            return current;
        }
    }

    public static void main(String[] args) {
        Thirteen game = new Thirteen();
        // System.out.println(threeOfSpades);
        game.displayState();
        System.out.println(game.whosFirst(game.players) + " will go first!");

        // game.playGame();
    }
}
