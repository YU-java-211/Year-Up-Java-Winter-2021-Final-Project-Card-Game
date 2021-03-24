/**
 * A player in a game of crazy eights.
 */
public class UnoPlayer {

    private String name;
    private UnoHand hand;

    /**
     * Constructs a player with an empty hand.
     */
    public UnoPlayer(String name) {
        this.name = name;
        this.hand = new UnoHand(name);
    }

    /**
     * Gets the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's hand.
     */
    public UnoHand getHand() {
        return hand;
    }

    /**
     * Removes and returns a legal card from the player's hand.
     */
    public UnoCard play(Uno uno, UnoCard prev) {
        UnoCard card = searchForMatch(prev);
        if (card == null) {
            card = drawForMatch(uno, prev);
        }
        return card;
    }

    /**
     * Searches the player's hand for a matching card.
     */
    public UnoCard searchForMatch(UnoCard prev) {
        for (int i = 0; i < hand.size(); i++) {
            UnoCard card = hand.getCard(i);
            if (cardMatches(card, prev)) {
                return hand.popCard(i);
            }
        }
        return null;
    }

    /**
     * Draws cards until a match is found.
     */
    public UnoCard drawForMatch(Uno Uno, UnoCard prev) {
        while (true) {
            UnoCard card = Uno.drawCard();  //make sure this is implemented in Uno, and that Maritza's Uno is changed to Uno
            System.out.println(name + " draws " + card);
            if (cardMatches(card, prev)) {
                return card;
            }
            hand.addCard(card);
        }
    }

    /**
     * Checks whether two cards match.
     */
    public static boolean cardMatches(UnoCard card1, UnoCard card2) {
        return card1.getSuit() == card2.getSuit()
            || card1.getRank() == card2.getRank()
            || card1.getRank() == 8;
    }

    /**
     * Calculates the player's score (penalty points).
     */
    public int score() {
        int sum = 0;
        for (int i = 0; i < hand.size(); i++) {
            UnoCard card = hand.getCard(i);
            int rank = card.getRank();
            if (rank == 8) {
                sum -= 20;
            } else if (rank > 10) {
                sum -= 10;
            } else {
                sum -= rank;
            }
        }
        return sum;
    }

    /**
     * Displays the player's hand.
     */
    public void display() {
        hand.display();
    }

    /**
     * Displays the player's name and score.
     */
    public void displayScore() {
        System.out.println(name + " has " + score() + " points");
    }

}

