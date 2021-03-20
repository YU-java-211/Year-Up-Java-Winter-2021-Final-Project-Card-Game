import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack{
	 private Player livePlayer;
	 private Player dealer;
	 private Scanner in;
	 private int pointPlayer; 
	 private int pointDealer;
	 private Deck deck;
	
	public BlackJack() {
	        deck = new Deck("Deck");
	        deck.shuffle();

	        livePlayer = new Player("Player");
	        dealer = new Player("Dealer");

	        deck.deal(livePlayer.getHand(), 2);
	        deck.deal(dealer.getHand(), 2);
	    }
	
	 public void displayState() {
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
	        
	        System.out.println(pointPlayer + aces + " or " + pointPlayer + (aces * 11));
	        
	        pointDealer = 0; 
	        Hand dealerHand = dealer.getHand();
	        for (int i = 1; i < dealerHand.cards.size(); i++) {
	        	Card card = dealerHand.getCard(i); 
	        	System.out.println(dealerHand.getCard(i));
	 	        if (card.toString().indexOf("Ace") > -1) {
	 	        		aces++; 
	 	        }
	 	        pointDealer += card.getRank();
	        }
	        
	        System.out.println(pointDealer + aces + " or " + pointDealer + (aces * 11) + " showing, one card face down.");
	    }
	 
	
	 
	 public void playGame() {
		  
	    }
	 
	 public static void main(String[] args) {
	      BlackJack game = new BlackJack();
	      game.playGame();
	 }
}
