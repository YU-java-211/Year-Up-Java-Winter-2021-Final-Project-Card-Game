public class UnoCard { 
	    
	    public static final String[] RANKS = {
	    		"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
	    		"Nine", "DrawTwo", "Skip", "Reverse", "Wild", "Wild_Four"};
	    public static final String[] SUITS = {"Red", "Blue", "Green", "Yellow", "Wild"};  
	    
	    

	    private final int rank;

	    private final int suit;

	    
	    public UnoCard(int rank, int suit) {
	        this.rank = rank;
	        this.suit = suit;
	        
	    }
	    
	    public UnoCard(int rank, int suit, String Uno) {
	    	this.rank = rank;
	    	this.suit = suit;
	    	
	    }

	    /**
	     * Returns a negative integer if this card comes before
	     * the given card, zero if the two cards are equal, or
	     * a positive integer if this card comes after the card.
	     */
	    public int compareTo(UnoCard that) {
	        if (this.suit < that.suit) {
	            return -1;
	        }
	        if (this.suit > that.suit) {
	            return 1;
	        }
	        if (this.rank < that.rank) {
	            return -1;
	        }
	        if (this.rank > that.rank) {
	            return 1;
	        }
	        return 0;
	    }

	    /**
	     * Returns true if the given card has the same
	     * rank AND same suit; otherwise returns false.
	     */
	    public boolean equals(UnoCard that) {
	        return this.rank == that.rank
	            && this.suit == that.suit;
	    }

	    /**
	     * Gets the card's rank.
	     */
	    public int getRank() {
	        return this.rank;
	    }

	    /**
	     * Gets the card's suit.
	     */
	    public int getSuit() {
	        return this.suit;
	    }

	    /**
	     * Returns the card's index in a sorted deck of 52 cards.
	     */
	    public int position() {
	        return this.suit * 13 + this.rank - 1;
	    }

	    /**
	     * Returns a string representation of the card.
	     */
	    public String toString() {
	        return RANKS[this.rank] + " of " + SUITS[this.suit];
	    }

	}

	
	        


