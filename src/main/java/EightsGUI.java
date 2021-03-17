// %%writefile Eights.java
// To test in Colab/Jupyter notebooks, use the following code in its own code block:
// Note, that you'll have to add the -e to the echo statement for this to be multiplayer!!
/*
!javac Card.java CardCollection.java Deck.java Eights.java Hand.java Test.java
!echo -e "nicole\nbob\ncindy\nsky" > test
!java Eights < test
*/
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Simulates a game of Crazy Eights.
 * See https://en.wikipedia.org/wiki/Crazy_Eights.
 */
public class EightsGUI extends Games {

    //private PlayerEightsGUI one;
    //private PlayerEightsGUI two;
    private ArrayList<PlayerEightsGUI> players;
    private ArrayList<Hand> piles;
    private Hand drawPile;
    private Hand discardPile;
    private Deck deck;
    private Scanner in;
    

    /**
     * Initializes the state of the game.
     */
    public EightsGUI() {

        Deck deck = new Deck("Deck");
        deck.shuffle();
        
        // set up array list
        players = new ArrayList<PlayerEightsGUI>();

        // set up Scanner
        in = new Scanner(System.in);

        System.out.println("Please enter the name of each of your players on a seperate line");

        while (in.hasNextLine()) {
            String playerName = in.nextLine();
            // for manual input
            if (playerName.equals("done")) {
            	break;
            }
            players.add((new PlayerEightsGUI(playerName)));
        }

        for (PlayerEightsGUI player : players) {
            deck.deal(player.getHand(), 5);
        }

        // deal cards to each player



        /*one = new PlayerEightsGUI("Allen");
        deck.deal(one.getHand(), 5);
        two = new PlayerEightsGUI("Chris");
        deck.deal(two.getHand(), 5);
        */

        // turn one card face up
        discardPile = new Hand("Discards");
        deck.deal(discardPile, 1);

        // put the rest of the deck face down
        drawPile = new Hand("Draw pile");
        deck.dealAll(drawPile);
        
        piles = new ArrayList<Hand>();
        piles.add(discardPile);
        piles.add(drawPile);

        // create the scanner we'll use to wait for the user
        //in = new Scanner(System.in);
    }

    /**
     * Returns true if either hand is empty.
     */
    public boolean isDone() {
        //return one.getHand().isEmpty() || two.getHand().isEmpty();
        for (PlayerEightsGUI player : players) {
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
    public PlayerEightsGUI nextPlayer(PlayerEightsGUI current) {
        
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
        for (PlayerEightsGUI player : players) {
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
    public void takeTurn(PlayerEightsGUI player) {
        Card prev = discardPile.lastCard();
        Card next = player.play(this, prev);
        discardPile.addCard(next);

        System.out.println(player.getName() + " plays " + next);
        System.out.println();
    }
    
    public ArrayList<PlayerEightsGUI> getPlayers() {
    	return this.players;
    }
    
    public Deck getDeck() {
    	return this.deck;
    }
    
    public ArrayList<Hand> getPiles() {
    	return this.piles;
    }
    
    public PlayerEightsGUI getPlayer(String playerName) {
    	for (PlayerEightsGUI player : players) {
    		String testName = player.getName();
    		
    		if (playerName.equals(testName)) {
    		    return player;
    	    }
 
    	 }
    	return players.get(0);
    }
    
    

    /**
     * Plays the game.
     */
    public void playGame() {
        // changed from one to players.get(0);
        // then rename the player in this to playerTurn so it doesn't clash
        // with other code we have in our foreach looops
        PlayerEightsGUI playerTurn = players.get(0);

        // keep playing until there's a winner
        while (!isDone()) {
            displayState();
            takeTurn(playerTurn);
            playerTurn = nextPlayer(playerTurn);
        }

        // display the final score
        for (PlayerEightsGUI player : players) {
            player.displayScore();
        }
        /* one.displayScore();
        two.displayScore();
        */
    }

    /**
     * Creates the game and runs it.
     */
    public static void main(String[] args) {

        //game.playGame();
    }

}
