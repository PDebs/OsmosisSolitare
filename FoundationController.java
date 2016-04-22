package Osmosis;

import java.awt.event.MouseListener;

public class FoundationController {

	Osmosis osmosis;
	FoundationView view;
	Foundation foundation;
	
	
	public FoundationController(Osmosis o, FoundationView v) {
		this.osmosis = o;
		this.view = v;
		this.foundation = view.foundation;
	}
	

}
