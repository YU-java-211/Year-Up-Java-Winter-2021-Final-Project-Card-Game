import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.util.List;

public class BlackJack {
    private Scanner console;
    private ArrayList<Player> players;
    private int score;
    private int score1;
    private int score2;
    private Player winner;

    public BlackJack() {
        Deck deck = new Deck("Deck");
        deck.shuffle();

        players = new ArrayList<Player>();
        console = new Scanner(System.in);
        
        System.out.println("Welcome to a game of BlackJack! Whoever's hand is equal to or closest to 21 wins!");
        System.out.println();


        for (int i = 0; i < 2; i++) {
            if (i == 0) {
               System.out.print("Please enter player's name: ");
            } else if (i == 1) {
               System.out.println();
               System.out.print("Please enter dealer's name: ");
            }
            if (console.hasNextLine()) {
                String playerName = console.nextLine();
                players.add((new Player(playerName)));
                System.out.println("Player " + (i + 1) + ": " + playerName);
            }
        }
        for (Player player : players) {
               deck.deal(player.getHand(), 3);
        }
    }

   public void displayState() {
      for (Player player : players) {
         player.display();
      }
   }
   
   public int logic(Player player) {
      Hand currentHand = player.getHand();
      score = 0;
      for (int i = 0; i < currentHand.size(); i++) {
         Card currentCard = currentHand.getCard(i);
         if (currentCard.getRank() == 11 || currentCard.getRank() == 12 || currentCard.getRank() == 13) {
            score += 10;
         } else if ((currentCard.getRank() + score) == 21) {
            winner = player;
            score += currentCard.getRank();
            break;
         } else {
            score += currentCard.getRank();
         }
      }
      return score;
   }
 
    public void gameplay() {
      Player playerTurn = players.get(0);
      Player player1 = playerTurn;
      score1 = logic(playerTurn);
      playerTurn = players.get(1);
      Player player2 = playerTurn;
      score2 = logic(playerTurn);
      if (winner != null) {
         System.out.println("The winner is " + winner.getName());
      } else if (score1 > score2 && score1 <= 21 || score2 > 21) {
         System.out.println("The winner is " + player1.getName());
      } else if (score2 > score1 && score2 <= 21 || score1 > 21) {
         System.out.println("The winner is " + player2.getName());
      } else {
         System.out.println("It's a tie");
      }
      System.out.println();
      System.out.println(player1.getName() + "'s score: " + score1);
      System.out.println(player2.getName() + "'s score: " + score2);
    }

   public static void main(String[] args) {
      BlackJack game = new BlackJack();
      System.out.println();
      game.displayState();
      game.gameplay();
   }
}
