package germanTool;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {
	private JFrame frame;
	
	private static JButton nextButton;
	private static JButton enterButton;
	public static JLabel qLabel;
	public static JLabel wLabel;
	public static JLabel aLabel;
	public static JLabel cLabel;
	public static JTextField textField;

	public static int status;
	public static boolean write = false;
	public static boolean next = true;

	public MainFrame(String title) {
		super(title);
		// create components
		textField = new JTextField(30);
		textField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
		        
		      }

		    public void keyPressed(KeyEvent e) {
		    	if(e.getKeyCode() == KeyEvent.VK_ENTER){
		        	if(next == true){
		        		if(write == false){
		    				next();
		    			}
		    			else{
		    				lessonWriter lW = new lessonWriter("Writer");
		    			}
		        	}
		        	else{
		        		enter();
		        	}
		        }
		    }

		    public void keyReleased(KeyEvent e) {
		        
		    }
		});


		nextButton = new JButton("Next");
		nextButton.addActionListener(this);

		enterButton = new JButton("Enter");
		enterButton.setEnabled(false);
		enterButton.addActionListener(this);

		qLabel = new JLabel("Press Next to select a lesson, add a lesson or delete a lesson.");
		wLabel = new JLabel(" ");
		aLabel = new JLabel(" ");
		cLabel = new JLabel(" ");
		
		GridBagConstraints C = new GridBagConstraints();

		// add components
		this.setLayout(new GridBagLayout());

		C.gridx = 0;
		C.gridy = 2;
		C.anchor = GridBagConstraints.WEST;
		this.add(textField, C);

		C.gridx = 1;
		C.gridy = 4;
		C.anchor = GridBagConstraints.WEST;
		this.add(nextButton, C);

		C.gridx = 1;
		C.gridy = 2;
		C.anchor = GridBagConstraints.WEST;
		this.add(enterButton, C);

		C.gridx = 0;
		C.gridy = 0;
		C.gridwidth = 2;
		C.anchor = GridBagConstraints.WEST;
		this.add(qLabel, C);

		C.gridx = 0;
		C.gridy = 1;
		C.gridwidth = 1;
		C.anchor = GridBagConstraints.WEST;
		this.add(wLabel, C);

		C.gridx = 0;
		C.gridy = 3;
		C.anchor = GridBagConstraints.WEST;
		this.add(aLabel, C);
		
		C.gridx = 0;
		C.gridy = 4;
		C.anchor = GridBagConstraints.WEST;
		this.add(cLabel, C);

	}

	public static void setText(char label, String text) {
		if (label == 'q') {
			qLabel.setText(text);
		} else if (label == 'w') {
			wLabel.setText(text);
		} else if (label == 'a') {
			aLabel.setText(text);
		} else if (label == 'c'){
			cLabel.setText(text);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enterButton) {
			enter();
		} else if (e.getSource() == nextButton) {
			if(write == false){
				next();
			}
			else{
				lessonWriter lW = new lessonWriter("Writer");
			}
		}
			
	}
	
	
	
	public static boolean delete(){
		String target;
		target = selection(quizer.lessons
				.toArray(new String[quizer.lessons.size()]));
		File f = new File(germanTool.path + "lessons\\" + target);
		return f.delete();
	}
	
	public void next() {
		status = 0;
		if (quizer.x == 0 || quizer.engList.size() == 0) {
			next = true;
			quizer.selectLesson();
			quizer.x++;
		} else if (quizer.x < quizer.engList.size() * 2) {
			next = false;
			textField.setText("");
			quizer.getQuestion();

			setText('q', "Translate this into " + quizer.lText + ":");
			setText('w', quizer.word);
			setText('a', " ");
			setText('c', " ");

			enterButton.setEnabled(true);
			nextButton.setEnabled(false);
		} else {
			next = true;
			JOptionPane.showMessageDialog(
							null,
							"Your score is "
									+ quizer.score
									+ "\n"
									/*+ "That's "
									+ (int) ((((float) score / ((float) engList
											.size()) * 2) - 1) * (float) 100) + "%"*/,
							"Score", JOptionPane.PLAIN_MESSAGE);
			quizer.x = 0;
			setText('q', "Press Next to select a lesson, add a lesson or delete a lesson.");
		}
	}

	public static void enter() {
		next = true;
		status = 1;
		quizer.checkQuestion();
		enterButton.setEnabled(false);
		nextButton.setEnabled(true);
	}

	
    
    public static String selection(String[] array){
    	String choosen;
    	choosen = (String) JOptionPane.showInputDialog(null,
				"Select a lesson:\n",
				"Select Lesson",
				JOptionPane.INFORMATION_MESSAGE,
				null,
				array,
				array[0]);
		return choosen;
    	
    }
    
    public static void setWrite(boolean in){
    	write = in;
    }

}
