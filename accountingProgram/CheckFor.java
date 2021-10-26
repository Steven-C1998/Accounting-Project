package accountingProgram;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

final class CheckFor extends FileKeeper{
	private static boolean dupicateFound = false;
	private static int masterDupeLength = 0;
	private static int recordDupeLength = 0;
	
	static boolean getDupeFound() { //returns if a duplicate was found
		return dupicateFound;
	}

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

	static double balance (String[][] masterArray) { //returns the balance
		double sum = 0;
		for (int i = 0; i < masterArray.length; i++) {
			sum += Double.parseDouble(masterArray[i][2]);
		}
		return sum;
	}

	static void dupeCheck(String[][] masterArray, String[][] dupeArray) { //Checks for duplicates in the master file
		String compareTo ="";
		String compare ="";
		String fill = "";
		String filler = "";
		String fillers = ""; 
		int j = 1;
		int counter = 0;
		int dupeCounter = 0;
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
					if (masterArray[j].length == 3) {
						String one = masterArray[j][0];
						String two = masterArray[j][1];
						String three = masterArray[j][2];
						dupeArray[dupeCounter] = new String[]{one ,two, three};
						dupeCounter++;
						masterDupeLength++;
					} else if (masterArray[j].length == 4) {
						String one = masterArray[j][0];
						String two = masterArray[j][1];
						String three = masterArray[j][2];
						String four = masterArray[j][3];
						dupeArray[dupeCounter] = new String[]{one ,two, three, four};
						dupeCounter++;
						recordDupeLength++;
					} else if (masterArray[j].length == 6) {
						String one = masterArray[j][0];
						String two = masterArray[j][1];
						String three = masterArray[j][2];
						String four = masterArray[j][3];
						String five = masterArray[j][4];
						String six = masterArray[j][5];
						dupeArray[dupeCounter] = new String[]{one ,two, three, four, five, six};
						dupeCounter++;
						recordDupeLength++;
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
				}
			}
		}
	}
	
	static int getMasterDupeLength() { //returns array length of the duplicate master array
		return masterDupeLength;
	}
	
	static int getRecordDupeLength() { // returns array length of the duplicate records array
		return recordDupeLength;
	}

}
