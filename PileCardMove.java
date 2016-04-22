package Osmosis;

import ks.common.model.Pile;

public class PileCardMove implements Undo {
	
	Pile from;
	Foundation to;
	private Osmosis osmosis;
	
	PileCardMove(Pile p, Foundation f, Osmosis o){
		this.from = p;
		this.to = f;
		this.osmosis = o;
	}

	@Override
	public void undo() {
		from.add(to.get());
		osmosis.refreshWidgets();
	}
	
}
		