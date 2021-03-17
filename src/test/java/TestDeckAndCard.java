import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



public class TestDeckAndCard {

   @Test
   public void testDeckAndCard()
   {

     // action
     Deck testDeck = new Deck("test");
     Card testCard = testDeck.getCard(0);
     String actual = testCard.toString();
     String expected = "Ace of Clubs"; 

     // assertion
     assertEquals(expected, actual);

   }
}
