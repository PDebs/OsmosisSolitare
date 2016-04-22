package Osmosis;

public class WasteCardMove implements Undo {

	OsmosisWastePile from;
	Foundation to;
	private Osmosis osmosis;
	
	public WasteCardMove(OsmosisWastePile wp, Foundation f, Osmosis o){
		this.from = wp;
		this.to = f;
		this.osmosis = o;
	}

	@Override
	public void undo() {
		from.add(to.get());
		osmosis.refreshWidgets();
	}

}
