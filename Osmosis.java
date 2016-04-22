package Osmosis;

import heineman.Klondike;
import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Pile;
import ks.common.view.BuildablePileView;
import ks.common.view.CardImages;
import ks.common.view.ColumnView;
import ks.common.view.DeckView;
import ks.common.view.IntegerView;
import ks.common.view.PileView;
import ks.launcher.Main;

public class Osmosis extends Solitaire {

	/** Each Game has a Deck. */
	protected Deck deck;

	/** And four Piles  */
	protected Pile[] piles;
	
	/** Four Pile views */
	protected PileView[] pileViews;
	
	/** The view of the deck */
	protected DeckView deckView;
	
	/** The display for the score. */
	protected IntegerView scoreView;
	
	/** View for the number of cards left in the deck. */
	protected IntegerView numLeftView;
	
	/** Each game has four foundations */
	protected Foundation[] foundations;
	
	/** Each game has four foundation views */
	protected ColumnView[] foundationViews;
	
	/** Each game has a waste pile */
	protected Pile wastePile;
	
	/** Each game has a waste pile view */
	protected PileView wasteView;
	
	
	@Override
	public String getName() {
		return "PDeBrine: Osmosis";
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
		fillPiles();
		Card card = deck.get();
		foundations[0].initializeAsTop(card);
		foundations[0].add(card);
		updateScore(0);
		updateNumberCardsLeft(35);
	}


/**
 * initializes the views
 */
protected void initializeViews() {
	CardImages ci = getCardImages();

	deckView = new DeckView (deck);
	deckView.setBounds (20,30, ci.getWidth(), ci.getHeight());
	container.addWidget (deckView);

	// create PileViews, one after the other.
	pileViews = new PileView[4];
	for (int pileNum = 0; pileNum <4; pileNum++) {
		pileViews[pileNum] = new PileView (piles[pileNum]);
		pileViews[pileNum].setBounds (ci.getWidth(), 50+ci.getHeight()*(pileNum+1), ci.getWidth(), ci.getHeight());
		container.addWidget(pileViews[pileNum]);
	}
	
	// Create the foundation views
	foundationViews = new ColumnView[4];
	for (int i = 0; i < 4; i++){
		foundationViews[i] = new ColumnView(foundations[i]);
		foundationViews[i].setBounds(3*ci.getWidth(), 50+ci.getHeight()*(i+1), ci.getWidth(), ci.getHeight());
		container.addWidget(foundationViews[i]);
	}

	wasteView = new PileView (wastePile);
	wasteView.setBounds (20*2 + ci.getWidth(), 30, ci.getWidth(), ci.getHeight());
	container.addWidget (wasteView);

	scoreView = new IntegerView (getScore());
	scoreView.setBounds (20*7+7*ci.getWidth(), 30, 160, 60);
	container.addWidget (scoreView);

	numLeftView = new IntegerView (getNumLeft());
	numLeftView.setFontSize (14);
	numLeftView.setBounds (20 + ci.getWidth()/4, 10, ci.getWidth(), 20);
	container.addWidget (numLeftView);

}


	private void initializeControllers() {
		// TODO Auto-generated method stub
		OsmosisDeckController deckController = new OsmosisDeckController(this, deckView);
		deckView.setMouseAdapter(deckController);
		
		for (int i = 0; i < 4; i++){
			OsmosisPileController pileController = new OsmosisPileController(this, pileViews[i], i);
			pileViews[i].setMouseAdapter(pileController);
		}
	}

	private void initializeModel(int seed) {

		// Generate the deck
		this.deck = new Deck("Deck");
		deck.create(seed);
		model.addElement(deck);
		
		// Generate the Foundations
		foundations = new Foundation[4];
		for (int i = 0; i < 4; i++){
			foundations[i] = new Foundation();
			foundations[i].setNum(i);
			model.addElement(foundations[i]);
		}
		
		
		// Generate the Piles
		piles = new Pile[4];
		for (int i = 0; i < 4; i++){
			piles[i] = new Pile();
			model.addElement(piles[i]);
		}
		
		// Generate waste pile
		wastePile = new Pile("WastePile");
		model.addElement(wastePile);
		
		
	}
	
	private void fillPiles(){
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				piles[i].add(deck.get());
			}
		}
	}
	
	
	/** Code to launch solitaire variation. */
	public static void main (String []args) {
		// Seed is to ensure we get the same initial cards every time.
		// Here the seed is to "order by suit."
		Main.generateWindow(new Osmosis(), Deck.OrderBySuit);
	}

}
