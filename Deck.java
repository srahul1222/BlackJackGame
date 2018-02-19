/**
 * 
 */
package BlackjackGame;

import java.util.ArrayList;

/**
 * @author Rahul Swaminathan
 *8/1/15
 */
public class Deck {
	String[] deck = new String[52];
	ArrayList<Integer> sameValue = new ArrayList<Integer>();
	String[] suit = {" of Spades", " of Diamonds", " of Clubs", " of Hearts"}; //These represent the four suits
	String[] faceValue = {"Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"}; //These represent the 13 different faces of each suit
	int cardsDealt=0;

	public void shuffleDeck() {
		sameValue.clear(); // clears the arraylist
		//The first part of this method creates a deck of 52 cards
		int numberOfCard = 0;
		for ( int k = 0; k <= 3; k++ ) {
			for ( int j = 0; j < 13; j++ ) {
				deck[numberOfCard] = faceValue[j]+suit[k];
				numberOfCard++;
			}
		}
		//The second part of this method shuffles the deck by choosing random spots and switching them
		for ( int i = 51; i >= 0; i-- ) {
			int randomshuffle = (int)(Math.random()*52);
			sameValue.add(randomshuffle);
			if(sameValue.size()>1){
				for(int l = 0;l<sameValue.size()-2;l++){
					if(sameValue.get(l)==randomshuffle){
						int b = sameValue.size()-1;
						sameValue.remove(b);
						l--;
						randomshuffle = (int)(Math.random()*52);
						sameValue.add(randomshuffle);
					}
				}
			}
			String shuffleddeck = deck[i];
			deck[i] = deck[randomshuffle];
			deck[randomshuffle] = shuffleddeck;
		}
	}

	public void dealCards(ArrayList<String> hand){
		while(cardsDealt<52){
			hand.add(deck[cardsDealt]);
			cardsDealt++;
			//System.out.println(toString());
			//this toString method prints every card that is being dealt
			return;
		}
		if (cardsDealt == 52){
			shuffleDeck();
		}
	}
	//This is my toString method
	public String toString(){
		String cardDealt = "The card given was the "+deck[cardsDealt];
		return cardDealt;
	}
}
