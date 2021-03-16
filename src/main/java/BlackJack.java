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

	        deck.deal(livePlayer.getHand(), 2);
	        deck.deal(dealer.getHand(), 2);
	        

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
        	for (int i = 0; i < playerHand.cards.size(); i++) {
        		
        	}
	        for (Card card : playerHand.cards) {
	        	if (card.toString().indexOf("Ace") > -1) {
	        		aces++;
	        	}
	        	System.out.println(card); 
	        	pointPlayer += card.getRank();
	        }
	        /* Disabling for easier automation
	        in.nextLine();
	        */
	        System.out.println(pointPlayer + aces + " or " + pointPlayer + (aces * 11));
	        
	        pointDealer = 1; 
	        dCard1 = 0;
	        dCard2 = 1; 
	        Hand dealerHand = dealer.getHand();
	        for (int i = 0; i < dealerHand.cards.size(); i++) {
	        	
	        }
	        for (Card card : playerHand.cards) {
	        	if (card.toString().indexOf("Ace") > -1) {
	        		aces++; 
	        	}
	        	System.out.println(card); 
	        	pointDealer += card.getRank();
	        }
	        System.out.println(pointDealer + aces + " or " + pointDealer + (aces * 11));
	        // one card facedown
	        // reset values to default 
	    }
	 
	 public void playGame() {
		  	System.out.println("Dealing set up");
		  	
	    }
	 
	 public static void main(String[] args) {
		 	
	 
	 }
	
}
