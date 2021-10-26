package accountingProgram;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;

final class GenerateNew extends FileKeeper{

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

	static void masterFileNew() throws FileNotFoundException { //creates new master file
		PrintStream originalOut = System.out;
		PrintStream fileOut = new PrintStream("NewMaster.txt");
		int count = 0;
		System.setOut(fileOut);	
		for (int i = 0; i < masterFileArray.length; i++) {
			for (int j = 0; j < masterFileArray[i].length; j++) {
				count++;
				System.out.print(masterFileArray[i][j] + " ");
				if(count % 3 == 0) {
					System.out.println("");
				}
			}
		}
		System.setOut(originalOut);
	}
	
	 //corrects arrays for duplicate entries. Trying to fix errors relating to duplicates found one after another
	static void correctedArray(String[][] masterArray, String[][] dupeArray, String[][] correctedArray, int dupeLength) {
		String compareTo ="";
		String compare ="";
		String fill = "";
		String filler = "";
		String dupeFiller = "";
		String dupeCompare = "";
		String dupeCompareTo = "";
		String dupeFiller2 = "";
		int j = 0;
		int counter = 0;
		int dupeCounter = 0;
		int nullCheck = 0;
		int i = 0;
		int dupeArrayLength =  dupeLength;
		int m = 0;
		System.out.println("dupe length " + dupeArrayLength);
		System.out.println(Arrays.deepToString(dupeArray));
		for (;i<masterArray.length;) {
			fill="";
			for(int p = 0;p<masterArray[i].length;p++) {
				compare = masterArray[i][p];
				fill += compare;
			}
			for (;j<masterArray.length;) {
				for(int o = 0;o<dupeArray[j].length;o++) {
					if (dupeArray[j][o] != null) {
						compareTo = dupeArray[j][o];
						filler += compareTo;
					}
				}
				nullCheck++;
				System.out.println("nullCheck " + nullCheck);
				System.out.println("filler " +filler);
				System.out.println("fill " +fill);
				if(fill.equals(filler) && dupeCounter < masterArray.length -1 ) {
					for (;m < correctedArray.length;) {
						dupeFiller = "";
						for(int a = 0; a < correctedArray[m].length;a++) {
							dupeCompare = correctedArray[m][a];
							dupeFiller += dupeCompare; 
						}
						for (;j<correctedArray.length;) {
							if (dupeFiller.equals(fill)) {
								continue;
							}
							else if (!(dupeFiller.equals(fill))  ){
								if (masterArray[i].length == 3) {
									String one = masterArray[i][0];
									String two = masterArray[i][1];
									String three = masterArray[i][2];
									System.out.println("Dupe counter " + dupeCounter);
									System.out.println("master " + masterArray.length);
									System.out.println("i " + i);
									System.out.println("fill 2 " +fill);
									System.out.println("dupefill 2 " + dupeFiller);
									correctedArray[dupeCounter] = new String[]{one ,two, three};
									dupeCounter++;
									filler="";
									dupeFiller="";
									fill="";	
									if (i+dupeArrayLength>=masterArray.length) {
										System.out.println(Arrays.deepToString(FileKeeper.getCorrectedMasterArray()));
										return;
									}
								} else if (masterArray[i].length == 4) {
									String one = masterArray[i][0];
									String two = masterArray[i][1];
									String three = masterArray[i][2];
									String four = masterArray[i][3];
									correctedArray[dupeCounter] = new String[]{one ,two, three, four};
									dupeCounter++;
								} else if (masterArray[i].length == 6) {
									String one = masterArray[i][0];
									String two = masterArray[i][1];
									String three = masterArray[i][2];
									String four = masterArray[i][3];
									String five = masterArray[i][4];
									String six = masterArray[i][5];
									correctedArray[dupeCounter] = new String[]{one ,two, three, four, five, six};
									dupeCounter++;
								}
								counter ++;
								break;
							}
						}
						if (counter == 1) {
							break;
						}
					}
					i++;
					j++;
					dupeFiller="";
					dupeFiller2="";	
					filler="";
					continue;
				}else if (!(fill.equals(filler)) && nullCheck-1 < dupeArrayLength) {
					m++;
					filler="";
				} else if(!(fill.equals(filler)) && nullCheck > dupeArrayLength) {
					if (masterArray[i].length == 3) {
						String one = masterArray[i][0];
						String two = masterArray[i][1];
						String three = masterArray[i][2];
						correctedArray[dupeCounter] = new String[]{one ,two, three};
						System.out.println("here");
						System.out.println(i);
						System.out.println(filler);
						System.out.println(fill);
						System.out.println(Arrays.deepToString(FileKeeper.getCorrectedMasterArray()));
						System.out.println(dupeFiller2);
						System.out.println(dupeFiller);
						dupeCounter++;
						filler="";
						dupeFiller="";
						dupeFiller2="";	
						i++;
						m=0;
						nullCheck = 0;
						break;
					} else if (masterArray[i].length == 4) {
						String one = masterArray[i][0];
						String two = masterArray[i][1];
						String three = masterArray[i][2];
						String four = masterArray[i][3];
						correctedArray[dupeCounter] = new String[]{one ,two, three, four};
						dupeCounter++;
						filler="";
						dupeFiller="";
						dupeFiller2="";	
						i++;
						j=0;
						nullCheck = 0;
						break;
					} else if (masterArray[i].length == 6) {
						String one = masterArray[i][0];
						String two = masterArray[i][1];
						String three = masterArray[i][2];
						String four = masterArray[i][3];
						String five = masterArray[i][4];
						String six = masterArray[i][5];
						correctedArray[dupeCounter] = new String[]{one ,two, three, four, five, six};
						dupeCounter++;
						filler="";
						dupeFiller="";
						dupeFiller2="";	
						i++;
						j=0;
						nullCheck = 0;
						break;
					}
				} else if(dupeCounter > masterArray.length -1) {
					break;
				}
			}
		}
	}
	
}


