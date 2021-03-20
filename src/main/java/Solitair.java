
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
	        deck.deal(discardPile, 3);

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
	    	if ((baseCard.getRank() - 1) == topCard.getRank()) {
	    		if (stackAbleColor(baseCard.getSuit(), topCard.getSuit())) {
	    			return true;
	    			}
	    		}
	    	return false;
	    }
		
	 // this is saying that colors are different 
	    public boolean stackAbleColor(int a, int b) {
	    	// is a is black and b is red then that good 
	        if ((a == 1 || a == 2) && (b == 0 || b == 3)) {
	        
	        	return true;
	        	// if b is black and a is red that good 
	        } else if ((b == 1 || b == 2) && (a == 0 || a == 3)) {
	        	return true;
	        	
	        } else { // other wise it not return false.
	         	return false;
	        }
	    }
	    
		
		
		
	// new method for card renk need to be created for the 4 mpy slotes on the top.	
	//logic for moving the card to the 4 empty spot	
		
		
		
		
		
	// clubs is 0 diamonds is 1 hearts is 2 and spades is 3	
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
	     * Show the state of 18 more piles (anything face up needs to print out cards
	     * anything faced down needs to print no. of cards)
	     * Label your display
	     */
	    public void displayState() {
	    	 System.out.println("start");
		     //display drawpile face up card
		      System.out.println("Draw pile Faceup:  ");
		      discardPile.display();
		      
		      //displat size of draw card face down
		        System.out.print("Draw pile: ");
		        System.out.println(drawPile.size() + " cards");
		        System.out.println();
		        

		        //display size of facedown card in the each pile
		        System.out.println();
		        System.out.printf("pile1 facedown total cards: ");
		        System.out.println(pile1.size() + " cards");
		        
		        System.out.printf("pile2 facedown total cards: ");
		       System.out.println( pile2.size() + " cards");
		        
		        System.out.printf("pile3 facedown total cards: ");
		        System.out.println( pile3.size() + " cards");
		        
		        System.out.printf("pile4 facedown total cards: ");
		        System.out.println(pile4.size()+ " cards");
		        
		        System.out.printf("pile5 facedown total cards: ");
		        System.out.println( pile5.size() + " cards");
		        
		        System.out.printf("pile6 facedown total cards: ");
		        System.out.println( pile6.size() + " cards");
		        
		        System.out.printf("pile7 facedown total cards: ");
		        System.out.println(pile7.size() + " cards");
		        System.out.println();
		        
		        //display Faceup card each pile
		        
			    pile1FaceUp.display();
			    pile2FaceUp.display();
			    pile3FaceUp.display();
			    pile4FaceUp.display();
		        pile5FaceUp.display();
		        pile6FaceUp.display();
		        pile7FaceUp.display();
		      
		        //display Base card face up
		        System.out.println("pileBase1: ");
		        pileUpTo1.display();
		       
		        System.out.println("pileBase2: ");
		        pileUpTo2.display();
   
		        System.out.println("pileBase3: ");
			    pileUpTo3.display();
			   
				System.out.println("pileBase4: ");
				pileUpTo4.display();

				System.out.println("Go next");
			     

		        /* Disabling for easier automation
		        in.nextLine();
		        */
		    }
	    

	    /**
	     * One player takes a turn.
	     * write player taking a turn in solitair example below is for the Eights
	     * Ranking the next step and writing the method for gameplay
	     */
	    public void takeTurn() {
	        Card prev = discardPile.lastCard();
	        discardPile.addCard(next);

	        //System.out.println(player.getName() + " plays " + next);
	        //System.out.println();
	    }

	    /**
	     * Plays the game.
	     */
	    public void playGame() {
	        // changed from one to players.get(0);
	        // then rename the player in this to playerTurn so it doesn't clash
	        // with other code we have in our foreach looops
	       
	        // keep playing until there's a winner
	        while (!isDone()) {
	            displayState();
	            //takeTurn();
	        }

	        /* one.displayScore();
	        two.displayScore();
	        */
	    }

	    /**
	     * Creates the game and runs it.
	     */
	    public static void main(String[] args) {
	        Solitair game = new Solitair();
	        game.playGame();
	    }

	
}
