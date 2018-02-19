/**
 * 
 */
package BlackjackGame;

import java.util.Scanner;

/**
 * @author Rahul Swaminathan
 */
public class BlackJackGame {

 /**
  * @param args
  */
 public static void main(String[] args) {
  //All I am doing in the main is calling the casinoEntrance
  BlackJackPlayer BlackJackPlayer = new BlackJackPlayer();
  Scanner console = new Scanner (System.in);
  Deck deck = new Deck();
  Card card = new Card();
  BlackJackPlayer.casinoEntrance(console, deck, card, BlackJackPlayer);
  console.close();
 }

}
