package Osmosis;

import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JPanel;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Pile;
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
	protected OsmosisWastePile wastePile;
	
	/** Each game has a waste pile view */
	protected ColumnView wasteView;
	
	/** Each game has a stack of moves */
	Stack<Undo> undo;
	
	/** Each game has an undo view */
	JButton undoView;
	
	
	
	@Override
	public String getName() {
		return "PDeBrine: Osmosis";
	}

	@Override
	public boolean hasWon() {
		boolean answer = false;
		for (int i = 0; i < 4; i++){
			if (foundations[i].count() == 13){
				answer = true;
			}
			else answer = false;
		}
		return answer;
	}

	@Override
	public void initialize() {
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
		foundationViews[i].setBounds(50+2*ci.getWidth()*(i+1), 50+ci.getHeight(), ci.getWidth(), ci.getHeight());
		container.addWidget(foundationViews[i]);
	}

	wasteView = new ColumnView (wastePile);
	wasteView.setBounds (20*2 + ci.getWidth(), 30, ci.getWidth(), ci.getHeight());
	container.addWidget (wasteView);

	scoreView = new IntegerView (getScore());
	scoreView.setBounds (20*7+7*ci.getWidth(), 30, 160, 60);
	container.addWidget (scoreView);

	numLeftView = new IntegerView (getNumLeft());
	numLeftView.setFontSize (14);
	numLeftView.setBounds (20 + ci.getWidth()/4, 10, ci.getWidth(), 20);
	container.addWidget (numLeftView);
	
	undoView = new JButton("Undo");
	undoView.setBounds(100, 1, 50, 15);
	container.add(undoView);
	

}


	private void initializeControllers() {
		// TODO Auto-generated method stub
		OsmosisDeckController deckController = new OsmosisDeckController(this, deckView);
		deckView.setMouseAdapter(deckController);
		
		for (int i = 0; i < 4; i++){
			OsmosisPileController pileController = new OsmosisPileController(this, pileViews[i], i);
			pileViews[i].setMouseAdapter(pileController);
		}
		
		OsmosisWastePileController wastePileController = new OsmosisWastePileController(this, wasteView);
		wasteView.setMouseAdapter(wastePileController);
		
		UndoController undoController = new UndoController(this, undoView);
		undoView.addMouseListener(undoController);
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
		wastePile = new OsmosisWastePile();
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
