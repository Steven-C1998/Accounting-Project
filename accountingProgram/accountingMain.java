package accountingProgram;

import java.io.FileNotFoundException;

final class accountingMain {
	public static void main(String[] args)  {	
		try {
			//new GUI(); //works but layout and configurations not set
			CheckFor.masterLength();
			FileKeeper.masterReader();
			CheckFor.dupeCheck(FileKeeper.getMasterArray(),FileKeeper.getDupeMasterArray());
			if(CheckFor.getDupeFound() == true) {
				System.out.println("Please correct database for duplicates");
			}
			System.out.println("The Current Balance is $" + CheckFor.balance(FileKeeper.getMasterArray()));
			FileKeeper.fileArray();
			FileKeeper.recordFileReader();
			CheckFor.dupeCheck(FileKeeper.getRecordArray(), FileKeeper.getDupeFileRecords());
			if(CheckFor.getDupeFound() == true) {
				System.out.println("Please correct  database for duplicates");
			}
			FileKeeper.masterUpdate();
			GenerateNew.printInvoice();
			System.out.println("The New Balance is $" + CheckFor.balance(FileKeeper.getMasterUpdate()));
			//GenerateNew.correctedArray(FileKeeper.getMasterArray(), FileKeeper.getDupeMasterArray(), FileKeeper.getCorrectedMasterArray(), CheckFor.getMasterDupeLength());
			//GenerateNew.correctedArray(FileKeeper.getRecordArray(), FileKeeper.getDupeFileRecords(), FileKeeper.getCorrectedFileRecords(), CheckFor.getRecordDupeLength());
			//not sure why above method throws null for dupeFileArray when called. Looking into it.
			GenerateNew.masterFileNew();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
