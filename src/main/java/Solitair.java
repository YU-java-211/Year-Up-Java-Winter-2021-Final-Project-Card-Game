// starting of the game you will see a duck of cards to right of it you will see 4 empty spaces
// in the bottom there a row of 7 piles of cards
// pile one has o care facing up
//pile two has two kids and one of them facing up and one facing down 
//pile had the same thing but now it 3 cards
import java.util.ArrayList;
import java.util.Scanner;

public class Solitair {

	     //private Player one;
	    //private Player two;
	    private Hand drawPile;
	    private Hand discardPile;
	    private Scanner in;
	   
	    // these are my piles this goes on number 57
	    private Hand pile1;
	    private Hand pile2;
	    private Hand pile3;
	    private Hand pile4;
	    private Hand pile5;
	    private Hand pile6;
	    private Hand pile7;
	    
	    
	    
	    private Hand pile1FaceUp;
	    private Hand pile2FaceUp;
	    private Hand pile3FaceUp;
	    private Hand pile4FaceUp;
	    private Hand pile5FaceUp;
	    private Hand pile6FaceUp;
	    private Hand pile7FaceUp;
	    
	    //these are my pile up top that you will be putting cards in .
	    private Hand pileUpTo1;
	    private Hand pileUpTo2; 
	    private Hand pileUpTo3;
	    private Hand pileUpTo4;

	    /**
	     * Initializes the state of the game.
	     */
	    public Solitair() {

	        Deck deck = new Deck("Deck");
	        deck.shuffle();
	        
	    

	        // set up Scanner
	        in = new Scanner(System.in);


	      

	        /*one = new Player("Allen");
	        deck.deal(one.getHand(), 5);
	        two = new Player("Chris");
	        deck.deal(two.getHand(), 5);
	        */

	        // turn one card face up
	        discardPile = new Hand("Discards");
	        deck.deal(discardPile, 1);

	        // put the rest of the deck face down you need 7 of these
	      
	        
	       
	        // this is the 4 empty slots in the game where the card will go
	        pileUpTo1 = new Hand("Empty1");
	       
	        
	        pileUpTo2 = new Hand("Empty2");
	     
	        
	        pileUpTo3 = new Hand("Empty3");
	        
	        
	        pileUpTo4 = new Hand("Empty4");
	      
	        
	        
	        
	        
	        //these here are the face down card
	        pile1 = new Hand("Pile1");
	        deck.deal(pile1, 0);
	        
	        pile2 = new Hand("Pile2");
	        deck.deal(pile2, 1);
	       
	        pile3 = new Hand("Pile3");
	        deck.deal(pile3, 2);
	        
	        pile4 = new Hand("Pile4");
	        deck.deal(pile4, 3);
	        
	        pile5 = new Hand("Pile5");
	        deck.deal(pile5, 4);
	        
	        pile6 = new Hand("Pile6");
	        deck.deal(pile6, 5);
	        
	        pile7 = new Hand("Pile7");
	        deck.deal(pile7, 6);
	        
	        
	        // this here is the facedUp cards 
	        pile1FaceUp = new Hand("pile1FaceUp");
	        deck.deal(pile1FaceUp, 1);
	        
	        pile2FaceUp = new Hand("pile2FaceUp");
	        deck.deal(pile2FaceUp, 1);
	       
	        pile3FaceUp = new Hand("pile3FaceUp");
	        deck.deal(pile3FaceUp, 1);
	       
	        pile4FaceUp = new Hand("pile4FaceUp");
	        deck.deal(pile4FaceUp, 1);
	        
	        pile5FaceUp = new Hand("pile5FaceUp");
	        deck.deal(pile5FaceUp, 1);
	        
	        pile6FaceUp = new Hand("pile6FaceUp");
	        deck.deal(pile6FaceUp, 1);
	       
	        pile7FaceUp = new Hand("pile7FaceUp");
	        deck.deal(pile7FaceUp, 1);

	       //create a new hand and call it a new pile and all the rest of the cards to it.
	        drawPile = new Hand("Draw pile");
	        deck.dealAll(drawPile);

	    }
	    
	    

	    /**
	     * Returns true if either hand is empty.
	     */
	    public boolean isDone() {
            // all these pile is equel to 12 then the game is over if they are  not return false.
	      if (pileUpTo1.size() == 12 && pileUpTo2.size() == 12 && pileUpTo3.size() == 12 && pileUpTo4.size() == 12) {
	    	  return true;
	      }
	      return false;  
	    }
	    
	    
	    // compare the card on the pile in the (baseCard) to the card you are trying to move on top of it (topCard)
	    //and see if it is legal: most be oppsite color and most be 1 less then baseCard
	    public boolean canStack(Card baseCard, Card topCard) {
	         
	    }
	    
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
	     * Displays the state of the game.
	     */
	    public void displayState() {
	      System.out.println("Discard pile:  ");

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
	        Card next = player.play(this, prev);
	        discardPile.addCard(next);

	        System.out.println(player.getName() + " plays " + next);
	        System.out.println();
	    }

	    /**
	     * Plays the game.
	     */
	    public void playGame() {
	        // changed from one to players.get(0);
	        // then rename the player in this to playerTurn so it doesn't clash
	        // with other code we have in our foreach looops
	        Player playerTurn = players.get(0);

	        // keep playing until there's a winner
	        while (!isDone()) {
	            displayState();
	            takeTurn(playerTurn);
	            playerTurn = nextPlayer(playerTurn);
	        }

	        // display the final score
	        for (Player player : players) {
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
	        Eights game = new Eights();
	        game.playGame();
	    }

	
}
