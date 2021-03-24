public class UnoDeck extends UnoCardCollection {

    /**
     * Constructs a standard deck of 52 cards.
     */
    public UnoDeck(String label) {
        super(label);
        for (int suit = 0; suit <= 4; suit++) {
            for (int rank = 1; rank <= 14; rank++) {
                addCard(new UnoCard(rank, suit));
            }
        }
    }

}
