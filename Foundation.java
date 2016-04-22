package Osmosis;

import ks.common.model.Card;
import ks.common.model.Column;

public class Foundation extends Column {

	/** Array of ints representing the suits that can be added to this foundation */
	int [] ranksCanAdd = new int[maxPileSize];
	/** the suit of this foundation */
	int suit;
	/** determines whether the foundation has been played upon or if its empty */
	boolean played;
	/** Faundation knows which one it is */
	int num;
	
	/**
	 * Sets the num value
	 * @param num the number of the foundation
	 */
	
	public void setNum(int num){
		this.num = num;
	}

	/**
	 * Updates the array that determines whether a card can be added to this Foundation
	 * @param foundation The foundation that determines what cards can be added
	 */

	void updateCanAdd(Foundation foundation){
		for (int i = 0; i < foundation.count(); i++){
			ranksCanAdd[i] = foundation.cards[i].getRank();
		}
	}

	/**
	 * Checks to see if the designated card can be added to this foundation
	 * @param card The card being checked
	 * @return returns true if the card can be added, otherwise it returns false
	 */

	boolean canAddCard(Card card){
			if (suit == card.getSuit()){
				for (int i = 0; i < maxPileSize; i++){
					if (card.getRank() == ranksCanAdd[i]){
						return true;
					}
				}
			}
			return false;
	}

	/**
	 * Initializes a foundation of an Osmosis game
	 * @param card the card to played in the first foundation
	 */

	void initializeAsTop(Card card){
		for (int i = 1; i < maxPileSize+1; i++){
			int j = i - 1;
			ranksCanAdd[j] = i;
		}
		this.suit = card.getSuit();
		played = true;
	}
	
	void initialize(Card card){
		this.suit = card.getSuit();
		played = true;
	}
	/**
	 * Called when a foundation has not been played on but might be able to
	 * @param card The card to potentially be added
	 * @param parent The foundation above this foundation, if it is the first foundation, this should never be called
	 * @return returns true if a foundation is changed, otherwise false
	 */
	
	public boolean notPlayed(Card card, Foundation parent){
		assert (num > 0);
		this.updateCanAdd(parent);
		for (int i = 0; i < ranksCanAdd.length; i++){
			if (card.getRank() == (ranksCanAdd[i])){
				this.initialize(card);
				return true;
			}
		}
		return false;
	}



}
