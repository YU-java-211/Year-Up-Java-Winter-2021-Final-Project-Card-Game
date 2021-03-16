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
        String firstPlayer = "";
        for (int j = 0; j < players.size(); j++) {
            Hand hand = players.get(j).getHand();
            for (int i = 0; i < hand.size(); i++) {
                Card card = hand.getCard(i);
                if (card.equals(threeOfSpades)) {
                    firstPlayer = players.get(j).getName();
                    Player temp = players.get(0);
                    players.set(0, players.get(j));
                    players.set(j, temp);

                    discardPile.addCard(card);
                    hand.popCard(i);
                    System.out.println("Discards:");
                    System.out.println(discardPile.getCard(0));
                }
            }
        }

    }

    public void playCard(Hand hand, Card card){
        discardPile.addCard(card);
        //add pop thing

    }

    public void logic(Player player){
        Card prev = discardPile.lastCard();
        //find first card thats higher regardless of strategy
        //put card on discard pile
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

    public void playGame(){
        findLowest();
        displayState();
        Player playerTurn = players.get(0);
        while(!isDone()){
            //actual game logic
            playerTurn = nextPlayer(playerTurn);
        }
    }

    public static void main(String[] args) {
        Thirteen game = new Thirteen();
        // System.out.println(threeOfSpades);
        game.playGame();

    }
}
