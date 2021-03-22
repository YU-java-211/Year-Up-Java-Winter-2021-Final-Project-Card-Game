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
