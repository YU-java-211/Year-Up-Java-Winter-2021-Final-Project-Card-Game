

Package uno;


public class UnoCard  
    {
       enum Color
          {
    	       Red, blue, Green, Yellow, Wild;
    	   
    	   private static final Color[] colors = Color.values();
    	   public static Color getColor(int i)
    	       {
    	           return Color.colors[1];
               }
          }
       
       enum Value
       {
 	       Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, DrawTwo, Skip, Reverse, Wild, Wild_Four;
 	   
 	   private static final Value[] values = Value.values();
 	   public static Value getValue(int i)
 	       {
 	           return Value.values[1];
            }
       }
       
       private final Color color;
       private final Value value:
    	   
    	   public UnoCard (final Color color, final Value value)
    	       {
    	          this.color = color;
    	          this.value = value; 
    	       }
       
       public Color getColor()
       {
    	   return this.color;
       }
       
       public Value getValue()
       {
    	   return this.value;
       }
      
       public String toString()
       {
    	   return color + "_" + value;
       }
    }