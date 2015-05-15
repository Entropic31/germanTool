package germanTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class quizer {
	public static ArrayList<String> engList = new ArrayList<String>();
	public static ArrayList<String> deuList = new ArrayList<String>();
	public static ArrayList<String> lessons = new ArrayList<String>();
	public static String answer;
	public static String lText;
	public static String word;
	public static String cAnswer;
	public static int score;
	public static int x = 0;
	public static int index = 0;
	
	public static void getQuestion() {
		int oldIndex;
		int language;
		oldIndex = index;
		do {
			index = (int) ((Math.random() * (engList.size() + 0.5)) - 0.5);
		} while (index == oldIndex);
		language = (int) (Math.random() * 2);

		if (language == 0) {
			lText = "German";
			word = engList.get(index);
			cAnswer = deuList.get(index);
		} else {
			lText = "English";
			word = deuList.get(index);
			cAnswer = engList.get(index);
		}
		x++;
	}

	public static void checkQuestion() {
		String answer;

		answer = MainFrame.textField.getText();
		if (answer.equalsIgnoreCase(cAnswer)) {
			MainFrame.setText('a', "Correct");
			score++;
		} else {
			MainFrame.setText('a', "No, the answer is:");
			MainFrame.setText('c', cAnswer);
		}
	}

	public static void selectLesson() {
		String chosenOne = " ";
		
		engList.clear();
		deuList.clear();
		
		getLessons();

		String[] options = lessons
				.toArray(new String[lessons.size()+3]);
		options[options.length-3] = "| Random Words |";
		options[options.length-2] = "+ New Lesson +";
		options[options.length-1] = "- Delete Lesson -";
		
		do{
			chosenOne = MainFrame.selection(options);
		}while(chosenOne.equals(" "));
		
		if(chosenOne.equals("- Delete Lesson -")){
			MainFrame.delete();
		}
		else if(chosenOne.equals("| Random Words |")){
			randomLesson();
		}
		else if(chosenOne.equals("+ New Lesson +") == false){
			readLessons(chosenOne);
			showWord();
		}
		else{
			MainFrame.write = true;
			MainFrame.setText('q', "Press Next to write a lesson.");
		}
	}
	
	public static void readLessons(String chosenOne){
		String line = "";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(germanTool.path
					+ "lessons\\" + chosenOne));
			while ((line = br.readLine()) != null) {
				try {
					deuList.add((line.split(";")[0]));
					engList.add((line.split(";")[1]));
				} catch (NullPointerException e1) {
					System.out.println("NULL");
				}
			}
			br.close();
		} catch (Exception e1) {;
		}
	}
	
	public static void getLessons() {
		File file = null;
		String[] options;

		lessons.clear();
		try {
			file = new File(germanTool.path + "lessons");
			options = file.list();
			for (String f : options) {
				lessons.add(f);
			}
		} catch (Exception e) {
		}
	}
	
    public static void showWord(){
    	
		String words = "";
		for(int i = 0; i < engList.size(); i++){
			words = words + engList.get(i)+" = "+deuList.get(i)+"\n";
		}
		JOptionPane.showMessageDialog(null, words);
    }
    
    public static void randomLesson(){
    	getLessons();
    	int index;
    	for(String i : lessons){
    		readLessons(i);
    	}
    	ArrayList<String> eng = new ArrayList<String>();
    	ArrayList<String> deu = new ArrayList<String>();
    	
    	for(String i : engList.toArray(new String[engList.size()])){
    		eng.add(i);
    	}
    	for(String i : deuList.toArray(new String[deuList.size()])){
    		deu.add(i);
    	}
    	
    	engList.clear();
    	deuList.clear();
    	
    	for(int i = 0; i < 21; i++){
    		index = (int)(Math.random() * eng.size());
    		System.out.println(index);
    		engList.add(eng.get(index));
    		deuList.add(deu.get(index));
    		eng.remove(index);
    		deu.remove(index);
    	}
    	showWord();
    }

}
