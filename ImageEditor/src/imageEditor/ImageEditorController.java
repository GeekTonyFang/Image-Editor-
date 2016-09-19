package imageEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class ImageEditorController implements ToolChoiceListener, MouseListener, MouseMotionListener,ActionListener {

	private ImageEditorView view;
	private ImageEditorModel model;
	private Tool current_tool;
	private PixelInspectorTool inspector_tool;
	private PaintBrushTool paint_brush_tool;
	private BlurTool blur_tool;
	private PixelInspectorUI inspect_ui;
	private PaintBrushToolUI brush_ui;
	private Pixel store_pixel;
	private JButton load_button;
	private JTextField url_field;
	private Frame new_f;
	
	public ImageEditorController(ImageEditorView view, ImageEditorModel model) {
		this.view = view;
		this.model = model;
		
		store_pixel = null;
		
		inspector_tool = new PixelInspectorTool(model);
		paint_brush_tool = new PaintBrushTool(model);
		
		inspect_ui = (PixelInspectorUI) inspector_tool.getUI();
		brush_ui = (PaintBrushToolUI) paint_brush_tool.getUI();
		
		load_button = view.getLoadButton();
		url_field = view.getLoadURL();
		
		load_button.addActionListener(this);
		
		blur_tool = new BlurTool(model);
		view.addToolChoiceListener(this);
		view.addMouseListener(this);
		view.addMouseMotionListener(this);
		view.addActionListener(this);
		this.toolChosen(view.getCurrentToolName());
	}

	@Override
	public void toolChosen(String tool_name) {
		if (tool_name.equals("Pixel Inspector")) {
			view.installToolUI(inspector_tool.getUI());
			current_tool = inspector_tool;
		} else if (tool_name.equals("Paint Brush")) {
			//pass brush color from PixelInspector tool to Paint Brush
			paint_brush_tool.set_inspect_pixel(((PixelInspectorTool) current_tool).get_inspect_Pixel());
			view.installToolUI(paint_brush_tool.getUI());
			current_tool = paint_brush_tool;
		}
		else if(tool_name.equals("Blur Brush")){
			view.installToolUI(blur_tool.getUI());
			current_tool = blur_tool;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		current_tool.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		current_tool.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		current_tool.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		current_tool.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		current_tool.mouseExited(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		current_tool.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		current_tool.mouseMoved(e);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "undo"){
			model.undo_frame();
		}
		try{
			new_f = ColorFrame.readFromURL(url_field.getText());
			model.setCurrent(new_f);
			view.updateFrameView(new_f);
	
			url_field.setText("Enter URL");
		}catch (Exception ex){
			System.out.println("Invalid URL");
		}
	}


}
