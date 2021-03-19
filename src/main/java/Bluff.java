import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Bluff {
	private ArrayList<Player> players;
	public static final String[] RANKS = {null, "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	private Hand pile;
	private Hand tempPile;
	private int currentRank = 0;
	private Scanner input;
	
	public Bluff() {
		Deck deck = new Deck("Deck");
        deck.shuffle();
        
        players = new ArrayList<Player>();
        tempPile = new Hand("Temp Pile");
        pile = new Hand("Main Pile");
        
        currentRank = nextRank(currentRank);
        
        input = new Scanner(System.in);
        
        System.out.println("Please enter the name of each of your players on a seperate line (must be four players, and names cannot be the same): ");
        
        for(int i = 0; i < 4; i++) {
        	String playerName = input.nextLine();
            players.add((new Player(playerName)));
        }

        for(Player player : players) {
            deck.deal(player.getHand(), 13);
        }
	}
	
	public boolean isDone() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
            	System.out.println(player.getName() + " won!");
                return true;
            }
        }
        
        return false;
    }
	
	public Player nextPlayer(Player current) {
        if(players.indexOf(current) == players.size() -1) {
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
	
	public int nextRank(int currentRank) {
		if(currentRank == 13) {
			return 1;
		}
		else {
			return currentRank + 1;
		}
	}
	
	public void lie(Player player, int cardNum) {
		double rand = Math.random();
		Random randGen = new Random();
		int i = 1;
		
		// I would kind of like this to not be random as well but I'm not sure how else to do it
		if(rand < .5 || tempPile.size() == 0) {
			// this is a debugging statement
			// System.out.println(player.getName() + " is going to lie. They have " + player.getHand().size() + " cards. They will try to add " + cardNum + " card(s) to the temp pile.");
			
			// so what this does is
			// take i, and as long as i is less than or equal to cardNum and the player's hand isn't empty
			// add cards selected randomly from the player's hand to the temp pile
			// and take those cards out of the player's hand
			// since i starts at 1, if the temp pile already has 3 cards in it, it'll only run once
			while(i <= cardNum && !player.getHand().isEmpty()) {
				int randSelect = randGen.nextInt(player.getHand().size());
				tempPile.addCard(player.getHand().getCard(randSelect));
				player.getHand().popCard(randSelect);
				i++;
			}
		}
	}
	
	public boolean callBluff() {
		double rand = Math.random()*100;
		
		// randomly decide if a player is going to call bluff on the current player
		if(rand > 85) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void check(Player challenger, Player current) {
		System.out.println(challenger.getName() + " is challenging " + current.getName() + "!");
		
		// have to show the cards the current player played
		// them's the rules
		System.out.println("Here are the cards that were played: ");
		tempPile.display();
		
		// go through temp pile
		for(int i = 0; i < tempPile.size(); i++) {
			
			// check if each card rank in the temp pile matches the expected rank
			if(tempPile.getCard(i).getRank() != currentRank) {
				
				// if it doesn't, pile and temp pile go into the current player's hand
				pile.dealAll(current.getHand());
				tempPile.dealAll(current.getHand());
				
				// empty temp pile
				tempPile.cards.clear();
				
				System.out.println(current.getName() + " lied! Their cards have been put back in their hand and they have picked up the pile.");
				return;
			}
		}
		
		// if we get to here, the current player didn't lie
		// pile and temp pile go into challenger's hand
		// empty temp pile
		pile.dealAll(challenger.getHand());
		tempPile.dealAll(challenger.getHand());
		tempPile.cards.clear();
		
		System.out.println(current.getName() + " was telling the truth! " + challenger.getName() + " has picked up the pile.");
	}
	
	public void takeTurn(Player currentPlayer) {
		System.out.println("Current card rank to be played is " + RANKS[currentRank]);
		
		// display player's hand
		currentPlayer.display();
		
		for(Card card : currentPlayer.getHand().cards) {
        	if(card.getRank() == currentRank) {
        		// add card to tempPile if it's of the right rank
        		// no this is not a choice
        		// if you have cards of the right rank you WILL play them
        		tempPile.addCard(card);
        	}
        }
		
		// this removes the cards in the temp pile from the player's hand
		// might make this into a method at some point
		for(int i = 0; i < currentPlayer.getHand().size(); i++) {
			for(int j = 0; j < tempPile.size(); j++) {
				if(tempPile.getCard(j).getRank() == currentPlayer.getHand().getCard(i).getRank() && tempPile.getCard(j).getSuit() == currentPlayer.getHand().getCard(i).getSuit()) {
					currentPlayer.getHand().popCard(i);
				}
			}
		}
		
		// display cards you are going to play
		System.out.println("Here are the cards you are currently going to play (if nothing, you are currently playing no cards): ");
		tempPile.display();
		
		// display cards you have left in your hand
		System.out.println("Here are the remaining cards: ");
		currentPlayer.display();
		
		// if there aren't 4 cards in the temp pile, call the lie function
		// which will have the player lie if the temp pile has no cards or if the random number says they're going to lie
		if(tempPile.size() < 4) {
			// so the lie method receives a number of cards to try and add
			// if you have 3 cards in the temp pile you can only add 1, so
			// if you have 0 cards you can add 1-4, if you have 1 card you can add 1-3, if you have 2 cards you can add 1-2 cards
			// so I made a random generator that gets a number between those possibilities
			// and then sends that down to the lie method as the number of cards the AI player is trying to add
			int randNum;
			
			if(tempPile.size() == 3) {
				randNum = 1;
			}
			else {
				randNum = ThreadLocalRandom.current().nextInt(1, Math.abs(tempPile.size() - 4) + 1);
			}
			
			lie(currentPlayer, randNum);
		}
		
		for(int i = 0; i < currentPlayer.getHand().size(); i++) {
			for(int j = 0; j < tempPile.size(); j++) {
				if(tempPile.getCard(j).getRank() == currentPlayer.getHand().getCard(i).getRank() && tempPile.getCard(j).getSuit() == currentPlayer.getHand().getCard(i).getSuit()) {
					currentPlayer.getHand().popCard(i);
				}
			}
		}
		
		// new set of cards you're going to play, if you've chosen to lie
		System.out.println("Here are the cards you are going to play: ");
		tempPile.display();

		// tell what the current player is claiming to play
		System.out.println(currentPlayer.getName() + " plays " + tempPile.size() + " cards of rank " + RANKS[currentRank]);
		
		for(Player player : players) {
    		if(player.getName() != currentPlayer.getName()) {
    			if(callBluff()) {
    				check(player, currentPlayer);
    				return;
    			}
    		}
    	}
		
		// if the temp pile isn't empty, that means no one called bluff, because if they had the temp pile would be empty
		if(!tempPile.isEmpty()) {
        	// add tempPile cards to pile, empty tempPile	
			tempPile.dealAll(pile);
			
			tempPile.cards.clear();
        }
    }
	
	public void playGame() {
        Player currentPlayer = players.get(0);

        // keep playing until there's a winner
        while (!isDone()) {
            takeTurn(currentPlayer);
            currentPlayer = nextPlayer(currentPlayer);
            currentRank = nextRank(currentRank);
        }
    }
	
	public static void main(String[] args) {
		Bluff game = new Bluff();
        game.playGame();
    }
}