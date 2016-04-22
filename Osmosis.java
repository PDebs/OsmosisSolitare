package Osmosis;

import ks.common.games.Solitaire;
import ks.common.model.Deck;
import ks.common.model.Pile;
import ks.common.view.DeckView;
import ks.common.view.IntegerView;

public class Osmosis extends Solitaire {

	/** Each Game has a Deck. */
	protected Deck deck;

	/** And four Piles  */
	protected Pile[] piles = new Pile[4];
	
	/** The view of the deck */
	protected DeckView deckView;
	
	/** The display for the score. */
	protected IntegerView scoreView;
	
	/** View for the number of cards left in the deck. */
	protected IntegerView numLeftView;
	
	/** Each game has four foundations */
	protected Foundation[] foundations = new Foundation[4];
	
	
	@Override
	public String getName() {
		// TODO Make variable with the name as a string
		return null;
	}

	@Override
	public boolean hasWon() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
		// Initialize everything
		initializeModel(getSeed());
		initializeViews();
		initializeControllers();
		
		// Prepare the Game
		updateScore(0);
		updateNumberCardsLeft(51);
		
	}

	private void initializeControllers() {
		// TODO Auto-generated method stub
		
	}

	private void initializeViews() {
		// TODO Auto-generated method stub
		
	}

	private void initializeModel(int seed) {
		// TODO Auto-generated method stub
		
		// Generate the deck
		this.deck = new Deck("Deck");
		deck.create(seed);
		model.addElement(deck);
		
		// Generate the Foundations
		for (int i = 0; i < 4; i++){
			foundations[i] = new Foundation();
			model.addElement(foundations[i]);
		}
		
		
		// Generate the Piles
		for (int i = 0; i < 4; i++){
			piles[i] = new Pile();
			model.addElement(piles[i]);
		}
		
		
	}

}
