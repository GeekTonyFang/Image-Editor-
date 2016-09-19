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

public class BlurToolUI extends JPanel implements ChangeListener {

	// a slider control the range of blur
	private JSlider blur_range_slider;
	
	//a slider control the size of brush
	private JSlider size_slider;
	
	int blur_range;
	int brush_size;
	public BlurToolUI() {
		
		blur_range = 0;
		setLayout(new GridLayout(0,1));
		
		JPanel brush_pannel = new JPanel();
		brush_pannel.setLayout(new BorderLayout());
		
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0,1));
		
		JPanel blur_slider_panel = new JPanel();
		JLabel blur_label = new JLabel("Blur Size:");
		blur_slider_panel.setLayout(new BorderLayout());
		blur_slider_panel.add(blur_label, BorderLayout.WEST);
		blur_range_slider = new JSlider(0,5,0);
		blur_range_slider.setMajorTickSpacing(1);
		blur_range_slider.setPaintLabels(true);
		blur_range_slider.setSnapToTicks(true);
		blur_range_slider.addChangeListener(this);
		blur_range_slider.setName("blur_slider");
		blur_slider_panel.add(blur_range_slider, BorderLayout.CENTER);

		
		JPanel size_slider_panel = new JPanel();
		JLabel size_label = new JLabel("Brush Size: ");
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


		// Assumes green label is widest and asks red and blue label
		// to be the same.
		Dimension d = size_label.getPreferredSize();
		blur_label.setPreferredSize(d);
		
		slider_panel.add(blur_slider_panel);
		slider_panel.add(size_slider_panel);

		brush_pannel.add(slider_panel, BorderLayout.CENTER);
		
		add(brush_pannel);
		
		//stateChanged(null);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		blur_range = blur_range_slider.getValue();
		brush_size = size_slider.getValue();
	
	}
	
	
	public int getBlurRange() {
		return blur_range;
	}
	public int getBrushSize(){
		return brush_size;
	}
	
}
