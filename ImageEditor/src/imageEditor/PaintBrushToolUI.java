package imageEditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaintBrushToolUI extends JPanel implements ChangeListener {

	private JSlider red_slider;
	private JSlider green_slider;
	private JSlider blue_slider;
	private JSlider size_slider;
	private FrameView color_preview;
	private Pixel brush_color;
	private JSlider opacity_slider;
	
	//after pass ini_color from PixelInspector, whether change brush color again
	//if no, set the brush color to initial color
	
	//the initial color of brush. The color may be passed through PixelInspector
	private Pixel ini_color;
	
	int brush_size;
	public PaintBrushToolUI() {
		
		ini_color = new ColorPixel(0.5,0.5,0.5);
		brush_color = ini_color;
		setLayout(new GridLayout(0,1));
		
		JPanel color_chooser_panel = new JPanel();
		color_chooser_panel.setLayout(new BorderLayout());
		
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0,1));
		
		JPanel red_slider_panel = new JPanel();
		JLabel red_label = new JLabel("Red:");
		red_slider_panel.setLayout(new BorderLayout());
		red_slider_panel.add(red_label, BorderLayout.WEST);
		red_slider = new JSlider(0,100);
		red_slider.addChangeListener(this);
		red_slider.setName("red_slider");
		red_slider_panel.add(red_slider, BorderLayout.CENTER);

		JPanel green_slider_panel = new JPanel();
		JLabel green_label = new JLabel("Green:");
		green_slider_panel.setLayout(new BorderLayout());
		green_slider_panel.add(green_label, BorderLayout.WEST);
		green_slider = new JSlider(0,100);
		green_slider.addChangeListener(this);
		green_slider.setName("green_slider");
		green_slider_panel.add(green_slider, BorderLayout.CENTER);

		JPanel blue_slider_panel = new JPanel();
		JLabel blue_label = new JLabel("Blue: ");
		blue_slider_panel.setLayout(new BorderLayout());
		blue_slider_panel.add(blue_label, BorderLayout.WEST);
		blue_slider = new JSlider(0,100);
		blue_slider.addChangeListener(this);
		blue_slider.setName("blue_slider");
		blue_slider_panel.add(blue_slider, BorderLayout.CENTER);
		
		JPanel size_slider_panel = new JPanel();
		JLabel size_label = new JLabel("Size: ");
		size_slider_panel.setLayout(new BorderLayout());
		size_slider_panel.add(size_label, BorderLayout.WEST);
		size_slider = new JSlider(1,25,1);
		
		Hashtable labelTable = new Hashtable();
		labelTable.put( new Integer( 1 ), new JLabel("1") );
		labelTable.put( new Integer( 5 ), new JLabel("5") );
		labelTable.put( new Integer( 10 ), new JLabel("10") );
		labelTable.put( new Integer( 15 ), new JLabel("15") );
		labelTable.put( new Integer( 20 ), new JLabel("20") );
		labelTable.put( new Integer( 25 ), new JLabel("25") );
		size_slider.setLabelTable(labelTable);
		size_slider.setPaintLabels(true);
		size_slider.setName("size_slider");
		size_slider.addChangeListener(this);
		size_slider_panel.add(size_slider, BorderLayout.CENTER);

		//opacity slider
		JPanel opacity_slider_panel = new JPanel();
		JLabel opacity_label = new JLabel("Opacity: ");		
		opacity_slider_panel.setLayout(new BorderLayout());
		opacity_slider_panel.add(opacity_label, BorderLayout.WEST);
		opacity_slider = new JSlider(0, 100);
		opacity_slider.setMajorTickSpacing(25);
		opacity_slider.setPaintTicks(true);
		opacity_slider.setPaintLabels(true);
		opacity_slider_panel.add(opacity_slider, BorderLayout.CENTER);
		//opacity slider ends
		
		// Assumes green label is widest and asks red and blue label
		// to be the same.
		Dimension d = green_label.getPreferredSize();
		red_label.setPreferredSize(d);
		blue_label.setPreferredSize(d);
		size_label.setPreferredSize(d);
		
		slider_panel.add(red_slider_panel);
		slider_panel.add(green_slider_panel);
		slider_panel.add(blue_slider_panel);
		slider_panel.add(size_slider_panel);
		slider_panel.add(opacity_slider_panel);

		color_chooser_panel.add(slider_panel, BorderLayout.CENTER);

		color_preview = new FrameView(new ColorFrame(50,50));
		
		color_chooser_panel.add(color_preview, BorderLayout.EAST);
		
		add(color_chooser_panel);
		
		//stateChanged(null);
	}
	
	public JSlider getOpacitySlider(){
		return opacity_slider;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		if(!((JSlider)e.getSource()).getName().equals("size_slider")){
			Pixel p = new ColorPixel(red_slider.getValue()/100.0,
	                 green_slider.getValue()/100.0,
	                 blue_slider.getValue()/100.0);
			
			brush_color = (ColorPixel) p;
			Frame preview_frame = color_preview.getFrame();
			preview_frame.suspendNotifications();
			for (int x=0; x<preview_frame.getWidth(); x++) {
				for (int y=0; y<preview_frame.getHeight(); y++) {
					preview_frame.setPixel(x, y, brush_color);
				}
			}
			preview_frame.resumeNotifications();
		}
		else{
			brush_size = size_slider.getValue();
		}
		 
        
			
		
	}
	
	
	public Pixel getBrushColor() {
		return brush_color;
	}
	public int getBrushSize(){
		return brush_size;
	}
	
	//set init brush color, the color gets from PaintBrushTool
	public void set_ini_panel(Pixel p){
		Frame inspect_frame = color_preview.getFrame();
		inspect_frame.suspendNotifications();
		for (int x=0; x<inspect_frame.getWidth(); x++) {
			for (int y=0; y<inspect_frame.getHeight(); y++) {
				inspect_frame.setPixel(x, y, p);
			}
		}
		red_slider.setValue((int) (p.getRed()*100));
		green_slider.setValue((int) (p.getGreen()*100));
		blue_slider.setValue((int) (p.getBlue()*100));
		inspect_frame.resumeNotifications();
		brush_color = p;
	}
}
