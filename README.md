# Accounting-Project
This is a persoanl project that I am working on to put into practice what I have learned so far.
It is going to be a work in progress for the forseeable future.
This is not a complete program but it will run. 
Please refer to my Accounting Program project page for my progress. 
This is a package called accountingProgram.

## Version .1
It is currently a very primitive version of an accounting software and incomplete.
Uses .txt files as a database.
The data base needs .txt files named "Master.txt" and "FileRecords.txt" and they need to be in the same project folder.

### Master.txt Style
- Customer_number	   Customer_name	     Customer_balance
- I.E.
- 0000 XXXXXXXXXXXXXXXXXXXX	0.00
- Each value is seperate by a space and the name cannot include spaces. Do not include the column headers, only values.
- All entries must be in numerical order by Customer_Number

### FileRecords.txt Style
- O for orders
- O	Customer_number	Transaction_number	Item_ordered	Quantity Cost
- I.E.
- O 0000 0000 XXXXXXXXXXXXXXXXXXXX 0000 0.00
- P for payments
- P	Customer_number	Transaction_number Payment_amount		
- I.E.
- P 0000 0000 0.00
- Each value is seperate by a space and the name cannot include spaces. Do not include the column headers, only values.
- All entries must be in numerical order by Customer_Number

Sample Files are provided.
### known bugs
- dupeCheck will print out the first non duplicate entry as a duplicate too in certain cases.
- a line if checked twice due to dupeCheck it will display the same line twice.

The program currently runs only in the IDE but making it an executable will be done once the project is completed.
