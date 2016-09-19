package imageEditor;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BlurTool implements Tool{

	private BlurToolUI ui;
	private ImageEditorModel model;

	
	public BlurTool(ImageEditorModel model) {
		this.model = model;
		ui = new BlurToolUI();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//model.add_frame();
		model.blurAt(e.getX(), e.getY(), ui.getBrushSize(), ui.getBlurRange());
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

	@Override
	public void mouseDragged(MouseEvent e) {
		model.blurAt(e.getX(), e.getY(), ui.getBrushSize(), ui.getBlurRange());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Blur Brush";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}

//	@Override
//	public void stateChanged(ChangeEvent e) {
//		// TODO Auto-generated method stub
//		brush_size = 
//	}

}
