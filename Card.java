/**
 * 
 */
package BlackjackGame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Rahul Swaminathan
 *8/1/15
 */
public class Card {
	int bet = 10;//for player 1
	int bet1 = 10;//for player 2
	int bet2 = 10;//for player 3
	ArrayList<String> Dealer = new ArrayList<String>(); //dealer's hand
	ArrayList<String> player1hand = new ArrayList<String>();//player 1's hand
	ArrayList<String> player2hand = new ArrayList<String>();//player 2's hand
	ArrayList<String> player3hand = new ArrayList<String>();//player 3's hand
	int x;//for player 1
	int n;//for player 2
	int a;//for player 3
	int numValue=0;
	int currentCardValue=0;

	public void showCardsInHand(Scanner console, ArrayList<String> hand, BlackJackPlayer cardsInHand, Deck deck, Card card) {
		if(hand==Dealer && hand.size()==2){
			//prints dealer's cards as [hidden] | card2
			System.out.println("Dealer's cards: [hidden] | "+hand.get(1));
			showCardsInHand(console, player1hand, cardsInHand, deck, card);
		} else if(hand==Dealer){
			//prints dealer's cards
			System.out.print("Dealer's cards: ");
			for(int i = 0; i<hand.size()-1; i++){
				System.out.print(hand.get(i)+" | ");
			}
			System.out.println(hand.get(hand.size()-1));
			valuesOfCardsInHand(Dealer, console, cardsInHand, deck, card);
		} 
		if(hand==player1hand){
			//prints player 1 cards along with how much money he has
			System.out.print("["+bet+"]"+cardsInHand.y+"'s cards: ");
			for(int i = 0; i<hand.size()-1; i++){
				System.out.print(hand.get(i)+" | ");
			}
			System.out.println(hand.get(hand.size()-1));
			valuesOfCardsInHand(player1hand, console, cardsInHand, deck, card);
			cardsInHand.playersMove(console, 1, deck, card, cardsInHand,numValue,x);
			showCardsInHand(console, player1hand, cardsInHand, deck, card);
			valuesOfCardsInHand(player1hand, console, cardsInHand, deck, card);
		} 
		if(hand==player2hand){
			//prints player 2 cards along with how much money he has
			System.out.print("["+bet1+"]"+cardsInHand.z+"'s cards: ");
			for(int i = 0; i<hand.size()-1; i++){
				System.out.print(hand.get(i)+" | ");
			}
			System.out.println(hand.get(hand.size()-1));
			valuesOfCardsInHand(player2hand, console, cardsInHand, deck, card);
			cardsInHand.playersMove(console, 2, deck, card, cardsInHand,numValue,x);
			showCardsInHand(console, player2hand, cardsInHand, deck, card);
			valuesOfCardsInHand(player2hand, console, cardsInHand, deck, card);
		} 
		if(hand==player3hand){
			//prints player 3 cards along with how much money he has
			System.out.print("["+bet2+"]"+cardsInHand.yz+"'s cards: ");
			for(int i = 0; i<hand.size()-1; i++){
				System.out.print(hand.get(i)+" | ");
			}
			System.out.println(hand.get(hand.size()-1));
			valuesOfCardsInHand(player3hand, console, cardsInHand, deck, card);
			cardsInHand.playersMove(console, 3, deck, card, cardsInHand,numValue,x);
			showCardsInHand(console, player3hand, cardsInHand, deck, card);
			valuesOfCardsInHand(player3hand, console, cardsInHand, deck, card);
		}
	}

	public int getCardValue(String lastCard) {
		//This method finds the value of each card
		//face cards are all ten, and number cards are the same as what's on the cards.
		//Aces are ones for now, but that changes later
		int x = 0;
		String cardName[] = lastCard.split(" ");
		if(cardName[0].equals("Ace")){
			x = 1;
		} else if(cardName[0].equals("Two")){
			x = 2;
		} else if(cardName[0].equals("Three")){
			x = 3;
		} else if(cardName[0].equals("Four")){
			x = 4;
		} else if(cardName[0].equals("Five")){
			x = 5;
		} else if(cardName[0].equals("Six")){
			x = 6;
		} else if(cardName[0].equals("Seven")){
			x = 7;
		} else if(cardName[0].equals("Eight")){
			x = 8;
		} else if(cardName[0].equals("Nine")){
			x = 9;
		} else if(cardName[0].equals("Ten") || cardName[0].equals("Jack") || cardName[0].equals("Queen") || cardName[0].equals("King")){
			x = 10;
		}
		return x;
	}

	public void valuesOfCardsInHand(ArrayList<String> hand, Scanner console, BlackJackPlayer cardsInHand, Deck deck, Card card){
		//The first part of this method calculates the total sum of a hand
		currentCardValue=0;
		numValue=0;
		for ( int i = 0;  i < hand.size();  i++ ) {
			String lastCard = hand.get(i);
			currentCardValue = getCardValue(lastCard);
			if (currentCardValue == 1) {
				if (numValue + 10 <= 21 )
					numValue+=10;
			}
			numValue = numValue + currentCardValue;
		}
		//The rest of this method deals with different cases of winning and losing between different players
		if(hand!=Dealer){
			if(numValue==21){
				System.out.println("YOU GOT 21!!!");
				if(hand==player1hand && cardsInHand.ab==1){
					x=21;
					System.out.println("You got one extra token!!!");
					bet++;
					valuesOfCardsInHand(Dealer, console, cardsInHand, deck, card);
				}
				if(hand==player1hand && cardsInHand.ab==2){
					x=21;
					System.out.println("You got one extra token!!!");
					bet++;
					showCardsInHand(console, player2hand, cardsInHand, deck, card);
				}
				if(hand==player1hand && cardsInHand.ab==3){
					x=21;
					System.out.println("You got one extra token!!!");
					bet++;
					showCardsInHand(console, player2hand, cardsInHand, deck, card);
				}
				if(hand==player2hand && cardsInHand.ab==2){
					n=21;
					System.out.println("You got one extra token!!!");
					bet1++;
					valuesOfCardsInHand(Dealer, console, cardsInHand, deck, card);
				}
				if(hand==player2hand && cardsInHand.ab==3){
					System.out.println("You got one extra token!!!");
					n=21;
					bet1++;
					showCardsInHand(console, player3hand, cardsInHand, deck, card);
				}
				if(hand==player3hand && cardsInHand.ab==3){
					a=21;
					System.out.println("You got one extra token!!!");
					bet2++;
					valuesOfCardsInHand(Dealer, console, cardsInHand, deck, card);
				}
				return;
			} else if(numValue>21){
				System.out.println("BUSTED!!!");
				if(hand==player1hand && cardsInHand.ab==1){
					x=0;
					valuesOfCardsInHand(Dealer, console, cardsInHand, deck, card);
				}
				if(hand==player1hand && cardsInHand.ab==2){
					x=0;
					showCardsInHand(console, player2hand, cardsInHand, deck, card);
				}
				if(hand==player1hand && cardsInHand.ab==3){
					x=0;
					showCardsInHand(console, player2hand, cardsInHand, deck, card);
				}
				if(hand==player2hand && cardsInHand.ab==2){
					n=0;
					valuesOfCardsInHand(Dealer, console, cardsInHand, deck, card);
				}
				if(hand==player2hand && cardsInHand.ab==3){
					n=0;
					showCardsInHand(console, player3hand, cardsInHand, deck, card);
				}
				if(hand==player3hand && cardsInHand.ab==3){
					a=0;
					valuesOfCardsInHand(Dealer, console, cardsInHand, deck, card);
				}
			}
		} else{
			//The last part of this method deals with the dealer's move, and then calls a method to find out who wins.
			if(numValue<=17){
				System.out.println("The Dealer must hit.");
				deck.dealCards(card.Dealer);
				showCardsInHand(console, Dealer, cardsInHand, deck, card);
			} else if(numValue==21){
				System.out.println("The Dealer has 21!!");
				whoWins(21,cardsInHand,console,deck,card, cardsInHand.ab);
			} else if(numValue>21){
				System.out.println("The Dealer has BUSTED!!!");
				whoWins(0,cardsInHand,console,deck,card, cardsInHand.ab);
			} else if(numValue>17){
				System.out.println("The dealer must stay");
				whoWins(numValue,cardsInHand,console,deck,card, cardsInHand.ab);
			}
		}
	}

	public void whoWins(int numValue, BlackJackPlayer bets,Scanner console, Deck deck, Card card, int ab){
		//This whole method calculates how much money players win or lose depending on whether they win or lose.
		if(ab==1){
			//This is for a one-player game
			if(numValue>x){
				System.out.println("The Dealer wins!!! Time to Pay up!");
				System.out.println(bets.y+" lost "+bets.tokensBet1+" tokens!");
				bet-=bets.tokensBet1;
			} else if(numValue<x){
				System.out.println("NOOOOOO! The Dealer Lost!");
				System.out.println(bets.y+" won "+bets.tokensBet1+" tokens!");
				bet+=bets.tokensBet1;
			} else if(numValue==x){
				System.out.println("PUSH!!! "+bets.y+" got lucky this time!");
			}
			if(bets.ab==1){
				bets.casinoEntrance(console, deck, card, bets);
			} else{
				showCardsInHand(console, player2hand, bets, deck, card);
			}

		} else if(ab==2){
			//This is for a two-player game
			if(bets.ab==2){
				if(numValue>x){
					System.out.println("The Dealer wins!!! Time to Pay up!");
					System.out.println(bets.y+" lost "+bets.tokensBet1+" tokens!");
					bet-=bets.tokensBet1;
				} else if(numValue<x){
					System.out.println("NOOOOOO! The Dealer Lost!");
					System.out.println(bets.y+" won "+bets.tokensBet1+" tokens!");
					bet+=bets.tokensBet1;
				} else if(numValue==x){
					System.out.println("PUSH!!! "+bets.y+" got lucky this time!");
				}
				if(numValue>n){
					System.out.println("The Dealer wins!!! Time to Pay up!");
					System.out.println(bets.z+" lost "+bets.tokensBet2+" tokens!");
					bet1-=bets.tokensBet2;
				} else if(numValue<n){
					System.out.println("NOOOOOO! The Dealer Lost!");
					System.out.println(bets.z+" won "+bets.tokensBet2+" tokens!");
					bet1+=bets.tokensBet2;
				} else if(numValue==n){
					System.out.println("PUSH!!! "+bets.z+" got lucky this time!");
				}
				bets.casinoEntrance(console, deck, card, bets);
			} else{
				whoWins(numValue, bets, console, deck, card, 3);
			}
		} else if(ab==3){
			//This is for a three-player game
			if(numValue>x){
				System.out.println("The Dealer wins!!! Time to Pay up!");
				System.out.println(bets.y+" lost "+bets.tokensBet1+" tokens!");
				bet-=bets.tokensBet1;
			} else if(numValue<x){
				System.out.println("NOOOOOO! The Dealer Lost!");
				System.out.println(bets.y+" won "+bets.tokensBet1+" tokens!");
				bet+=bets.tokensBet1;
			} else if(numValue==x){
				System.out.println("PUSH!!! "+bets.y+" got lucky this time!");
			}
			if(numValue>n){
				System.out.println("The Dealer wins!!! Time to Pay up!");
				System.out.println(bets.z+" lost "+bets.tokensBet2+" tokens!");
				bet1-=bets.tokensBet2;
			} else if(numValue<n){
				System.out.println("NOOOOOO! The Dealer Lost!");
				System.out.println(bets.z+" won "+bets.tokensBet2+" tokens!");
				bet1+=bets.tokensBet2;
			} else if(numValue==n){
				System.out.println("PUSH!!! "+bets.z+" got lucky this time!");
			}
			if(numValue>a){
				System.out.println("The Dealer wins!!! Time to Pay up!");
				System.out.println(bets.yz+" lost "+bets.tokensBet3+" tokens!");
				bet2-=bets.tokensBet3;
			} else if(numValue<a){
				System.out.println("NOOOOOO! The Dealer Lost!");
				System.out.println(bets.yz+" won "+bets.tokensBet3+" tokens!");
				bet2+=bets.tokensBet3;
			} else if(numValue==a){
				System.out.println("PUSH!!! "+bets.yz+" got lucky this time!");
			}
			bets.casinoEntrance(console, deck, card, bets);
		}
	}
}
