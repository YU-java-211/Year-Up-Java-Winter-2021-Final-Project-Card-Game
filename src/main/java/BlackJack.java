import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack{
	 private Player livePlayer;
	 private Player dealer;
	 private Hand drawPile;
	 private Hand discardPile;
	 private Scanner in;
	 private int pointPlayer; 
	 private int pointDealer;
	
	public BlackJack() {
	        Deck deck = new Deck("Deck");
	        deck.shuffle();

	        // set up Scanner
	        in = new Scanner(System.in);

	        livePlayer = new Player("Player");
	        dealer = new Player("Dealer");

	        deck.deal(livePlayer, 2);
	        deck.deal(dealer, 2);
	        

	        // put the rest of the deck face down
	        drawPile = new Hand("Draw pile");
	        deck.dealAll(drawPile);

	        // create the scanner we'll use to wait for the user
	        //in = new Scanner(System.in);
	        
	    }
	
	 public void displayState() {
	        System.out.print("Draw pile: ");
	        System.out.println(drawPile.size() + " cards");
	        System.out.println("Player has ");
	        
        	pointPlayer = 0;
        	int aces = 0; 
        	Hand playerHand = livePlayer.getHand();
	        for (Card card : playerHand) {
	        	if (card.toString().indexOf("Ace") > -1) {
	        		aces++;
	        	}
	        	System.out.println(card); 
	        	pointPlayer += card.getRank();
	        }
	        /* Disabling for easier automation
	        in.nextLine();
	        */
	    }
	 
	 public void playGame() {
		  	System.out.println("Dealing set up");
		  	
	    }
	 
	 public static void main(String[] args) {
		 
	 
	 }
	
}
