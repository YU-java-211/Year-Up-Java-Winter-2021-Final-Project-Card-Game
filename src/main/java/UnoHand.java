/**
 * A hand of playing cards.
 */
public class UnoHand extends UnoCardCollection {

    /**
     * Constructs an empty hand.
     */
    public UnoHand(String label) {
        super(label);
    }

    /**
     * Prints the label and cards.
     */
    public void display() {
        System.out.println(getLabel() + ": ");
        for (int i = 0; i < size(); i++) {
            System.out.println(getCard(i));
        }
        System.out.println();
    }

}
