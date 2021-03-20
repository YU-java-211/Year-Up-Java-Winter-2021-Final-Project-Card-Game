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
		/* less important, but would be nice to figure out:
		 * sorting cards in hand by rank
		 * giving the player only 30 seconds to decide if they want to call bluff or not
		 */
		
		/* feature request ideas:
		 * if I don't get to them today, the less important things that would be nice to figure out
		 * allow for player to play with 2-4 AI players instead of inherently having 3 (needs way to deal whole deck of cards to players when 52 can't be divided evenly by player number)
		 * much smarter AI
		 * multiplayer--allow for multiple real people to play together!
		 */

		Deck deck = new Deck("Deck");
        deck.shuffle();
        
        players = new ArrayList<Player>();
        tempPile = new Hand("Cards to Be Played");
        pile = new Hand("Main Pile");
        
        currentRank = nextRank(currentRank);
        
        input = new Scanner(System.in);
        
        System.out.println("Please enter the name of each of your players on a seperate line (last name entered will be you, the player): ");
        
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
		Random randGen = new Random();
		int i = 0;
		// this is a debugging statement
		// System.out.println(player.getName() + " is going to lie. They have " + player.getHand().size() + " cards. They will try to add " + cardNum + " card(s) to the temp pile.");
		
		// if it's the real player, show them their cards
		if(players.indexOf(player) == 3) {
			int j = 1;
			System.out.println("Here are your cards: ");
			
			for(Card card : player.getHand().cards) {
				System.out.println(j + ". " + card);
				
				j++;
			}
			
			// tempPile.size() < 4 is there because I don't trust the player to not send like...3 when they already have 3 cards in the pile
			// anyway this runs as long as their hand isn't empty, the pile size isn't 4, and they haven't reached the number of cards they wanted to enter
			while(i < cardNum && !player.getHand().isEmpty() && tempPile.size() < 4) {
				// here we get the number of the card the player wants to put in the pile
				// because I printed out cards starting at 0 but remove cards based on index in array list
				// I take in the number the user wants and subtract one
				System.out.println("\nEnter the number of the card you would like to play: ");
				int k = input.nextInt() - 1;
				
				// add the card to the temp pile, take it out of the player's hand
				tempPile.addCard(player.getHand().getCard(k));
				player.getHand().popCard(k);
				
				// show them their cards again
				// because I am a merciful card god
				j = 1;
				System.out.println("\nHere are your cards now: ");
				
				for(Card card : player.getHand().cards) {
					System.out.println(j + ". " + card);
					j++;
				}
				
				i++;
			}
		}
		// so what this does is
		// take i, and as long as i is less than or equal to cardNum and the player's hand isn't empty
		// add cards selected randomly from the player's hand to the temp pile
		// and take those cards out of the player's hand
		// since i starts at 1, if the temp pile already has 3 cards in it, it'll only run once
		else {
			while(i < cardNum && !player.getHand().isEmpty()) {
				int randSelect = randGen.nextInt(player.getHand().size());
				tempPile.addCard(player.getHand().getCard(randSelect));
				player.getHand().popCard(randSelect);
				i++;
			}
		}
	}
	
	public boolean callBluff() {
		double rand = Math.random()*100;
		
		// randomly decide if an AI player is going to call bluff on the current player
		// I want this to be smarter but how
		// probability was mentioned
		// but idk math
		// send help
		if(rand > 94) {
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
		System.out.println("\nHere are the cards that were played: ");
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
	
	public void removeCards(Player player) {
		// this removes the cards in the temp pile from the player's hand
		for(int i = 0; i < player.getHand().size(); i++) {
			for(int j = 0; j < tempPile.size(); j++) {
				if(tempPile.getCard(j).getRank() == player.getHand().getCard(i).getRank() && tempPile.getCard(j).getSuit() == player.getHand().getCard(i).getSuit()) {
					player.getHand().popCard(i);
				}
			}
		}
	}
	
	public void takeTurn(Player currentPlayer) {
		System.out.println("\nCurrent card rank to be played is " + RANKS[currentRank]);
		
		// display player's hand
		// only if it's the real player
		// AI players don't need to see their hands
		if(players.indexOf(currentPlayer) == 3) {
			currentPlayer.display();
		}
		
		for(Card card : currentPlayer.getHand().cards) {
        	if(card.getRank() == currentRank) {
        		// add card to tempPile if it's of the right rank
        		// no this is not a choice
        		// if you have cards of the right rank you WILL play them
        		tempPile.addCard(card);
        	}
        }
		
		removeCards(currentPlayer);
		
		// display cards you are going to play
		// but only if you're the real player
		// sorry no easy wins anymore
		if(players.indexOf(currentPlayer) == 3) {
			System.out.println("\nHere are the cards you are currently going to play (if nothing, you are currently playing no cards): ");
			tempPile.display();
			
			// display cards you have left in your hand
			System.out.println("Here are your remaining cards: ");
			currentPlayer.display();
		}
		
		// if there aren't any cards in the pile and the player is the real player
		// they gotta bluff
		// so ask how many cards they want to put in the pile
		if(tempPile.size() == 0 && players.indexOf(currentPlayer) == 3) {
			System.out.println("Sorry, but you have to bluff.");

			int cards;
			
			do {
			    System.out.println("How many cards do you want to play? Enter a number 1-4.");
			    
			    while (!input.hasNextInt()) {
			    	// come on guys don't be like this
			        System.out.println("Why are you like this?\nPlease enter a number 1-4. Not a string.");
			        input.next();
			    }
			    
			    cards = input.nextInt();
			} while (cards <= 0 || cards > 4);

			lie(currentPlayer, cards);
		}
		// if there are cards in the pile but less than four
		// and it's the real player
		// ask if they want to bluff
		else if(tempPile.size() < 4 && players.indexOf(currentPlayer) == 3) {
			System.out.println("Do you want to bluff? Enter y or n. ");
			String bluff = input.next().toLowerCase();
			
			// if they do, ask how many cards they want to add
			if(bluff.equals("y") || bluff.equals("yes")) {
				int cards;
				
				do {
				    System.out.println("How many cards do you want to add? Enter a number 1-3.");
				    
				    while (!input.hasNextInt()) {
				    	// seriously don't be like this
				        System.out.println("Why are you like this?\nPlease enter a number 1-3. Not a string.");
				        input.next();
				    }
				    
				    cards = input.nextInt();
				} while (cards <= 0 || cards > 3);
				
				lie(currentPlayer, cards);
			}
		}
		// if it's not the real player but there's less than four cards in the temp pile
		else if(tempPile.size() < 4){
			// we randomly decide if the AI will bluff!
			// or we make them bluff if there's no cards in the temp pile
			// you gotta play a card, them's the rules
			double rand = Math.random();
			
			// I would kind of like this to not be random but I'm not sure how else to do it
			if(rand < .5 || tempPile.size() == 0) {
				// pick random number to send to lie method
				// you know I feel like with how I have the lie method set up now I could just trash all this
				// and just have it generate a random number between 1 and 4
				// because ultimately it won't matter since the while loop terminates if:
				// the player's hand is empty
				// the desired number of cards have been added
				// or the temp pile size is 4
				// but I worked hard to make this and I think that with how I'm going to ask the player to pick their cards, I'll be able to
				// take the temp pile size check out of the AI part of the method
				// so I'm leaving this in
				int randNum;
				
				if(tempPile.size() == 3) {
					randNum = 1;
				}
				else {
					randNum = ThreadLocalRandom.current().nextInt(1, Math.abs(tempPile.size() - 4) + 1);
				}
				
				lie(currentPlayer, randNum);
			}
		}
		
		// this has to happen twice for two reasons
		// one: the lie method that might be called above picks cards at random from cards in the player's hand
		// which means if cards already in the temp pile aren't removed above, they might end up in the temp pile twice.
		// two: if the cards already in the temp pile aren't removed above, then the player can't see the cards they have remaining
		// after the cards of the appropriate rank have been put in the temp pile (if any).
		removeCards(currentPlayer);
		
		// new set of cards you're going to play, if you've chosen to lie
		// ....or you know if you haven't then you'll just see the exact same cards again
		// it's fine
		// who doesn't like redundancy
		if(players.indexOf(currentPlayer) == 3) {
			System.out.println("\nHere are the cards you have decided to play: ");
			tempPile.display();
		}

		// tell what the current player is claiming to play
		System.out.println("\n" + currentPlayer.getName() + " says they are playing " + tempPile.size() + " cards of rank " + RANKS[currentRank] + ".");
		
		for(Player player : players) {
    		if(players.indexOf(player) != players.indexOf(currentPlayer)) {
    			if(players.indexOf(player) != 3) {
    				int cardNum = 0;
    				
    				for(Card card : player.getHand().cards) {
    					if(card.getRank() == currentRank) {
    						cardNum++;
    					}
    				}
    				
    				if(cardNum > tempPile.size()) {
    					// debugging statement
    					// System.out.println("\n" + player.getName() + " knows that " + currentPlayer.getName() + " lied!");
						check(player, currentPlayer);
					}
    			}
    			// if it's the real player ask if they wanna bluff
    			else if(players.indexOf(player) == 3) {
    				System.out.println("\nDo you want to call bluff on the current player, " + currentPlayer.getName() + "? Enter y or n.");
    				String call = input.next().toLowerCase();
    				
    				// if they do we go right to the check method, because we don't need to use the callBluff method for a real person
    				if(call.equals("y") || call.equals("yes")) {
    					check(player, currentPlayer);
    					return;
    				}
    			}
    			// if it's not the real player just...use the callBluff method
    			else if(callBluff()) {
    				check(player, currentPlayer);
    				return;
    			}
    		}
    	}
		
		// if the temp pile isn't empty, that means no one called bluff, because if they had the temp pile would be empty
		// duplicates were happening here for two reasons:
		// first, I didn't use dealAll correctly; I was dealing the pile...into tempPile.
		// second, I didn't clear tempPile!
		// which...I think somehow resulted in there being duplicates in tempPile sometimes?
		// and then....people picked up those duplicates.
		// I don't know the point is it works now
		if(!tempPile.isEmpty()) {
			System.out.println("\nNo one called bluff on " + currentPlayer.getName() + "! They have successfully played their cards.");
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