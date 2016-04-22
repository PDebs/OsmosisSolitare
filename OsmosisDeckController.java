package Osmosis;

import ks.common.model.Deck;
import ks.common.view.DeckView;

public class OsmosisDeckController extends java.awt.event.MouseAdapter {

	Osmosis osmosis;
	DeckView view;
	
	public OsmosisDeckController(Osmosis o, DeckView v){
		this.osmosis = o;
		this.view = v;
	}
	
	/**
     * A MouseClicked event on the DeckView is a signal to deal the next
     * set of cards (if the deck is nonempty).
     *
     * @param me     low-level mouse event
     */
	
	public void mouseClicked(java.awt.event.MouseEvent me){
		System.out.println("Clicked");
		Deck deck = getDeck();
		OsmosisWastePile wastePile = getWastePile();
		
		if (deck.count() == 0){
			refillDeck(deck, wastePile);
			osmosis.updateNumberCardsLeft(deck.count());
		}
		else {
			if (3 < deck.count()){
				System.out.println("Trying to get" + deck.count());
				wastePile.addCard(deck.get());
				System.out.println(deck.count());
				wastePile.addCard(deck.get());
				wastePile.addCard(deck.get());
				osmosis.updateNumberCardsLeft(-3);
			}
			else while(0 < deck.count()){
				wastePile.addCard(deck.get());
				osmosis.updateNumberCardsLeft(-1);
			}
			wastePile.update();
		}
		osmosis.refreshWidgets();
	}

	private OsmosisWastePile getWastePile() {
		return osmosis.wastePile;
	}

	private Deck getDeck() {
		return osmosis.deck;
	}
	
	/**
	 * Refills the deck with the waste pile
	 * @param deck the deck to be refilled
	 * @param wastePile the pile to be emptied
	 */
	
	void refillDeck(Deck deck,OsmosisWastePile wastePile){
		wastePile.refill(deck);
	}
}
