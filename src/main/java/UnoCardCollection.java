                                                                                                                                                                             
import java.util.ArrayList;                                                                                                                                                  
import java.util.Random;                                                                                                                                                     
                                                                                                                                                                             
/**                                                                                                                                                                          
 * A collection of playing cards.                                                                                                                                            
 */                                                                                                                                                                          
public class UnoCardCollection {                                                                                                                                             
                                                                                                                                                                             
    private String label;                                                                                                                                                    
    private ArrayList<UnoCard> cards;                                                                                                                                        
                                                                                                                                                                             
    /**                                                                                                                                                                      
     * Constructs an empty collection.                                                                                                                                       
     */                                                                                                                                                                      
    public UnoCardCollection(String label) {                                                                                                                                 
        this.label = label;                                                                                                                                                  
        this.cards = new ArrayList<UnoCard>();                                                                                                                               
    }                                                                                                                                                                        
                                                                                                                                                                             
    /**                                                                                                                                                                      
     * Returns the label of the card collection.                                                                                                                             
     */                                                                                                                                                                      
    public String getLabel() {                                                                                                                                               
        return label;                                                                                                                                                        
    }                                                                                                                                                                        
                                                                                                                                                                             
    /**                                                                                                                                                                      
     * Returns a string representation of the card collection.                                                                                                               
     */                                                                                                                                                                      
    public String toString() {                                                                                                                                               
        return label + ": " + cards.toString();                                                                                                                              
    }                                                                                                                                                                        
                                                                                                                                                                             
    /**                                                                                                                                                                      
     * Adds the given card to the collection.                                                                                                                                
     */                                                                                                                                                                      
    public void addCard(UnoCard card) {                                                                                                                                      
        cards.add(card);                                                                                                                                                     
    }                                                                                                                                                                        
                                                                                                                                                                             
    /**                                                                                                                                                                      
     * Removes and returns the card with the given index.                                                                                                                    
     */                                                                                                                                                                      
    public UnoCard popCard(int i) {                                                                                                                                          
        return cards.remove(i);                                                                                                                                              
    }                                                                                                                                                                        
                                                                                                                                                                             
    /**                                                                                                                                                                      
     * Removes and returns the last card.                                                                                                                                    
     */                                                                                                                                                                      
    public UnoCard popCard() {                                                                                                                                               
        int i = cards.size() - 1;    // from the end of the list                                                                                                             
        return popCard(i);                                                                                                                                                   
    }                                                                                                                                                                        
                                                                                                                                                                             
    /**                                                                                                                                                                      
     * True if the collection is empty, false otherwise.                                                                                                                     
     */                                                                                                                                                                      
    public boolean isEmpty() {                                                                                                                                               
        return cards.isEmpty();                                                                                                                                              
    }                                                                                                                                                                        
                                                                                                                                                                             
    /**                                                                                                                                                                      
     * Returns the number of cards.                                                                                                                                          
     */                                                                                                                                                                      
    public int size() {                                                                                                                                                      
        return cards.size();                                                                                                                                                 
    }                                                                                                                                                                        
                                                                                                                                                                             
    /**                                                                                                                                                                      
     * Returns the card with the given index.                                                                                                                                
     */                                                                                                                                                                      
    public UnoCard getCard(int i) {                                                                                                                                          
        return cards.get(i);                                                                                                                                                 
    }                                                                                                                                                                        
                                                                                                                                                                             
    /**                                                                                                                                                                      
     * Returns the last card.                                                                                                                                                
     */                                                                                                                                                                      
    public UnoCard lastCard() {                                                                                                                                              
        int i = cards.size() - 1;                                                                                                                                            
        return cards.get(i);                                                                                                                                                 
    }                                                                                                                                                                        
                                                                                                                                                                             
    /**                                                                                                                                                                      
     * Swaps the cards at indexes i and j.                                                                                                                                   
     */                                                                                                                                                                      
    public void swapCards(int i, int j) {                                                                                                                                    
        UnoCard temp = cards.get(i);                                                                                                                                         
        cards.set(i, cards.get(j));                                                                                                                                          
        cards.set(j, temp);                                                                                                                                                  
    }                                                                                                                                                                        
                                                                                                                                                                             
    /**                                                                                                                                                                      
     * Randomly permute the cards.                                                                                                                                           
     */                                                                                                                                                                      
    public void shuffle() {                                                                                                                                                  
        Random random = new Random();                                                                                                                                        
        for (int i = cards.size() - 1; i > 0; i--) {                                                                                                                         
            int j = random.nextInt(i + 1);                                                                                                                                   
            swapCards(i, j);                                                                                                                                                 
        }                                                                                                                                                                    
    }                                                                                                                                                                        
                                                                                                                                                                             
    /**                                                                                                                                                                      
     * Moves n cards from this collection to the given collection.                                                                                                           
     */                                                                                                                                                                      
    public void deal(UnoCardCollection that, int n) {                                                                                                                           
        for (int i = 0; i < n; i++) {                                                                                                                                        
            UnoCard card = popCard();                                                                                                                                        
            that.addCard(card);                                                                                                                                              
        }                                                                                                                                                                    
    }                                                                                                                                                                        
                                                                                                                                                                             
    /**                                                                                                                                                                      
     * Moves all remaining cards to the given collection.                                                                                                                    
     */                                                                                                                                                                      
    public void dealAll(UnoCardCollection that) {                                                                                                                               
        int n = cards.size();                                                                                                                                                
        deal(that, n);                                                                                                                                                       
    }                                                                                                                                                                        
                                                                                                                                                                             
}                                                                                                                                                                            
                                                                                                                                                                             
                                                                                                                                                                             
