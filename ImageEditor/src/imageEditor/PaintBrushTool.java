package imageEditor;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaintBrushTool implements Tool{

	private PaintBrushToolUI ui;
	private ImageEditorModel model;	
	private JSlider opacity_slider;
	
	public PaintBrushTool(ImageEditorModel model) {
		this.model = model;
		ui = new PaintBrushToolUI();
		opacity_slider = ui.getOpacitySlider();
	}
	
	public void set_inspect_pixel(Pixel p){
		ui.set_ini_panel(p);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int getOpacity(){
		return opacity_slider.getValue();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//model.add_frame();
		int opacity_level = opacity_slider.getValue();
		model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), ui.getBrushSize(),opacity_level);
		//model.add_frame();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//model.add_frame();
		
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
		int opacity_level = opacity_slider.getValue();
		model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), ui.getBrushSize(), opacity_level);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Paint Brush";
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
