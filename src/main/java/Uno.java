import java.util.*;

/**
 * Allows two players to play a simplified version of Uno against each other.
 */
public class Uno {

	final private static int NUMCARDSHAND = 7;

	private CardCollection deck;
	private CardCollection discardPile;
	private CardCollection hand1;
	private CardCollection hand2;

	public Uno() {
		Random r = new Random();

		// Make the deck and shuffle it.
		deck = new CardCollection("Deck");
		this.generateUnoDeck();
		deck.shuffle();

		// Discard Pile
		discardPile = new CardCollection("Discard Pile");

		// Create the two hands.
		hand1 = new CardCollection("Player 1 Hand");
		hand2 = new CardCollection("Player 2 Hand");

		// Deal them alternately.
		for (int i=0; i<NUMCARDSHAND; i++) {
			hand1.addCard(deck.popCard(0));
			hand2.addCard(deck.popCard(0));
		}
	}
	
	/**
	 * Creates 108 uno cards. 76 Number cards, 24 Action cards, & 8 Wild card.
	 */
	public void generateUnoDeck() {
		
		//Check Card class - String[] CARD_TYPES
		int numberCardTypeIndex = 0;
		int actionCardTypeIndex = 1;
		int wildCardTypeIndex = 2;
		
		//Check Card class - String[] COLORS
		int redColorIndex = 0;
		int yellowColorIndex = 1;
		int blueColorIndex = 2;
		int greenColorIndex = 3;
		
//		System.out.println(Card.NUMBERS.length);
//		System.out.println(Card.ACTIONS.length);
//		System.out.println(Card.WILDS.length);
		
		//Create Number cards Each color has 19 cards. 0 is one number card & 1-9 is two sets of cards numbered.
		for(int i=0; i<Card.NUMBERS.length; i++) {
			if(i == 0) { // there should be only one zero card for each color.
				deck.addCard(new Card(numberCardTypeIndex, redColorIndex, i));
				deck.addCard(new Card(numberCardTypeIndex, yellowColorIndex, i));
				deck.addCard(new Card(numberCardTypeIndex, blueColorIndex, i));
				deck.addCard(new Card(numberCardTypeIndex, greenColorIndex, i));
			} 	else { // 
				//first set.
				deck.addCard(new Card(numberCardTypeIndex, redColorIndex, i));
				deck.addCard(new Card(numberCardTypeIndex, yellowColorIndex, i));
				deck.addCard(new Card(numberCardTypeIndex, blueColorIndex, i));
				deck.addCard(new Card(numberCardTypeIndex, greenColorIndex, i));
				// second set.
				deck.addCard(new Card(numberCardTypeIndex, redColorIndex, i));
				deck.addCard(new Card(numberCardTypeIndex, yellowColorIndex, i));
				deck.addCard(new Card(numberCardTypeIndex, blueColorIndex, i));
				deck.addCard(new Card(numberCardTypeIndex, greenColorIndex, i));
			}
		}
		
		//Create Actions Cards for each color.
		for(int i=0; i<Card.ACTIONS.length; i++) {
			deck.addCard(new Card(actionCardTypeIndex, redColorIndex, i));
			deck.addCard(new Card(actionCardTypeIndex, yellowColorIndex, i));
			deck.addCard(new Card(actionCardTypeIndex, blueColorIndex, i));
			deck.addCard(new Card(actionCardTypeIndex, greenColorIndex, i));
		}
		
		//Create Wild Cards. 4 of each. (No specifc color for wild cards)
		for(int i=0; i<Card.WILDS.length; i++) {
			deck.addCard(new Card(wildCardTypeIndex, -1, i));
			deck.addCard(new Card(wildCardTypeIndex, -1, i));
			deck.addCard(new Card(wildCardTypeIndex, -1, i));
			deck.addCard(new Card(wildCardTypeIndex, -1, i));
		}
		
	}

	public void playGame() {

		Scanner stdin = new Scanner(System.in);

		System.out.println("Player 1, here is your hand:\n"+hand1);
		System.out.println("What card would you like to discard? Please give the associated number (List index number).");
		int card = stdin.nextInt();
		discardPile.addCard(hand1.popCard(card));
		System.out.println();

		int turn = 2;

		// The game ends if the deck or either player's hand is empty.
		while (deck.size() > 0 && hand1.size() > 0 && hand2.size() > 0) {

			playTurn(turn);

			// Flip the player's turn.
			if (turn == 1)
				turn = 2;
			else
				turn = 1;
			
			System.out.println();
		}

		printResult();

	}

	// Precondition: Can only be called at the end of the game!
	public void printResult() {
		if (deck.size() == 0)
			System.out.println("Sorry, the game has ended in a draw.");
		else if (hand1.size() == 0)
			System.out.println("Player 1, you win =)");
		else
			System.out.println("Player 2, you win =)");
	}
	
	//Checks if player has a card to play in his desk for their round.
	public boolean canPlayerPlayRound(CardCollection playerHand, Card discardPileTopCard) {
		for(int i=0; i<playerHand.size(); i++) {
			Card playerCard = playerHand.getCard(i);
			boolean canCardBeUsed = this.canCardBePlayedAgainstPileTopCard(playerCard, discardPileTopCard);
			if(canCardBeUsed) {
				return true;
			}
		}
		return false;
	}
	
	//Checks if player card be played with the discard pile top card.
	public boolean canCardBePlayedAgainstPileTopCard(Card playerCard, Card discardPileTopCard) {
		if(playerCard.cardType == 0 && discardPileTopCard.cardType == 0) { // Number card type for both player & top card on discard pile.
			if(playerCard.cardValue == discardPileTopCard.cardValue) {
				return true; // card numbers match player can still play.
			} else if(playerCard.color == discardPileTopCard.color) {
				return true; //card color match player can still play.
			}
		}else if(playerCard.cardType == 1) { //Player has action type card.
			if(playerCard.color == discardPileTopCard.color) { // if color matches player can play.
				return true;
			}
		}else if(playerCard.cardType == 2) {
			return true; // wild types can be used by the player in any situation.
		}
		return false;
	}

	// Plays one turn for the player number indicated.
	public void playTurn(int player) {

		Scanner stdin = new Scanner(System.in);

		Card discardPileTopCard = discardPile.getCard(0);
		System.out.println("Next Player turn.....");
		System.out.println("The current card at the top of the discard pile is "+ discardPileTopCard);

		if (player == 1) {
			System.out.println("Player 1 turn!");
			// We have a card to play!
			if (this.canPlayerPlayRound(hand1, discardPileTopCard)) {
				System.out.println("\nPlayer 1, here is your hand:\n"+hand1);
				System.out.println("What card would you like to discard? Please give the associated number(List index number).");
				int card = stdin.nextInt();

				// Only play this card if it's really valid!
				if (this.canCardBePlayedAgainstPileTopCard(hand1.getCard(card), discardPileTopCard))
					discardPile.addCard(hand1.popCard(card));
				else
					System.out.println("Sorry that is not a valid card. You lost your opportunity to drop a card.");

				// UNO 
				if (hand1.size() == 1)
					System.out.println("Player One says UNO!!!!");
			}

			// Add a card and show the result.
			else {
				System.out.println("Sorry, you can't play on this card. A card has been drawn for you.");
				hand1.addCard(deck.popCard(0));
				System.out.println("Player 1, here is your resulting hand:\n"+hand1);
			}
		}
		else {
			System.out.println("Player 2 turn!");
			// We have a card to play!
			if (this.canPlayerPlayRound(hand2, discardPileTopCard)) {
				System.out.println("\nPlayer 2, here is your hand:\n"+hand2);
				System.out.println("What card would you like to discard? Please give the associated number(List index number).");
				int card = stdin.nextInt();

				// Only play this card if it's really valid!
				if (this.canCardBePlayedAgainstPileTopCard(hand2.getCard(card), discardPileTopCard))
					discardPile.addCard(hand2.popCard(card));
				else
					System.out.println("Sorry that is not a valid card. You lost your opportunity to drop a card.");

				// UNO
				if (hand2.size() == 1)
					System.out.println("Player Two says UNO!!!!");
			}

			// Add a card and show the result.
			else {
				System.out.println("Sorry, you can't play on this card. A card has been drawn for you.");
				hand2.addCard(deck.popCard(0));
				System.out.println("Player 2, here is your resutling hand:\n"+hand2);
			}

		}
	}

	public static void main(String[] args) {
		Uno myGame = new Uno();
		myGame.playGame();
	}
}

