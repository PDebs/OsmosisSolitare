package Osmosis;

import ks.common.model.Column;

public class DealThreeMove implements Undo {

	Column cards;
	Osmosis osmosis;
	
	public DealThreeMove(Osmosis o, Column col){
		this.osmosis = o;
		this.cards = col;
	}
	
	public void undo(){
		System.out.println("Got here");
		
		osmosis.deck.add(osmosis.wastePile.get());
		osmosis.deck.add(osmosis.wastePile.get());
		osmosis.deck.add(osmosis.wastePile.get());
		
		osmosis.wastePile.add(cards.get());
		osmosis.wastePile.add(cards.get());
		osmosis.wastePile.add(cards.get());
		osmosis.refreshWidgets();
	}
	
	
	
	

}
