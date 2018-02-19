package BlackjackGame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BlackJackPlayer {
	int tokensBet1; //for player 1
	int tokensBet2; //for player 2
	int tokensBet3; //for player 3
	public String y;//for player 1
	public String z;//for player 2
	public String yz;//for player 3
	int ab=0; //to find out how many people want to play

	public void casinoEntrance(Scanner console, Deck deck, Card card, BlackJackPlayer BlackJackPlayer){
		//These if statements make it so that if any player loses all of their money, he and his friends are kicked out
		if(card.bet==0){
			System.out.println("You are out of money. You are now WORTHLESS!!!");
			System.out.println("Get outta here 'fore I call my security!!!");
			System.exit(0);
		}
		if(card.bet1==0){
			System.out.println("You are out of money. You are now WORTHLESS!!!");
			System.out.println("Get outta here 'fore I call my security!!!");
			System.exit(0);
		}
		if(card.bet2==0){
			System.out.println("You are out of money. You are now WORTHLESS!!!");
			System.out.println("Get outta here 'fore I call my security!!!");
			System.exit(0);
		}
		card.Dealer.clear(); //this clears each player's hand after each round
		card.player1hand.clear(); //this clears each player's hand after each round
		card.player2hand.clear(); //this clears each player's hand after each round
		card.player3hand.clear(); //this clears each player's hand after each round
		System.out.println("Welcome to Rahul's Casino. Would you like to TRY to beat me in Blackjack?");
		//All of these questions determine who is playing.
		String b = console.next();
		console.nextLine();
		if(b.equalsIgnoreCase("yes")||(b.equalsIgnoreCase("y"))){
			System.out.println();
			System.out.println("Will any of your friends be joining us? You can invite a maximum of two friends.");
			String c = console.next();
			console.nextLine();
			if(c.equalsIgnoreCase("yes")||(c.equalsIgnoreCase("y"))){
				System.out.println();
				System.out.println("How many? 1 or 2?");
				String x =console.next();
				console.nextLine();
				if(x.equalsIgnoreCase("1")){
					ab = 2;
					System.out.println();
					System.out.println("Please state your name: ");
					y =console.next();
					console.nextLine();
					System.out.println("Please state your friend's name: ");
					z =console.next();
					console.nextLine();
					opening(console, deck, card, BlackJackPlayer,2);

				} else if(x.equalsIgnoreCase("2")){
					ab = 3;
					System.out.println();
					System.out.println("Please state your name: ");
					y =console.next();
					console.nextLine();
					System.out.println("Please state your friend's name: ");
					z =console.next();
					console.nextLine();
					System.out.println("Please state your other friend's name: ");
					yz =console.next();
					console.nextLine();
					opening(console, deck, card, BlackJackPlayer,3);
				} else if(!x.equalsIgnoreCase("2") && !x.equalsIgnoreCase("1")){
					System.out.println("That is not a valid option.");
					System.out.println("Get outta here 'fore I call my security!!!");
					System.out.println();
					System.out.println();
					casinoEntrance(console, deck, card, BlackJackPlayer);
				}

			} else if(c.equalsIgnoreCase("no")||(c.equalsIgnoreCase("n"))){
				ab = 1;
				System.out.println();
				System.out.println("Please state your name: ");
				y =console.next();
				console.nextLine();
				opening(console, deck, card, BlackJackPlayer,1);
			} else if((!c.equalsIgnoreCase("no")||(!c.equalsIgnoreCase("n"))) && (!c.equalsIgnoreCase("yes")||(!c.equalsIgnoreCase("y")))){
				System.out.println("That is not a valid option.");
				System.out.println("Get outta here 'fore I call my security!!!");
				System.out.println();
				System.out.println();
				casinoEntrance(console, deck, card, BlackJackPlayer);
			}

		} else if(b.equalsIgnoreCase("no")||(b.equalsIgnoreCase("n"))){
			console.close();
			System.out.println("Good bye!");
			System.exit(0);

		} else if((!b.equalsIgnoreCase("no")||(!b.equalsIgnoreCase("n"))) && (!b.equalsIgnoreCase("yes")||(!b.equalsIgnoreCase("y")))){
			System.out.println("That is not a valid option.");
			System.out.println("Get outta here 'fore I call my security!!!");
			System.out.println();
			System.out.println();
			casinoEntrance(console, deck, card, BlackJackPlayer);
		}
	}

	public void playersMove(Scanner console, int listNum, Deck deck, Card card, BlackJackPlayer cardsInHand, int numValue, int x){
		if(listNum==1){ // for player 1
			System.out.print("Would "+y+" like to Hit, Stay, Double Down, Split, or Surrender? (H,S,D,ST,--): ");
			String input =console.next();
			console.nextLine();
			if(input.equalsIgnoreCase("H")){
				//if hit, this deals one card to the player's hand
				deck.dealCards(card.player1hand);
			} else if (input.equalsIgnoreCase("S") && ab==1){
				//if stay, the cards don't change and the next player's turn is called
				card.x=numValue;
				card.valuesOfCardsInHand(card.Dealer, console, cardsInHand, deck, card);
			} else if (input.equalsIgnoreCase("S") && ab!=1){
				//if stay, the cards don't change and the next player's turn is called
				card.x=numValue;
				card.showCardsInHand(console, card.player2hand, cardsInHand, deck, card);
			}else if(input.equalsIgnoreCase("D") && cardsInHand.tokensBet1<=(card.bet/2)){
				//if Double Down, the players bet is doubled and a card is dealt to his hand
				cardsInHand.tokensBet1*= 2;
				deck.dealCards(card.player1hand);
			} else if(input.equalsIgnoreCase("D")){
				//This statement occurs when someone tries to double down on more than half their bet.
				System.out.println("That is not a valid option right now because you do not have enough money.");
				playersMove(console,listNum,deck,card,cardsInHand,numValue,x);
			} else if(input.equalsIgnoreCase("--")){
				//This results in the player losing half their bet, and the dealer wins
				System.out.println(y+" surrendered and LOST half your bet!!!");
				card.bet-=(cardsInHand.tokensBet1/2);
				cardsInHand.casinoEntrance(console, deck, card, cardsInHand);
			} else if(input.equalsIgnoreCase("ST")){
				//Split was very hard for me to do, and I have no time on Friday or Saturday.
				System.out.println("I'm sorry. I did not have time to make this option yet.");
			} else if(!input.equalsIgnoreCase("D") && !input.equalsIgnoreCase("S") && !input.equalsIgnoreCase("H") && !input.equalsIgnoreCase("--")){
				//This statement occurs when a user enters an invalid move
				System.out.println("That is not a valid option right now.");
				playersMove(console,listNum,deck,card,cardsInHand,numValue,x);
			}
		}
		if(listNum==2){ //for player 2
			System.out.print("Would "+z+" like to Hit, Stay, Double Down, Split, or Surrender? (H,S,D,ST,--): ");
			String input =console.next();
			console.nextLine();
			if(input.equalsIgnoreCase("H")){
				//if hit, this deals one card to the player's hand
				deck.dealCards(card.player2hand);
			} else if (input.equalsIgnoreCase("S") && ab!=3){
				//if stay, the cards don't change and the Dealer's turn is called
				card.n=numValue;
				card.valuesOfCardsInHand(card.Dealer, console, cardsInHand, deck, card);
			} else if (input.equalsIgnoreCase("S") && ab==3){
				//if stay, the cards don't change and the next player's turn is called
				card.n=numValue;
				card.showCardsInHand(console, card.player3hand, cardsInHand, deck, card);
			} else if(input.equalsIgnoreCase("D") && cardsInHand.tokensBet2<=(card.bet1/2)){
				//if Double Down, the players bet is doubled and a card is dealt to his hand
				cardsInHand.tokensBet2*= 2;
				deck.dealCards(card.player2hand);
			} else if(input.equalsIgnoreCase("D")){
				//This statement occurs when someone tries to double down on more than half their bet.
				System.out.println("That is not a valid option right now because you do not have enough money.");
				playersMove(console,listNum,deck,card,cardsInHand,numValue,x);
			} else if(input.equalsIgnoreCase("--")){
				//This results in the player losing half their bet, and the dealer wins
				System.out.println("You surrendered and LOST half your bet!!!");
				card.bet1-=(cardsInHand.tokensBet2/2);
				cardsInHand.casinoEntrance(console, deck, card, cardsInHand);
			} else if(input.equalsIgnoreCase("ST")){
				//Split was very hard for me to do, and I have no time on Friday or Saturday.
				System.out.println("I'm sorry. I did not have time to make this option yet.");
			} else if(!input.equalsIgnoreCase("D") && !input.equalsIgnoreCase("S") && !input.equalsIgnoreCase("H") && !input.equalsIgnoreCase("--")){
				//This statement occurs when a user enters an invalid move
				System.out.println("That is not a valid option right now because you do not have enough money.");
				playersMove(console,listNum,deck,card,cardsInHand,numValue,x);
			}
		}
		if(listNum==3){
			System.out.print("Would "+yz+" like to Hit, Stay, Double Down, Split, or Surrender? (H,S,D,ST,--): ");
			String input =console.next();
			console.nextLine();
			if(input.equalsIgnoreCase("H")){
				//if hit, this deals one card to the player's hand
				deck.dealCards(card.player3hand);
			} else if (input.equalsIgnoreCase("S")){
				//if stay, the cards don't change and the next player's turn is called
				card.a=numValue;
				card.valuesOfCardsInHand(card.Dealer, console, cardsInHand, deck, card);
			} else if(input.equalsIgnoreCase("D") && cardsInHand.tokensBet3<=(card.bet/2)){
				//if Double Down, the players bet is doubled and a card is dealt to his hand
				cardsInHand.tokensBet3*= 2;
				deck.dealCards(card.player3hand);
			} else if(input.equalsIgnoreCase("D")){
				//This statement occurs when someone tries to double down on more than half their bet.
				System.out.println("That is not a valid option right now because you do not have enough money.");
				playersMove(console,listNum,deck,card,cardsInHand,numValue,x);
			} else if(input.equalsIgnoreCase("--")){
				//This results in the player losing half their bet, and the dealer wins
				System.out.println("You surrendered and LOST half your bet!!!");
				card.bet2-=(cardsInHand.tokensBet3/2);
				cardsInHand.casinoEntrance(console, deck, card, cardsInHand);
			} else if(input.equalsIgnoreCase("ST")){
				//This statement occurs when a user enters an invalid move
				System.out.println("I'm sorry. I did not have time to make this option yet.");
			} else if(!input.equalsIgnoreCase("D") && !input.equalsIgnoreCase("S") && !input.equalsIgnoreCase("H") && !input.equalsIgnoreCase("--")){
				//This statement occurs when a user enters an invalid move
				System.out.println("That is not a valid option right now because you do not have enough money.");
				playersMove(console,listNum,deck,card,cardsInHand,numValue,x);
			}
		}
	}

	public void opening(Scanner console, Deck deck, Card card, BlackJackPlayer BlackJackPlayer, int numOfPlayers){
		//This method is called at the start of each game to display each player's money after the previous round, and to deal cards and shuffle the deck
		if(numOfPlayers==1){
			System.out.println(y+" will start off with "+card.bet+" tokens");
		} else if(numOfPlayers==2){
			System.out.println(y+" will start off with "+card.bet+" tokens");
			System.out.println(z+" will start off with "+card.bet1+" tokens");
		} else if(numOfPlayers==3){
			System.out.println(y+" will start off with "+card.bet+" tokens");
			System.out.println(z+" will start off with "+card.bet1+" tokens");
			System.out.println(yz+" will start off with "+card.bet2+" tokens");
		}
		System.out.println("The cards are being shuffled and dealt as we speak");
		System.out.println();
		if(numOfPlayers==1){
			System.out.println("How much would "+y+" like to bet on this match?: ");
			try{
				tokensBet1 =console.nextInt();
				console.nextLine();
			} catch(InputMismatchException e){
				console.nextLine();
				System.out.println("That is an invalid amount of money to bet!");
				System.out.println("I suspect that you are cheating!");
				System.out.println("Get outta here 'fore I call my security!!!");
				System.out.println();
				System.out.println();
				casinoEntrance(console, deck, card, BlackJackPlayer);
			}
			if(tokensBet1>card.bet || tokensBet1<=0){
				System.out.println("You ain't got that kinda money!!");
				System.out.println("Get outta here 'fore I call my security!!!");
				System.out.println();
				System.out.println();
				casinoEntrance(console, deck, card, BlackJackPlayer);
			}
			System.out.println();
			deck.shuffleDeck();
			deck.dealCards(card.Dealer);
			deck.dealCards(card.Dealer);
			deck.dealCards(card.player1hand);
			deck.dealCards(card.player1hand);
			card.showCardsInHand(console, card.Dealer,BlackJackPlayer, deck, card);
			card.showCardsInHand(console, card.player1hand,BlackJackPlayer, deck, card);
		} else if(numOfPlayers==2){
			System.out.println("How much would "+y+" like to bet on this match?: ");
			try{
				tokensBet1 =console.nextInt();
				console.nextLine();
			} catch(InputMismatchException e){
				console.nextLine();
				System.out.println("That is an invalid amount of money to bet!");
				System.out.println("I suspect that you are cheating!");
				System.out.println("Get outta here 'fore I call my security!!!");
				System.out.println();
				System.out.println();
				casinoEntrance(console, deck, card, BlackJackPlayer);
			}
			if(tokensBet1>card.bet || tokensBet1<=0){
				System.out.println("You ain't got that kinda money!!");
				System.out.println("Get outta here 'fore I call my security!!!");
				System.out.println();
				System.out.println();
				casinoEntrance(console, deck, card, BlackJackPlayer);
			}
			System.out.println();
			System.out.println("How much would "+z+" like to bet on this match?: ");
			try{
				tokensBet2 =console.nextInt();
				console.nextLine();
			} catch(InputMismatchException e){
				console.nextLine();
				System.out.println("That is an invalid amount of money to bet!");
				System.out.println("I suspect that you are cheating!");
				System.out.println("Get outta here 'fore I call my security!!!");
				System.out.println();
				System.out.println();
				casinoEntrance(console, deck, card, BlackJackPlayer);
			}
			if(tokensBet2>card.bet1 || tokensBet2<=0){
				System.out.println("You ain't got that kinda money!!");
				System.out.println("Get outta here 'fore I call my security!!!");
				System.out.println();
				System.out.println();
				casinoEntrance(console, deck, card, BlackJackPlayer);
			}
			System.out.println();
			deck.shuffleDeck();
			deck.dealCards(card.Dealer);
			deck.dealCards(card.Dealer);
			deck.dealCards(card.player1hand);
			deck.dealCards(card.player1hand);
			deck.dealCards(card.player2hand);
			deck.dealCards(card.player2hand);
			card.showCardsInHand(console, card.Dealer,BlackJackPlayer, deck, card);
			card.showCardsInHand(console, card.player1hand,BlackJackPlayer, deck, card);
		} else if(numOfPlayers==3){
			System.out.println("How much would "+y+" like to bet on this match?: ");
			try{
				tokensBet1 =console.nextInt();
				console.nextLine();
			} catch(InputMismatchException e){
				console.nextLine();
				System.out.println("That is an invalid amount of money to bet!");
				System.out.println("I suspect that you are cheating!");
				System.out.println("Get outta here 'fore I call my security!!!");
				System.out.println();
				System.out.println();
				casinoEntrance(console, deck, card, BlackJackPlayer);
			}

			if(tokensBet1>card.bet || tokensBet1<=0){
				System.out.println("You ain't got that kinda money!!");
				System.out.println("Get outta here 'fore I call my security!!!");
				System.out.println();
				System.out.println();
				casinoEntrance(console, deck, card, BlackJackPlayer);
			}
			System.out.println();
			System.out.println("How much would "+z+" like to bet on this match?: ");
			try{
				tokensBet2 =console.nextInt();
				console.nextLine();
			} catch(InputMismatchException e){
				console.nextLine();
				System.out.println("That is an invalid amount of money to bet!");
				System.out.println("I suspect that you are cheating!");
				System.out.println("Get outta here 'fore I call my security!!!");
				System.out.println();
				System.out.println();
				casinoEntrance(console, deck, card, BlackJackPlayer);
			}
			if(tokensBet2>card.bet1 || tokensBet2<=0){
				System.out.println("You ain't got that kinda money!!");
				System.out.println("Get outta here 'fore I call my security!!!");
				System.out.println();
				System.out.println();
				casinoEntrance(console, deck, card, BlackJackPlayer);
			}
			System.out.println();
			System.out.println("How much would "+yz+" like to bet on this match?: ");
			try{
				tokensBet3 =console.nextInt();
				console.nextLine();
			} catch(InputMismatchException e){
				console.nextLine();
				System.out.println("That is an invalid amount of money to bet!");
				System.out.println("I suspect that you are cheating!");
				System.out.println("Get outta here 'fore I call my security!!!");
				System.out.println();
				System.out.println();
				casinoEntrance(console, deck, card, BlackJackPlayer);
			}
			if(tokensBet3>card.bet2 || tokensBet3<=0){
				System.out.println("You ain't got that kinda money!!");
				System.out.println("Get outta here 'fore I call my security!!!");
				System.out.println();
				System.out.println();
				casinoEntrance(console, deck, card, BlackJackPlayer);
			}
			System.out.println();
			deck.shuffleDeck();
			deck.dealCards(card.Dealer);
			deck.dealCards(card.Dealer);
			deck.dealCards(card.player1hand);
			deck.dealCards(card.player1hand);
			deck.dealCards(card.player2hand);
			deck.dealCards(card.player2hand);
			deck.dealCards(card.player3hand);
			deck.dealCards(card.player3hand);
			card.showCardsInHand(console, card.Dealer,BlackJackPlayer, deck, card);
			card.showCardsInHand(console, card.player1hand,BlackJackPlayer, deck, card);
		}
	}
}