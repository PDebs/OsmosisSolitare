package Osmosis;

import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Deck;

public class OsmosisWastePile extends Column {

	/**
	 * Updates the waste pile to show the top 3 cards
	 */
	
	static Column background;
	
	public OsmosisWastePile() {
		OsmosisWastePile.background = new Column();
	}
	
	public void update(){
		while (this.count() < 3){
			this.add(background.get());
		}
	}
	
	public void addCard(Card card){
		if (this.count() > 2){
			Card temp1, temp2, temp3;
			temp1 = this.get();
			temp2 = this.get();
			temp3 = this.get();
			
			this.add(temp2);
			this.add(temp1);
			this.add(card);
			background.add(temp3);
		}
		else {
			this.add(card);
		}
	}

	public void refill(Deck deck) {
		while(this.count() > 0){
			deck.add(this.get());
		}
		while (background.count() > 0){
			deck.add(background.get());
		}
	}

	
}
