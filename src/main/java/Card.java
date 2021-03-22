/**
 * A standard playing card.
 */
public class Card {

	public static final String[] CARD_TYPE = {
            "Number", "Action", "Wild"};
	
	public static final String[] NUMBERS = {
        "0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9"};
    
    public static final String[] ACTIONS = {
            "Draw +2", "Reverse", "Skip"};
    
    public static final String[] WILDS = {
            "Wild", "Wild Draw +4"};

    public static final String[] COLORS = {
        "Red", "Yellow", "Blue", "Green"};
    
    // Card color. Can be null for certain cards (i.e wild type)
    final int color;
    
    // The type of uno card it is.
    final int  cardType;
    
    // Card value can be either a number, action, or wild card type.
    final int cardValue;

    /**
     * Constructs a card of the given card type, color, & cardValue.
     */
    public Card(int cardType, int color, int cardValue) {
        this.cardType = cardType;
        this.color = color;
        this.cardValue = cardValue;
        //System.out.println("New Card created... Card Type: " + this.cardType + " Color: " + this.color + " Card Value: " + this.cardValue);
    }

    /**
     * CompareTo not really needed.
     */
    public int compareTo(Card that) {
        return 0;
    }

    /** Most likely wont be used.
     * Returns true if the given card has the same
     * rank AND same suit; otherwise returns false.
     */
    public boolean equals(Card that) {
        return this.cardType == that.cardType
            && this.color == that.color
            && this.cardValue == that.cardValue;
    }

    /**
     * Returns a string representation of the card.
     */
    public String toString() {
    	if(CARD_TYPE[this.cardType] == CARD_TYPE[0]) {  // Number card type
    		return NUMBERS[this.cardValue] + " " + COLORS[this.color];
    	}else if(CARD_TYPE[this.cardType] == CARD_TYPE[1]) {  // Action card type
    		return ACTIONS[this.cardValue] + " " + COLORS[this.color];
    	} else { // last card type which is wild.
    		return WILDS[this.cardValue];
    	}
    }

}

