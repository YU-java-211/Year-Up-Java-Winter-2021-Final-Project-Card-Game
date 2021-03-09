import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;

public class TesDeckAndCard {

   @Test
   public void testDeckAndCard()
   {

     // action
     Deck testDeck = new Deck("test");
     String actual = testDeck.cards[0].toString;
     String expected = "Ace of Clubs"; 

     // assertion
     assertEquals(expected, actual);

   }
}
