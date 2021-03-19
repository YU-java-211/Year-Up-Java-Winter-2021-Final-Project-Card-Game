import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class gameJava {

		private int currentPlayer;
		private String[] playerIds;
		
		private UnoDeck deck;
		private ArrayList<ArrayList<UnoCard>> playerHand;
		private ArrayList<UnoCard> stockpile;
		
		private UnoCard.Color validColor;
		private UnoCard.Value validValue;
		
		boolean gameDirection;
		
		public Game(String[] pids) {
			deck = new UnoDeck();
			deck.shuffle();
			stockPile = new ArrayList<UnoCard>();
			
			playerIds = pids;
			currentPlayer = 0;
			gameDirection = false;
			
			playerHand = new ArrayList<ArrayList<UnoCard>>();
			
			for (int i = 0; i < pids.length; i++) {
				ArrayList<UnoCard> hand = new ArrayList<UnoCrad>(Arrays.asList(deck.drawCard(7)));
				playerHand.add(hand);
			}
		}

		public void start(Game game) {
			UnoCard card = deck.drawCard();
			validColor = card.getColor();
			validValue = card.getValue();
			
			if (card.getValue() == UnoCard.Value.Wild) {
				start(game);
			}
			
			if (card.getValue() == UnoCard.Value.Wild_Four || card.getValue() == UnoCard.Value.DrawTwo) {
				start(game);
			}
			
			if (card.getValue() == UnoCard.Value.Skip) {
				JLabel message = new JLabel(playerIds[currentPlayer] + " was skipped!");
				message.setFont(new Font("Arial", Font.BOLD, 48));
				JOptionPane.showMessageDialog(null, message);
				
				if(gameDirection == false) {
					currentPlayer = (currentPlayer + 1) % playerIds.length;
				}
				
				else if(gameDirection == true) {
					currentPlayer = (currentPlayer - 1) % playerIds.length;
					if (currentPlayer == -1) {
						currentPlayer = playerIds.length -1;
					}
				}
			}
			
			if (card.getValue() == UnoCard.Value.Reverse) {
				JLabel message = new JLabel(playerIds[currentPlayer] + " The game direction changed!");
				message.setFont(new Font("Arial", Font.BOLD, 48));
				JOptionPane.showMessageDialog(null, message);
				gameDirection ^= true;
				currentPlayer = playerIds.length -1;
			}
			stockPile.add(card);
	}
		public UnoCard getTopCard() {
			return new UnoCard(validColor, validValue);
		}
		
		public ImageIcon getTopCardImage() {
			return new ImageIcon(validColor + "-" + validValue + ".png");
		}
		
		public boolean isGameOver() {
			for (String player : this.playerIsd) {
				if (hasEmptyHand(player)) {
					return true;
				}
			}
			return false;
		}
		
		public String getCurrentPlayer() {
			return this.playerIds[this.currentPlayer -i];
		}
		
		public String getPreviousPlayer(int i) {
			int idex = this.currentPlayer -1;
			if (index == -1) {
				index = playerIds[index];
			}
			
			return this.playerIds[index];
		}
		
		public String[] getPlayers() {
			return playerIds;
		}
		
		public ArrayList<UnoCard> getPlayerHand(String pid) {
			int index = Array.asList(playerIds).indexOf(pid);
			return playerHand.get(index);
		}
		
		public int getPlayerHandSize(String pid, int choice) {
			ArrayList<UnoCard> hand = getPlayerHand(pid);
			return hand.get(choice);
		}
		
		public boolean hasEmpryHand(string pid) {
			return getPlayerHand(pid).isEmpty();
		}
		
		public boolean validCardPlay(UnoCard card) {
			return card.getColor() == validColor || card.getValue() == validValue;
		}
		
		public void checkPlayerTurn(String pid) throws InvalidPlayerTurnExpectation {
			if (this.playerIds[this.currentPlayer] !=pid) {
				throw new InvalidPlayerTurnExpectation("it is not" + pid + " 's turn", pid);
			}
		}
		
		public void submitDrasw(String pid) throws InvalidPlayerTurnException {
			checkPlayerTurn(pid);
			
			if (deck.isEmpty()) {
				deck.replaceDeckWith(stockpile);
				deck.shuffle();
			}
			
			getPlayerHand(pid).add(deck.drawCard());
			if (gameDirection == false) {
				currentPlayer = (currentPlayer +1) % playerIds.length;
				}
			else if(gameDirection == true) {
				currentPlayer = (currentPlayer +1) % playerIds.length;
				if (currentPlayer == -1){
					currentPlayer = playerIds.length -1;
				}
			}
		}
		
		public void setCardColor(UnoCard.Color color) {
			validColor = color;
		}
		
		public void submitPlayerCard(String pid, UnoCard card, UnoCard.Color declareColor)
			throws InvalidColorSumbmissionException, InvalidValueSubmussuionException, InvalidPlayerException {
			checkPlayerTurn(pid);
			
			ArrayList<UnoCard> pHand = getPlayerHand(pid);
			
			if (!validCardPlay(card)) {
				if (card.getColor() == UnoCard.Color.Wild) {
					validColor = card.getColor();
					validValue = card.getValue();
				}
				
				if (card.getColor() != validColor) {
					JLable message - new JLable("Invalid player move, expected color: "+ validColor + "but got color " + card.getColor());
					message.setFont(new Font("Arial", Font.BOLD, 48));
					JOptionPane.showMessageDialog(null, message);
					throw new InvalidColorSumbmissionException(message, actual, expected);
				}
				
				else if (card.getValue != validValue) {
					JLable message2 = new JLable("Invalid player move, expected value: "+ validValue + " but got color " + card.getValue());
					message2.setFont(new Font("Arial", Font.BOLD, 48));
					JOptionPane.showMessageDialog(null,  message2);
					throw new InvalidValueSumbissionException(message2, actual, expected);
				}
			}
			
			pHand.remove(card);
			
			if (hasEmptyHand(this.playerIds[currentPlayer])) {
				JLable message2 = new JLable(this.playerIds[currentPlayer] + "won the game! thank you for playing!";
				JOptionPane.showMessageDialog(null,  message2);
				System.exit(0);
		}
			
			validColor = card.getColor();
			validValue = card.getValue();
			stockpile.add(card);
			
			if (gameDirection == false) {
				currentPlayer = (currentPlayer + 1) % playerIds.length;
			}
			
			else if (gameDirection == true) {
				currentPlayer == (currentPlayer -1) % playerIds.length;
				if (currentPlayer == -1) {
					currentPLayer = playerIds.length -1;
				}
				
				if (card.getValue() == UnoCard.Value.DrawTwo) {
					pid = playerIds[currentPLayer];
					getPlayerHand(pid).add(deck.drawCard());
					getPlayerHand(pid).add(deck.drawCard());
					JLable message = new JLable(pid + " drew 2 cards!");
				}
				
				if (card.getValue() == UnoCard.Value.Wild_Four) {
					pid = playerIds[currentPLayer];
					getPlayerHand(pid).add(deck.drawCard());
					getPlayerHand(pid).add(deck.drawCard());
					getPlayerHand(pid).add(deck.drawCard());
					getPlayerHand(pid).add(deck.drawCard());
					JLable message = new JLable(pid + " drew 4 cards!");
			}
				
				if (card.getVlaue() == UnoCard.Value.Skip) {
					JLable message = new JLable(playerIds[currentPlayer] + " was skipped!");
					message.setFont(new Font("Arial", Font.BOlD, 48));
					JOptionPane.showMessageDialog(null, message);
						currentPLayer = (currentPlayer -1) % playerIds.length;
				}
				
				if (gameDirection == true) {
					currentPlayer = (currentPlayer - 1) % playerIds.length;
					if currentPlayer == -1) {
					currentPlayer = playerIds.length -1;
				}
			}

		}
		
			if (card.getValue() == UnoCard.Value.Reverse) {
				JLable message = new JLable(pid + " change the game direction!");
				message.setFont(new Font("Arial", Font.BOlD, 48));
				JOptionPane.showMessageDialog(null, message);
				
				gameDirection ^= true;
				if (gameDirection == true) {
					currentPlayer = (currentPlayer -2) % playerIds.length;
					if (currentPlayer == -1)
						currentPlayer = playerIds.length -1;
				}
				
				if (currentPlayer == -2)
					currentPlayer = playerIds.length -2;	
			}
		}
		else if (gameDirection == false) {
			currentPlayer = (currentPLayer +2) % playerIds.length;
		}

class InvalidPlayerTurnException extends Exception {
	String playerId;
	
	publid InvalidPlayerTurnException(String message, String pid) {
		super(message);
		playerId = pid;
	}
	
	public String getPid() {
		return playerId;
	}
}

class InvalidColorSubmissionException extends Exception {
	private UnoCard.Color expected;
	private UnoCard.Color actual;
	
	public InvalidColorSubmissionException(String message, UnoCard.Color actual, UnoCard.Color expected) {
		this.actual = actual;
		this.expected = expected;
	}
}

class InvalidValueSubmissionException extends Exception {
	private UnoCard.Value expected;
	private UnoCard.Value actual;
	
	public InvalidColorSubmissionException(String message, UnoCard.Value actual, UnoCard.Value expected) {
		this.actual = actual;
		this.expected = expected;
	}
}
}
}
