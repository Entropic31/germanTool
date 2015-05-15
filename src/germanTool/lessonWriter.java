package germanTool;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class lessonWriter extends JFrame implements ActionListener {
	private JButton doneButton;
	private JButton helpButton;
	private JTextArea content;
	private JTextField nameField;
	
	public lessonWriter(String title) {
		super(title);
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				germanTool.path + "flag.png"));

		this.setSize(700, 570);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		doneButton = new JButton("Done");
		doneButton.addActionListener(this);
		
		helpButton = new JButton("Help");
		helpButton.addActionListener(this);
		
		content = new JTextArea(30, 50);
		
		nameField = new JTextField(44);
		
		GridBagConstraints C = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		C.gridx = 0;
		C.gridy = 0;
		C.anchor = GridBagConstraints.WEST;
		this.add(nameField, C);
		
		C.gridx = 1;
		C.gridy = 0;
		C.anchor = GridBagConstraints.WEST;
		this.add(doneButton, C);
		
		C.gridx = 2;
		C.gridy = 0;
		C.anchor = GridBagConstraints.WEST;
		this.add(helpButton, C);
		
		C.gridx = 0;
		C.gridy = 1;
		C.gridwidth = 3;
		C.anchor = GridBagConstraints.WEST;
		this.add(content, C);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == doneButton){
			done();
		}
		else if(e.getSource() == helpButton){
			help();
		}
			
	}
	
	public void done(){
		File lesson = new File(germanTool.path+"\\lessons\\"+nameField.getText()+".txt");
		try {
			lesson.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String[] lines = content.getText().split("\\n");
		try{
			FileWriter fw;
			fw = new FileWriter(lesson.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(String i : lines){
				bw.write(i+"\n");
			}
			bw.close();
		}catch(IOException e1){}
		
		this.dispose();
		MainFrame.setWrite(false);
	}

	public void help(){
		JOptionPane.showMessageDialog(null, 
				 "- Enter the file name in the text field to the left.\n"
				+"- The file's content will go in the text area below.\n"
				+"- Write german words on the left and english ones on the right\n"
				+"  seperating them with a semicolon (';').\n"
				+"- When you are finished press 'Done'.");
	}
	

}
