package imageEditor;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImageEditorView extends JPanel {

	private JFrame main_frame;
	private FrameView frame_view;
	private ImageEditorModel model;
	private ToolChooserWidget chooser_widget;
	private JPanel tool_ui_panel;
	private JPanel tool_ui;
	private JButton undo;
	private JButton open_button;
	private JTextField text_field;
	private JPanel load_image_panel;
	
	public ImageEditorView(JFrame main_frame, ImageEditorModel model) {
		this.main_frame = main_frame;
		
		setLayout(new BorderLayout());
		
		frame_view = new FrameView(model.getCurrent());
		
		add(frame_view, BorderLayout.NORTH);
		
		tool_ui_panel = new JPanel();
		tool_ui_panel.setLayout(new BorderLayout());
		load_image_panel = new JPanel();
		load_image_panel.setLayout(new GridLayout(1, 2));
		
		open_button = new JButton("Load Image");
		text_field = new JTextField("Enter URL");
		chooser_widget = new ToolChooserWidget();
		
		load_image_panel.add(open_button);
		load_image_panel.add(text_field);
		
		tool_ui_panel.add(chooser_widget, BorderLayout.NORTH);
		tool_ui_panel.add(load_image_panel, BorderLayout.SOUTH);
		
		add(tool_ui_panel, BorderLayout.CENTER);
		
		tool_ui = null;
		undo = new JButton("undo");
		undo.setActionCommand("undo");
		add(undo,BorderLayout.SOUTH);
	}

	public void addToolChoiceListener(ToolChoiceListener l) {
		chooser_widget.addToolChoiceListener(l);
	}
	
	public String getCurrentToolName() {
		return chooser_widget.getCurrentToolName();
	}

	public void installToolUI(JPanel ui) {
		if (tool_ui != null) {
			tool_ui_panel.remove(tool_ui);
		}
		tool_ui = ui;
		tool_ui_panel.add(tool_ui);
		validate();
		main_frame.pack();
	}
	
	@Override
	public void addMouseMotionListener(MouseMotionListener l) {
		frame_view.addMouseMotionListener(l);
	}
	
	@Override
	public void removeMouseMotionListener(MouseMotionListener l) {
		frame_view.removeMouseMotionListener(l);
	}

	@Override
	public void addMouseListener(MouseListener l) {
		frame_view.addMouseListener(l);
	}
	
	public void removeMouseListener(MouseListener l) {
		frame_view.removeMouseListener(l);
	}
	public void addActionListener(ActionListener l){
		undo.addActionListener(l);
		
	}
	public void updateFrameView(Frame new_f){
		frame_view.setFrame(new_f);
	}
	
	public FrameView getFrameView(){
		return frame_view;
	}
	
	public JButton getLoadButton(){
		return (JButton) load_image_panel.getComponent(0);
	}
	
	public JTextField getLoadURL(){
		return (JTextField) load_image_panel.getComponent(1);
	}
}
