package accountingProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

final class FileKeeper {
	private static String[][] masterFileArray = new String[CheckFor.masterLength()][3]; //Master File Array
	private static String[][] masterArrayUpdate = new String[CheckFor.masterLength()][3]; //Updated Master File Array after masterUpdate call
	private static String[][] recordFileArray; //Records in file array

	public static void setRecordFileArray(String[][] correctedFileArray) { //sets record file array
		recordFileArray = correctedFileArray;
	}

	static void masterUpdate () { //reads through transactions and updates the master record
		int i = 0; 
		for(int h=0;h<masterFileArray.length;h++) {
			for(int j=0;j<masterFileArray[h].length;j++) {
				masterArrayUpdate[h][j] = masterFileArray[h][j];
			}
		}
		//System.out.println(Arrays.deepToString(masterArrayUpdate));
		for(int m = 0;m<recordFileArray.length;) {
			if (masterFileArray[i][0].equals(recordFileArray[m][1])) {
				String fileA = recordFileArray[m][0];
				if(fileA.equals("O")) {
					masterArrayUpdate[i][2] = String.valueOf(String.format("%.2f",
							Double.parseDouble(masterArrayUpdate[i][2])  
							+ Double.parseDouble(recordFileArray[m][4])  
							* Double.parseDouble(recordFileArray[m][5])));	
					m++;
				} else if(fileA.equals("P")) {
					masterArrayUpdate[i][2] =  String.valueOf(String.format("%.2f",
							Double.parseDouble(masterArrayUpdate[i][2]) 
							- Double.parseDouble(recordFileArray[m][3])));
					m++;
				}
			} else if(!(masterArrayUpdate[i][2].equals(recordFileArray[m][1]))) {
				i++;
			}
		}
	}

	static void recordFileReader () { //scans in transactions and reads them into 2d array
		Scanner fileIn; 
		Scanner inTwo;
		String buffer = "";
		int i = 0;
		int k = 0;
		int fileCounter = CheckFor.fileSize();
		try {
			fileIn= new Scanner(new File("FileRecords.txt"));
			inTwo = new Scanner(new File("FileRecords.txt"));
			if(fileIn.hasNext()) {
				for(;k<recordFileArray.length;) {
					for(; i < fileCounter;i++) {
						buffer = fileIn.next().toString(); 
						Boolean orderNext = buffer.equals("O");
						Boolean paymentNext = buffer.equals("P");
						if (orderNext) {
							for(int j = 0; j < 6; j++) {
								recordFileArray [k][j] = inTwo.next();	
							}
							k++;
						} else if (paymentNext){
							for(int j = 0; j < 4; j++) {
								recordFileArray [k][j] = inTwo.next();
							}
							k++;
						} 
					}
				}		
			}
			fileIn.close();
			inTwo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String[][] getRecordArray (){ //returns array of file records
		return recordFileArray;
	}

	public static String[][] getMasterArray () { //returns array of master file
		return masterFileArray;
	}

	public static String[][] getMasterUpdate () { //returns updated master array
		return masterArrayUpdate;
	}

	static void masterReader () { //reads in master file in 2d array
		Scanner fileIn;
		try {
			fileIn = new Scanner(new File("Master.txt"));
			for(int i = 0; i < masterFileArray.length; i++) {
				for(int j = 0; j < masterFileArray[i].length; j++) {
					masterFileArray [i][j] = fileIn.next();
				}
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	static void fileArray () { //initializes file records array
		recordFileArray = new String[CheckFor.fileLength()][];
		Scanner fileIn;
		String p = "P";
		String o = "O";
		String buffer = "";
		try {
			fileIn = new Scanner(new File("FileRecords.txt"));
			for(int i = 0; i < recordFileArray.length ; i++) {
				while(fileIn.hasNext()) {
					buffer = fileIn.next().toString(); 
					Boolean orderNext = o.equals(buffer);
					Boolean paymentNext = p.equals(buffer);
					if(orderNext) {
						recordFileArray[i] = new String[6] ;
						i++;
					} else if (paymentNext) {
						recordFileArray[i] = new String[4];
						i++;
					}
				}
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}