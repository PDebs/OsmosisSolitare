package Osmosis;

import java.awt.event.MouseEvent;

import ks.common.model.Card;
import ks.common.view.ColumnView;

public class OsmosisWastePileController extends java.awt.event.MouseAdapter {

	/** The game */
	Osmosis osmosis;
	/** the view associated with this controller */
	ColumnView view;

	public OsmosisWastePileController(Osmosis o, ColumnView v){
		super();

		this.osmosis = o;
		this.view = v;
	}

	/**
	 * Respond to mouse click events.
	 */

	public void mouseClicked(MouseEvent me){
		Foundation[] foundations = this.getFoundations();
		OsmosisWastePile pile = this.getPile();
		Card card = pile.get();
		boolean played = false;

		for (int i = 0; i < 4; i++){
			if (i > 0){
				foundations[i].updateCanAdd(foundations[i - 1]);
			}
			if (addToFoundation(foundations[i], card)){
				played = true;
				break;
			}
			
			}
		if (!played){
			pile.addCard(card);
		}
		osmosis.wastePile.update();
		osmosis.refreshWidgets();

	}

	/**
	 * Gets the Foundations associated with the game
	 * @return returns the Foundation[]
	 */

	private Foundation[] getFoundations(){
		return osmosis.foundations;
	}

	/**
	 * Gets the pile associated with this controller
	 * @return returns the pile 
	 */

	private OsmosisWastePile getPile(){
		return osmosis.wastePile;
	}

	/**
	 * Adds the card to the foundation, does nothing if it is an invalid move
	 * @param foundation the foundation to be added to
	 * @param card the card to be added
	 * @return returns true if something is changed, otherwise false
	 */

	private boolean addToFoundation(Foundation foundation, Card card){
		if (foundation.played){	
			if (foundation.canAddCard(card)){
				foundation.add(card);
				osmosis.getScore().increment(1);
				Foundation temp = null;
				for (int i = 0; i < 4; i++){
					if (card.getSuit() == getFoundations()[i].suit){
						temp = getFoundations()[i];
					}
				}
				//osmosis.undo.push(new WasteCardMove(getPile(), temp, osmosis));
				return true;
			}
			return false;
		}
		else if (foundation.notPlayed(card, osmosis.foundations[foundation.num - 1])){
			foundation.add(card);
			osmosis.getScore().increment(1);
			Foundation temp = null;
			for (int i = 0; i < 4; i++){
				if (card.getSuit() == getFoundations()[i].suit){
					temp = getFoundations()[i];
				}
			}
			//osmosis.undo.push(new WasteCardMove(getPile(), temp, osmosis));
			return true;
		}
		return false;
	}



}

