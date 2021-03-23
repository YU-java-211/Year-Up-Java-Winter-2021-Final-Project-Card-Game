import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class GoFish{
   private Player one;
   private Player two;
   private Player three;
   private Player four;
   private Hand discardPile;
   private Hand drawPile;
   private Random rand;
   private ArrayList<Hand> bookHands;
   private Scanner scn;
   

   public GoFish(){
      Deck deck = new Deck("Deck");
      deck.shuffle();        
      
      scn = new Scanner(System.in);
        
      one = new Player("Adelina");
      deck.deal(one.getHand(), 5);
      two = new Player("Ernesto");
      deck.deal(two.getHand(), 5);
      three = new Player("James");
      deck.deal(three.getHand(), 5);
      System.out.println("Please input your player name");
      four = new Player(scn.nextLine());
      deck.deal(four.getHand(), 5);

   
      discardPile = new Hand("Discards");
   
      drawPile = new Hand("Draw pile");
      deck.dealAll(drawPile);
        
      rand = new Random();
        
      bookHands = new ArrayList<Hand>();
        
      for (int i = 0; i < 4; i++){
         bookHands.add((new Hand("Player Book Hands")));
      }
   
   }
   
   
   public void showHands(){
      
      this.one.display();
      this.two.display();
      this.three.display();
      this.four.display();
   }
   
   public void fishingTime(Player fisher, Player fishee){
      boolean flag = true;
      while (flag){
         flag = false;
         int randomCardNumber = rand.nextInt(fisher.getHand().size());
         Card bernie = fisher.getHand().getCard(randomCardNumber);
         if (fisher == this.four){
            System.out.println(fisher.getHand().toString());
            System.out.println("Please type a number between 1 and " + (fisher.getHand().size()) + " to select your card");
            bernie = fisher.getHand().getCard(scn.nextInt() - 1);
         }
         Hand fisheeHand = fishee.getHand();
         
         for (int i = 0; i < fisheeHand.size(); i++){
            if (fisheeHand.getCard(i).getRank() == bernie.getRank()){
               fisher.getHand().addCard(fisheeHand.popCard(i));
               i--;
               flag = true;
            }
         }
         bookDetected(fisher);
         if (fisher.getHand().size() == 0){
            flag = false;
         }
      }
      if (drawPile.size() > 0){
         drawPile.deal(fisher.getHand(), 1);
      }
   }
   
   public void bookDetected(Player player){
      int counter;
      Hand playerHand = player.getHand();
      for (int i = 0; i < playerHand.size(); i++){
         counter = 0;
         for (int j = 0; j < playerHand.size(); j++){
            
            if (playerHand.getCard(i).getRank() == playerHand.getCard(j).getRank()){
               counter++;
            }
         }
         
         if (counter == 4){
            addBook(i, player);
         }
      }  
   }
   
   public void addBook(int index, Player player){
      Hand bookHand;
      Hand playerHand = player.getHand();
      if (player.getName().equals("Adelina")){
         bookHand = this.bookHands.get(0);
      }
      else if (player.getName().equals("Ernesto")){
         bookHand = this.bookHands.get(1);
      }
      else if (player.getName().equals("James")){
         bookHand = this.bookHands.get(2);
      }
      else{
         bookHand = this.bookHands.get(3);
      }
      
      Card playerCard = playerHand.getCard(index);
      bookHand.addCard(playerHand.popCard(index));
      for (int i = 0; i < playerHand.size(); i++){
         if (playerHand.getCard(i).getRank() == playerCard.getRank()){
            bookHand.addCard(playerHand.popCard(i));
            i--;
         }
      }
   }
   
   //auto turn player
   public Player takeTurn(Player player){
      if (player.getName().equals("Adelina")){
         if (this.two.getHand().size() > 0)
            return this.two;
         else if (this.three.getHand().size() > 0)
            return this.three;
         else
            return this.four;
      }
      else if (player.getName().equals("Ernesto")){
         if (this.three.getHand().size() > 0)
            return this.three;
         else if (this.four.getHand().size() > 0)
            return this.four;
         else
            return this.one;
      }
      else if (player.getName().equals("James")){
         if (this.four.getHand().size() > 0)
            return this.four;
         else if (this.one.getHand().size() > 0)
            return this.one;
         else
            return this.two;
      }
      else{
         if (this.one.getHand().size() > 0)
            return this.one;
         else if (this.two.getHand().size() > 0)
            return this.two;
         else
            return this.three;
      }
   }
   
   public Player getFishee(Player player){
      Random rand = new Random();
      int randInt;
      Player fishee = player;
      while (player == fishee){
         randInt = rand.nextInt(4);
         if (randInt == 0)
            fishee = this.one;
         else if (randInt == 1)
            fishee = this.two;
         else if (randInt == 2)
            fishee = this.three;
         else
            fishee = this.four;
      }
      return fishee;
   }
   
   public void playGame(){
      Player currentPlayer = this.one;
      Player nextPlayer = getFishee(currentPlayer);
      while (!isDone()){
         fishingTime(currentPlayer, nextPlayer);
         bookDetected(currentPlayer);
         currentPlayer = takeTurn(currentPlayer);
         nextPlayer = getFishee(currentPlayer);
      }
      whoWon();
   }
   
   public boolean isDone(){
      if (this.one.getHand().size() == 0  && this.two.getHand().size() == 0 && this.three.getHand().size() == 0 && this.four.getHand().size() == 0){
         return true;
      }
      else
         return false;
   }
   
   public void whoWon(){
      int biggestHandNum = 0;
      int counter = 0;
      for (Hand hands : this.bookHands){
         System.out.println(hands.toString());
         if (hands.size() > biggestHandNum){
            biggestHandNum = hands.size();
         }
      }
      
      for (int i = 0; i < 4; i++){
         if (this.bookHands.get(i).size() == biggestHandNum)
            counter++;
      }
      
      if (counter > 0 && this.bookHands.get(0).size() == biggestHandNum){
         System.out.println("Congrats " + this.one.getName() + ", you've won with " + (this.bookHands.get(0).size() / 4) + " books!");
      }
      if (counter > 0 && this.bookHands.get(1).size() == biggestHandNum){
         System.out.println("Congrats " + this.two.getName() + ", you've won with " + (this.bookHands.get(1).size() / 4) + " books!");
      }
      if (counter > 0 && this.bookHands.get(2).size() == biggestHandNum){
         System.out.println("Congrats " + this.three.getName() + ", you've won with " + (this.bookHands.get(2).size() / 4) + " books!");
      }
      if (counter > 0 && this.bookHands.get(3).size() == biggestHandNum){
         System.out.println("Congrats " + this.four.getName() + ", you've won with " + (this.bookHands.get(3).size() / 4) + " books!");
      }
   }
   
   public static void main(String[] args) {
      GoFish game = new GoFish();
      game.showHands();
      game.playGame();
      System.out.println("That's all folks!");
   }
   

}
/*

- first write 4 players

- deal 5 cards to those 4 players
- (if 2 or 3 players each player recieves 7, 5 cards with 4-5 characters), two different ways of handling cards, so that each player gets the right amount of cards

- statement to show many cards each player has (Hand.display(), Hand(extends CardCollection) size())
- let there be set 4 players with 5 cards ^^
- Arrange cards by number so that all the 1s are together, all the 2s are together, and so on.

- draw pile (you pick who is going to go first) that player is allowed to look at any other player at the board and request other players cards from their hand

- the fisher ie. current player and request the same cards they have
   the fisher asks another player for a card with a rank the fisher has in their hand
   if the fishee has this rank in their hand, they lose all of that rank, give them to the fisher and the fisher can go again, recursive method
   else, the fishee says "GO FISH!", and the fisher has to draw 1 card from the draw pile
   turns ALWAYS end in a go fish

- goal is to get a player vs ai, but start as computer versus computer ->> input a person vs person

The winner is the person who has collected the most sets of 4 cards, meaning 1 rank(number/title) across all 4 suit's(hearts/diamonds/club/spades)

user interactive with 4 computers


swapcards method
swap to rank number
if rank number holds a card rank lower than it's own
go to rank + 1, check previous condition

1 2
2 3
3 4
4 1

check into .getrank = .getrank if they're both strings
equals method in card class


get their name so you can select the right position in the array list SPECIFIC TO THE PLAYER
*/