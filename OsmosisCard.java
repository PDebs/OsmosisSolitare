package Osmosis;

import ks.common.model.Card;
import ks.common.model.Column;

public class OsmosisCard {

	Card card;
	
	public OsmosisCard(Card c) {
		this.card = card;
	}
	
	boolean canAddFoundations(Column[] col){
		for (int i = 0; i < 4; i++){
			if (canAddFoundation(col[i])){
				return true;
			}
		}
		return false;
	}
	
	boolean canAddFoundation(Column col){
		Column temp = col;
		for (int i = 0; i < temp.count(); i++){
			if (this.card.equals(temp.get())){
				return true;
			}
		}
		return false;
	}
	
	void add(Column[] col){
		if (canAddFoundations(col)){
			for (int i = 0; i < 4; i++){
				if (canAddFoundation(col[i])){
					addFoundation(col[i]);
				}
			}
		}
	}
	
	void addFoundation(Column col){
		col.add(this.card);
	}

}
