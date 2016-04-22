package Osmosis;

import ks.common.model.Card;
import ks.common.model.Column;

public class Foundation extends Column {

	int[] ranksCanAdd;
	int suit;
	boolean played;

	/**
	 * Updates the array that determines whether a card can be added to this Foundation
	 * @param foundation The foundation that determines what cards can be added
	 */

	void updateCanAdd(Foundation foundation){
		for (int i = 0; i < foundation.numCards; i++){
			ranksCanAdd[i] = foundation.cards[i].getRank();
		}
	}

	/**
	 * Checks to see if the designated card can be added to this foundation
	 * @param card The card being checked
	 * @return returns true if the card can be added, otherwise it returns false
	 */

	boolean canAddCard(Card card){
		if(played){
			if (suit == card.getSuit()){
				for (int i = 0; i < this.numCards; i++){
					if (card.getRank() == ranksCanAdd[i]){
						return true;
					}
				}
			}
		return false;
		}
		else return true;
	}

	/**
	 * Initializes a foundation of an Osmosis game
	 * @param card the card to played in the first foundation
	 */

	void initialize(Card card){
		for (int i = 1; i < 14; i++){
			int j = i - 1;
			ranksCanAdd[j] = i;
		}

		this.cards[0] = card;
		this.suit = card.getSuit();
	}



}
