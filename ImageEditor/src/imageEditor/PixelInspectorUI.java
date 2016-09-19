package imageEditor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorUI extends JPanel implements ActionListener {

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;

	private Pixel p;
	
	//after type set_pixel button, the inspect_color will passed into brush tool
	private Pixel inspect_color;
	public PixelInspectorUI() {
		//p = inspect_color;
		this.inspect_color = new ColorPixel(0.5,0.5,0.5);
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_info = new JLabel("(r,g,b)");
		JButton set_color= new JButton("Set PaintBrush Color");
		set_color.setActionCommand("set_color");
		set_color.addActionListener(this);

		setLayout(new GridLayout(0,1));
		add(x_label);
		add(y_label);
		add(pixel_info);
		add(set_color);
	}
	
	public void setInfo(int x, int y, Pixel p) {
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));		
	}

	public void set_color(Pixel pixel){
		p = pixel;
	}
	public Pixel get_changed_pixel(){
		return inspect_color;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String action_command = e.getActionCommand();
		if(action_command == "set_color"){
			inspect_color = p;
		}
		
	}
	
}
