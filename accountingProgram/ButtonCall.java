package accountingProgram;

import java.io.*;
import javax.swing.*;

class ButtonCall extends GUI{
static String masterLocation ="";
	static void openFiles() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File f = chooser.getSelectedFile();
		String fileName = f.getAbsolutePath();
		try {
			FileReader reader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(reader);
			ta.read( br,null);
			br.close();
			ta.requestFocus();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}
	static void selectMaster() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File f = chooser.getSelectedFile();
		masterLocation = f.getAbsolutePath();
	}
}

