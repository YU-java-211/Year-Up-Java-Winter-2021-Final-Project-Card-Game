//package uno;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Simulates a game of Crazy Eights.
 * See https://en.wikipedia.org/wiki/Crazy_Eights.
 */
public class UnoCard {

    //private Player one;
    //private Player two;
    private ArrayList<Player> players;
    private Hand drawPile;
    private Hand discardPile;
    private Scanner in;

    /**
     * Initializes the state of the game.
     */
    public UnoCard() {

        Deck deck = new Deck("Deck");
        deck.shuffle();
        
        // set up array list
        players = new ArrayList<Player>();

        // set up Scanner
        in = new Scanner(System.in);

        System.out.println("Please enter the name of each of your players on a seperate line");

        for (int i = 0; i < 4; i++){
            String playerName = in.nextLine();
            players.add((new Player(playerName)));
        }

        for (Player player : players) {
            deck.deal(player.getHand(), 5);
        }

        // deal cards to each player



        /*one = new Player("Allen");
        deck.deal(one.getHand(), 5);
        two = new Player("Chris");
        deck.deal(two.getHand(), 5);
        */

        // turn one card face up
        discardPile = new Hand("Discards");
        deck.deal(discardPile, 1);

        // put the rest of the deck face down
        drawPile = new Hand("Draw pile");
        deck.dealAll(drawPile);

        // create the scanner we'll use to wait for the user
        //in = new Scanner(System.in);
    }

    /**
     * Returns true if either hand is empty.
     */
    public boolean isDone() {
        //return one.getHand().isEmpty() || two.getHand().isEmpty();
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Moves cards from the discard pile to the draw pile and shuffles.
     */
    public void reshuffle() {
        // save the top card
        Card prev = discardPile.popCard();

        // move the rest of the cards
        discardPile.dealAll(drawPile);

        // put the top card back
        discardPile.addCard(prev);

        // shuffle the draw pile
        drawPile.shuffle();
    }

    /**
     * Returns a card from the draw pile.
     */
    public Card drawCard() {
        if (drawPile.isEmpty()) {
            reshuffle();
        }
        return drawPile.popCard();
    }

    /**
     * Switches players.
     */
    public Player nextPlayer(Player current) {
        
        if (players.indexOf(current) == players.size() -1) {
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
        
        /*if (current == one) {
            return two;
        } else {
            return one;
        }
        */
    }

    /**
     * Displays the state of the game.
     */
    public void displayState() {
        for (Player player : players) {
            player.display();
        }

        discardPile.display();
        System.out.print("Draw pile: ");
        System.out.println(drawPile.size() + " cards");
        /* Disabling for easier automation
        in.nextLine();
        */
    }

    /**
     * One player takes a turn.
     */
    public void takeTurn(Player player) {
        Card prev = discardPile.lastCard();
        Card next = playCard(player, prev);
        discardPile.addCard(next);

        System.out.println(player.getName() + " plays " + next);
        System.out.println();
    }
    
    public Card playCard(Player player, Card prev){
      Hand playerHand = player.getHand();
      for (int i = 0; i < playerHand.size(); i++){
         if (playerHand.getCard(i).getRank() == prev.getRank() || playerHand.getCard(i).getSuit() == prev.getSuit()){
            if (playerHand.size() == 2){
               System.out.println(player.getName() + " says UNO!");
            }
            return playerHand.popCard(i);
         }
      }
      if (drawPile.size() > 0){
         drawPile.deal(playerHand, 1);
      }
      else{
         reshuffle();
      }
      return playCard(player, prev);
    }

    /**
     * Plays the game.
     */
    public void playGame() {
        // changed from one to players.get(0);
        // then rename the player in this to playerTurn so it doesn't clash
        // with other code we have in our foreach looops
        Player playerTurn = players.get(players.size() - 1);

        // keep playing until there's a winner
        while (!isDone()) {
            playerTurn = nextPlayer(playerTurn);
            displayState();
            takeTurn(playerTurn);
        }

        
        System.out.println("Congrats " + playerTurn.getName() + ", you've won!");
        /* one.displayScore();
        two.displayScore();
        */
    }

    /**
     * Creates the game and runs it.
     */
    public static void main(String[] args) {
        UnoCard game = new UnoCard();
        game.playGame();
    }

}
