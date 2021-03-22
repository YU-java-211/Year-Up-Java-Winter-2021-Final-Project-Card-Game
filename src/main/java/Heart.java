import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Heart {
    private Scanner console;
    private ArrayList<Player> players;
    private Hand discardPile;
    private Card twoOfClubs;
    private Player lastPlayer;
    private int score;

public Heart() {
        twoOfClubs = new Card(2, 0);
        Deck deck = new Deck("Deck");
        discardPile = new Hand("Discards");
        deck.shuffle();

        players = new ArrayList<Player>();
        console = new Scanner(System.in);

        System.out.println("Please enter the name of each of your players on a seperate line");

        for (int i = 0; i < 4; i++) {
            if (console.hasNextLine()) {
                String playerName = console.nextLine();
                players.add((new Player(playerName)));
                System.out.println("Player " + (i + 1) + ": " + playerName);
            }
        }
        
        System.out.println();
        System.out.println("In hearts, you must play the 2 of Clubs first.");
        System.out.println();

        for (Player player : players) {
            deck.deal(player.getHand(), 13);
        }
    }

    public void displayState() {
        for (Player player : players) {
            player.display();
        }
    }

    public boolean isDone() {      
      for (Player player : players) {
         if (player.getHand().isEmpty()) {
            return true;
         }
      }
      return false;
    }
    
    public void findLowest() {
        for (int j = 0; j < players.size(); j++) {
            Hand hand = players.get(j).getHand();
            for (int i = 0; i < hand.size(); i++) {
                Card card = hand.getCard(i);
                if (card.equals(twoOfClubs)) {
                    lastPlayer = players.get(j);
                    Player temp = players.get(0);
                    players.set(0, players.get(j));
                    players.set(j, temp);
                    playCard(hand, card);
                }

            }
        }

    }

    public void playCard(Hand hand, Card card) {
        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.getCard(i);
            if (currentCard.equals(card)) {
                System.out.println(lastPlayer.getName() + " is playing: " + currentCard.toString());
                discardPile.addCard(currentCard);
                hand.popCard(i); // adds card to discard pile
                break;
            }
        }

    }

    public void logic(Player player) {
        Card prev = discardPile.lastCard();
        Hand currentHand = player.getHand();

        for (int i = 0; i < currentHand.size(); i++) {
            Card currentCard = currentHand.getCard(i);
            if (player.getName() == lastPlayer.getName()) {
                playCard(currentHand, currentCard);
                prev = discardPile.lastCard();
                break;
            } 
            if (currentCard.getSuit() == 2) {
                lastPlayer = player;
                playCard(currentHand, currentHand.getCard(i));
                prev = discardPile.lastCard();
                break;
            }
            if (currentCard.getSuit() == 1 && prev.getSuit() >= 3) {
                lastPlayer = player;
                playCard(currentHand, currentHand.getCard(i));
                prev = discardPile.lastCard();
                break;
            }
            if (currentCard.getSuit() == prev.getSuit() && prev.getSuit() < 3) {
                lastPlayer = player;
                playCard(currentHand, currentCard);
                prev = discardPile.lastCard();
                break;
            }
            if (currentCard.getSuit() >= prev.getSuit() && prev.getSuit() >= 3) {
                lastPlayer = player;
                playCard(currentHand, currentCard);
                prev = discardPile.lastCard();
                break;
            }
            if (currentCard.getSuit() == 2) {
                  for (int j = 0; j < 4; j++) {
                     score += 1;
                  }
            }        
        }
    }   

    public Player nextPlayer(Player current) {
        if (players.indexOf(current) == players.size() - 1) {
            return players.get(0);
        } else if (players.indexOf(current) < players.size()) {
            int playerIndex = players.indexOf(current) + 1;
            return players.get(playerIndex);
        } else {
            System.out.println("Something went wrong");
            return current;
        }
    }

    public void playGame() {
        findLowest();
        Player playerTurn = players.get(1);
        while (!isDone()) {
            logic(playerTurn);
            playerTurn = nextPlayer(playerTurn);
        }
        System.out.println();
        System.out.println("First person to have 0 cards wins.");
        System.out.println();
        System.out.println("Winner is: " + lastPlayer.getName());
        System.out.println(); 
        System.out.println(lastPlayer.getName() + "'s number of cards " + score);
        System.out.println();
        displayState();
    }

    public static void main(String[] args) {
        Heart game = new Heart();
        game.playGame();
    }
}

