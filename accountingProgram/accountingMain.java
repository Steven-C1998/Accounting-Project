package accountingProgram;

import java.io.FileNotFoundException;

final class accountingMain {
	public static void main(String[] args)  {	
		try {
			new GUI();
			FileKeeper.masterReader();
			CheckFor.dupeCheck(FileKeeper.getMasterArray());
			System.out.println("The Current Balance is $" + CheckFor.balance(FileKeeper.getMasterArray()));
			FileKeeper.fileArray();
			FileKeeper.recordFileReader();
			CheckFor.dupeCheck(FileKeeper.getRecordArray());
			FileKeeper.masterUpdate();
			GenerateNew.printInvoice();
			System.out.println("The New Balance is $" + CheckFor.balance(FileKeeper.getMasterUpdate()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
