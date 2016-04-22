package Osmosis;

import java.awt.event.MouseEvent;

import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Pile;
import ks.common.view.PileView;

public class OsmosisPileController extends java.awt.event.MouseAdapter {

	Osmosis osmosis;
	PileView view;
	
	public OsmosisPileController(Osmosis o, PileView v){
		super();
		
		this.osmosis = o;
		this.view = v;
	}
	
	/**
	 * Respond to mouse click events.
	 */
	public void mouseClicked(MouseEvent me){
		Foundation[] foundations = this.getFoundations();
		Pile pile = this.getPile();
		Card card = pile.get();
		
		for (int i = 0; i < 4; i++){
			addToFoundation(foundations[i], card);
			break;
		}
		
	}
	
	private Foundation[] getFoundations(){
		// TODO
		return null;
	}
	
	private Pile getPile(){
		// TODO
		return null;
	}
	
	private void addToFoundation(Foundation foundation, Card card){
		if (foundation.canAddCard(card)){
			foundation.add(card);
		}
	}
	
	

}
