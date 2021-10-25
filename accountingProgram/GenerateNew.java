package accountingProgram;

import java.io.FileNotFoundException;
import java.io.PrintStream;

final class GenerateNew {

	static void printInvoice () throws FileNotFoundException { //prints invoice for each customer
		int i = 0;
		int j =0;
		String[][] masterFileArray = FileKeeper.getMasterArray();
		String[][] recordFileArray = FileKeeper.getRecordArray();
		String[][] masterArrayUpdate = FileKeeper.getMasterUpdate();
		PrintStream originalOut = System.out;
		PrintStream fileOut = new PrintStream(masterFileArray[i][0]+ " " 
				+ masterFileArray[i][1] +".txt");
		while(j<recordFileArray.length) {
			fileOut = new PrintStream(masterFileArray[i][0]+ " " 
					+ masterFileArray[i][1] +".txt");
			System.setOut(fileOut);	
			System.out.println(masterFileArray[i][1]+ " "+ masterFileArray[i][0]);
			System.out.println();
			System.out.printf("%-22s%-22s%-22s\n" , " " ,"Previous Balance ", 
					masterFileArray[i][2] );
			System.out.println();
			for (;j<recordFileArray.length;) {
				if (masterFileArray[i][0].equals(recordFileArray[j][1])) {
					String fileA = recordFileArray[j][0];
					if(fileA.equals("O")) {				
						String filler = (String.format("%.2f", 
								Integer.parseInt(recordFileArray[j][4])  
								* Double.parseDouble(recordFileArray[j][5])));
						if (masterFileArray[i][0].equals(recordFileArray[j][1])) {
							System.out.printf("%-22s%-22s%-22s\n", 
									recordFileArray[j][2], recordFileArray[j][3], filler );
							j++;		
						}
					} else if(fileA.equals("P")) {
						if(masterFileArray[i][0].equals(recordFileArray[j][1])) {
							System.out.printf("%-22s%-22s%-22s\n",
									recordFileArray[j][2], "Payment", recordFileArray[j][3]);
							j++;
						}
					}
				} else {
					System.out.println();
					System.out.printf("%-22s%-22s%-22s\n", "", "Balance Due", 
							masterArrayUpdate[i][2]);
					i++;
					break;
				}
			}
		}
		System.setOut(originalOut);
	}
	
	static void masterFileNew(String[][] masterArray) throws FileNotFoundException {
		PrintStream originalOut = System.out;
		PrintStream fileOut = new PrintStream("NewMaster.txt");
		int count = 0;
		System.setOut(fileOut);	
		for (int i = 0; i < masterArray.length; i++) {
			for (int j = 0; j < masterArray[i].length; j++) {
				count++;
				System.out.print(masterArray[i][j] + " ");
				if(count % 3 == 0) {
					System.out.println("");
				}
			}
		}
		System.setOut(originalOut);
	}

}
