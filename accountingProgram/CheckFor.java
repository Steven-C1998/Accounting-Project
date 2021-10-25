package accountingProgram;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

final class CheckFor {
	protected static boolean dupicateFound = false;

	static int masterLength() { // check for the row length for masterCount
		Scanner fileIn;
		int counter = 0;
		try {
			fileIn = new Scanner(new File("Master.txt"));
			while(fileIn.hasNext()) {
				String bufferVoid = fileIn.next().toString(); //unused variable to loop through the file
				counter++;
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return counter/3;
	}

	static int fileLength ()  { // checks row length for File Records
		Scanner fileIn;
		int counterO = 0;
		int counterP = 0;
		try {
			fileIn = new Scanner(new File("FileRecords.txt"));
			String p = "P";
			String o = "O";
			String buffer = "";
			while(fileIn.hasNext()) {
				buffer = fileIn.next().toString(); 
				Boolean orderNext = o.equals(buffer);
				Boolean paymentNext = p.equals(buffer);
				if(orderNext) {
					counterO++;
				} else if(paymentNext) {
					counterP++;
				}
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int fileCount = counterO + counterP;
		return fileCount;
	}

	static int fileSize() { // check for the total length of File records
		Scanner fileIn = null;
		int counter = 0;
		try {
			fileIn = new Scanner(new File("FileRecords.txt"));
			while(fileIn.hasNext()) {
				String bufferVoid = fileIn.next().toString(); //unused variable to loop through the file
				counter++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			fileIn.close();
		}
		return counter;
	}

	static double balance (String[][] masterArray) {
		double sum = 0;
		for (int i = 0; i < masterArray.length; i++) {
			sum += Double.parseDouble(masterArray[i][2]);
		}
		return sum;
	}

	static void dupeCheck(String[][] masterArray) { //Checks for duplicates in the master file
		String compareTo ="";
		String compare ="";
		String fill = "";
		String filler = "";
		String fillers = ""; 
		int j = 1;
		int counter = 0;
		for (int i = 0;i<masterArray.length;i++) {
			fill="";
			for(int p = 0;p<masterArray[i].length;p++) {
				compare = masterArray[i][p];
				fill += compare;
			}
			if(j>1) {
				j = masterArray.length-counter+1;
			}
			counter=0;
			for (;j<masterArray.length;) {
				for(int o = 0;o<masterArray[j].length;o++) {
					compareTo = masterArray[j][o];
					filler += compareTo;				
				}	
				if(fill.equals(filler)) {
					for(int f = 0;f<masterArray[j].length;f++) {
						fillers += masterArray[j][f] + " ";		
					}
					dupicateFound = true;
					System.out.println("Duplicate entry detected on line " 
							+ j + " Entry: " + fillers);
					j++;
					counter++;
					filler="";
					fillers="";
				} else if(!(fill.equals(filler))) {
					j++;
					counter++;
					filler="";
					//continue;
				}

			}
		}
	}
}

