import java.util.Scanner;
import java.util.ArrayList;


public class Uno {

    //private UnoPlayer one;
    //private UnoPlayer two;
    private ArrayList<UnoPlayer> players;
    private UnoHand drawPile;
    private UnoHand discardPile;
    private Scanner in;

    /**
     * Initializes the state of the game.
     */
    public Uno() {

        UnoDeck deck = new UnoDeck("UnoDeck");
        deck.shuffle();
        
        // set up array list
        players = new ArrayList<UnoPlayer>();

        // set up Scanner
        in = new Scanner(System.in);

        System.out.println("Please enter the name of each of your players on a seperate line");

        for (int i = 0; i < 4; i++){
            String playerName = in.nextLine();
            players.add((new UnoPlayer(playerName)));
        }

        for (UnoPlayer player : players) {
            deck.deal(player.getHand(), 5);
        }

        // deal cards to each player



        /*one = new UnoPlayer("Allen");
        deck.deal(one.getHand(), 5);
        two = new UnoPlayer("Chris");
        deck.deal(two.getHand(), 5);
        */

        // turn one card face up
        discardPile = new UnoHand("Discards");
        deck.deal(discardPile, 1);

        // put the rest of the deck face down
        drawPile = new UnoHand("Draw pile");
        deck.dealAll(drawPile);

        // create the scanner we'll use to wait for the user
        //in = new Scanner(System.in);
    }

    /**
     * Returns true if either hand is empty.
     */
    public boolean isDone() {
        //return one.getHand().isEmpty() || two.getHand().isEmpty();
        for (UnoPlayer player : players) {
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
        UnoCard prev = discardPile.popCard();

        // move the rest of the cards
        discardPile.dealAll(drawPile);

        // put the top card back
        discardPile.addCard(prev);

        // shuffle the draw pile
        drawPile.shuffle();
    }

    /**
     * Returns a card from the UnoCard pile.
     */
    public UnoCard drawCard() {
        if (drawPile.isEmpty()) {
            reshuffle();
        }
        return drawPile.popCard();
    }

    /**
     * Switches players.
     */
    public UnoPlayer nextPlayer(UnoPlayer current) {
        
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
        for (UnoPlayer player : players) {
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
    public void takeTurn(UnoPlayer player) {
        UnoCard prev = discardPile.lastCard();
        UnoCard next = playCard(player, prev);
        discardPile.addCard(next);

        System.out.println(player.getName() + " plays " + next);
        System.out.println();
    }
    
    public UnoCard playCard(UnoPlayer player, UnoCard prev){
      UnoHand playerHand = player.getHand();
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
        UnoPlayer playerTurn = players.get(players.size() - 1);

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
        Uno game = new Uno();
        game.playGame();
    }

}