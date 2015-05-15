package germanTool;

import javax.swing.*;
import java.awt.Toolkit;

public class germanTool {
	static String path = "bin\\germanTool\\";

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new MainFrame("German Tool");
				frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
						path + "flag.png"));

				frame.setSize(425, 140);
				frame.setResizable(false);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
	}
}
