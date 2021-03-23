import java.util.ArrayList;
import java.util.Scanner;

public class TwentyOne{
	 private Player livePlayer;
	 private Player dealer;
	 private Scanner in;
	 private int pointPlayer; 
	 private int pointDealer;
	 private Deck deck;
	
	public TwentyOne() {
	        deck = new Deck("Deck");
	        deck.shuffle();

	        livePlayer = new Player("Player");
	        dealer = new Player("Dealer");

	        deck.deal(livePlayer.getHand(), 2);
	        deck.deal(dealer.getHand(), 2);
	    }
	
	 public void displayStatePlayer() {
	        System.out.print("Draw pile: ");
	        System.out.println("Player has ");
	        
        	pointPlayer = 0;
        	int aces = 0; 
        	Hand playerHand = livePlayer.getHand();
        	
	        for (Card card : playerHand.cards) {
	        	if (card.toString().indexOf("Ace") > -1) {
	        		aces++;
	        	}
		        System.out.println(card); 
		        pointPlayer += card.getRank();
	        }
	        
	        int smallPointPlayer = pointPlayer + aces;
	        int largePointPlayer = pointPlayer + (aces * 11);
	        
	        System.out.println(smallPointPlayer + " or " + largePointPlayer);
	 }
	 
	 public void displayStateDealer() {
	        
	        pointDealer = 0; 
	        int aces = 0; 
	        Hand dealerHand = dealer.getHand();
	        for (int i = 1; i < dealerHand.cards.size(); i++) {
              Card card = dealerHand.getCard(i); 
              System.out.println(dealerHand.getCard(i));
              if (card.toString().indexOf("Ace") > -1) {
                  aces++; 
              }
              pointDealer += card.getRank();
	        }
	        
	        int smallPointDealer = pointDealer + aces; 
	        int largePointDealer = pointDealer + (aces * 11); 
	        
	        System.out.println(smallPointDealer + " or " + largePointDealer + " showing, one card face down.");
	    }
	 
	 public void takeTurn() {
		 while (pointPlayer < 21) {
			   System.out.println("Player takes a card.");
		     deck.deal(livePlayer.getHand(), 1);
		     displayStatePlayer();
		 }
		 while (pointDealer < 21) {
         System.out.println("Dealer takes a card.");
         deck.deal(dealer.getHand(), 1);
         displayStateDealer();
		 }
		 if (pointPlayer > 21) {
         System.out.println("Player bust.");
         return;
		 }else{
         if (pointPlayer > pointDealer) {
             System.out.println("You win!");
             return;
         }else{
             System.out.println("Dealer wins!");
             return;
         }
		 }
		 
	 }
	 
	 public void playGame() {
        System.out.println("Dealing set up");
        takeTurn();
	  }
	 
	 public static void main(String[] args) {
	      TwentyOne game = new TwentyOne();
	      game.playGame();
	 }
}
