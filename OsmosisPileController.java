package Osmosis;

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
	

}
