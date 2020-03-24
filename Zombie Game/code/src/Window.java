import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	public Window(int width, int hight,String title,Game game) {
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width,hight));
		frame.setMaximumSize(new Dimension(width,hight));
		frame.setMinimumSize(new Dimension(width,hight));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//if the size can be change
		frame.setResizable(false);
		//window at middle
		
		
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		
	}
}
