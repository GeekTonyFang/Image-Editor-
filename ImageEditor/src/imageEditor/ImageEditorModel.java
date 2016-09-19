package imageEditor;

import java.util.Stack;

public class ImageEditorModel {

	private Frame original;
	private Frame current;
	
	//a stack store all changed frames
	private Stack<Frame> undo_frames;
	
	public ImageEditorModel(Frame f) {
		original = f;
		current = original.copy();
		undo_frames = new Stack<Frame>();
		undo_frames.push(f.copy());
	}
	public void setCurrent(Frame new_f){
		current = new_f;
	}

	public Frame getCurrent() {
		return current;
	}

	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	public void paintAt(int x, int y, Pixel brushColor, int brush_size, int opacity) {
		
		//push current fraem into stack
		undo_frames.push(current.copy());
		
		current.suspendNotifications();
		
		for (int xpos=x-brush_size+1; xpos <=x+brush_size-1; xpos++) {
			for (int ypos=y-brush_size+1; ypos <=y+brush_size-1; ypos++) {
				if (xpos >= 0 &&
					xpos < current.getWidth() &&
					ypos >= 0 &&
					ypos < current.getHeight()) {
Pixel curr_pixel = getPixel(xpos, ypos);
					
					double opacity_percent = (double) opacity/100;
					double new_red = (opacity_percent * brushColor.getRed()) + ((1-opacity_percent)*curr_pixel.getRed());
					double new_green = (opacity_percent * brushColor.getGreen()) + ((1-opacity_percent)*curr_pixel.getGreen());
					double new_blue =(opacity_percent * brushColor.getBlue()) + ((1-opacity_percent)*curr_pixel.getBlue());
					
					Pixel new_brush_color = new ColorPixel(new_red, new_green, new_blue);

					current.setPixel(xpos, ypos, new_brush_color);
				}
			}
		}
		current.resumeNotifications();
		
		
	}
	public void add_frame(){
		Frame new_frame = current.copy();
		undo_frames.push(new_frame);
	}
	
	//when click undo, the method pop a frame from stack
	public void undo_frame(){
		if(!undo_frames.empty()){
			Frame tem = undo_frames.pop();
			current.suspendNotifications();
			for(int i = 0; i < current.getWidth(); i++){
				for(int j = 0; j < current.getHeight(); j++){
					current.setPixel(i, j, tem.getPixel(i, j));
				}
			}
			current.resumeNotifications();
		}
		
		
	}
	public void blurAt(int x, int y, int brush_size, int blur_size){
		
		undo_frames.push(current.copy());
		current.suspendNotifications();
		
		for (int xpos=x-brush_size+1; xpos <=x+brush_size-1; xpos++) {
			for (int ypos=y-brush_size+1; ypos <=y+brush_size-1; ypos++) {
				if (xpos >= 0 &&
					xpos < current.getWidth() &&
					ypos >= 0 &&
					ypos < current.getHeight()) {
					Frame result = getAround(original, xpos, ypos, blur_size);
					current.setPixel(xpos, ypos, result.getAverage());
				}
			}
		}
		current.resumeNotifications();
		
	}
	
	private Frame getAround(Frame blur_f, int x_pos, int y_pos, int size){
		
		//the length of sub IndirectFrame square area
		int range = 2*size + 1;	
		
		//the width of sub IndirectFrame
		int width = range;
		
		//the height of sub IndirectFrame area
		int height = range;
		
		//the start position of sub IndirectFrame
		int start_x = x_pos - size;
		
		//the start position of sub IndirectFrame
		int start_y = y_pos - size;
		
		
		if(x_pos < size){
			start_x = 0;
			width -= (size - x_pos);
		}
		if((blur_f.getWidth() - x_pos) <= size){
			width -= (size - (blur_f.getWidth() - x_pos -1));
		}
		if(y_pos < size){
			start_y = 0;
			height -= (size - y_pos);
		}
		if((blur_f.getHeight() - y_pos) <= size){
			height -= (size - (blur_f.getHeight() - y_pos -1));
		}
		
		IndirectFrame sub_frame = blur_f.crop(start_x, start_y, width, height);
		
		//blur_f.setPixel(x_pos, y_pos, sub_frame.getAverage());
		return sub_frame;
	}

}
