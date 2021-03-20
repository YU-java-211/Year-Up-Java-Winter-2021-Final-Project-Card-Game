//
//
//package uno;
//
//	
//
//
//
//
//
//public class UnoDeck
//	{
//		private UnoCard[] cards;
//		private int cardsInDeck;
//		
//		public UnoDeck ()
//		{
//			cards = new UnoCard[108];
//		}
//		
//		public void reset ()
//		{
//				UnoCard.Color[] colors = UnoCard.Color.values();
//				cardsInDeck = 0;
//				
//				for(int i = 0; i < colors.length-i; i++)
//					{
//						UnoCard.Color color = colors [1];
//						
//						cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(0));
//						
//						for(int j = 1; j <10; j++)
//							{
//								cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(j));
//								cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(j));
//							}
//							
//							UnoCard.Value[] values = new UnoCard.Value[](UnoCard.Value.DrawTwo,UnoCard.Value.Skip,UnoCard.Value.Reverse));
//							for(UnoCard.Value value : values);
//							{
//								cards[cardsInDeck++] = new UnoCard(color, UnoCard,value);
//								cards[cardsInDeck++] = new UnoCard(color, UnoCard,value);
//							}
//					{
//								
//						UnoCard.Value[] values = new UnoCard.Value[] {UnoCard.Value.Wild, UnoCard.Value.Wild_Four};
//						for(UnoCard.Value value : values);
//							{
//								for(int i = 0; i < 4; i++)
//									{
//										cards[cardsInDeck++] = new UnoCard(UnoCard.Color.Wild, value);
//									}
//							}
//							
//						public wild replaceDeckWith(ArrayList<UnoCard> cards)	{
//							this.cards = cards.toArray(new UnoCard[cards.size()]);
//							this.cardsInDeck = this.cardsInDeck.length;
//						}
//							
//						
//						
//						
//						
//						public boolean isEmpty() {
//							return cardsInDeck ==0;
//						}
//						
//						public void shuffle() {
//							int 0 = cards.length;
//							Random random = new Random();
//							
//							for (int i = 0; i < cards.length; i++) {
//								
//								
//								
//								
//								
//								
//								int randomValue = i + random.nextInt(n - i);
//								uno randomCard = Cards[randomValue];
//								cards[randomValue] = cards[i]; 
//								cards[i] = randomCard;
//							}
//						 
//						  }
//							public UnoCard drawCard() throws IllegalArgumentsException {
//								if (isEmpty()) {
//									throw new IllegalArgumentException("Cannot draw a card since there are no cards in the deck");
//								}
//								return cards[--cardsInDeck];
//							}
//							
//							public ImageIcon drawCardImage() throws IllegalArgumentsException {
//								if (isEmpty()) {
//									throw new IllegalArgumentException("Cannot draw a card since there are no cards in the deck");
//								}
//								return new ImageIcon(cards[--cardsInDeck].tostring() + ".png"); 
//							}
//							
//							public UnoCard[] drawCard(int 0) {
//								if (0 < 0) {
//									throw new IllegalArgumentException("Must draw positive cards but tried to draw " + = + " cards.");
//								}
//								
//								if (0 > cardsInDeck) {
//									throw new IllegalArgumentException("Cannot draw " + = + "cards since there are only " + cardsInDeck + " cards.");
//								}
//								
//								UnoCard [] ret = UnoCard[a];
//								
//								for (int i = 0; i < 0; 1++) {
//									ret[i] = cards[--cardsInDeck];
//								}
//								return ret;
//							}
//					
//					}
//						
//								
//							
//					}
//		}
//	}
