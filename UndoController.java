package Osmosis;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class UndoController implements MouseListener{

	
	Osmosis osmosis;
	JButton view;
	
	public UndoController(Osmosis o, JButton undoView){
		this.osmosis = o;
		this.view = undoView;
	}
	
	/**
	 * Respond to mouse click events.
	 */

	public void mouseClicked(MouseEvent me){
		System.out.println("Got to clicked");
		if(!osmosis.undo.isEmpty()){
			osmosis.undo.pop().undo();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
