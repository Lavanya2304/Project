package empdetails.com;

import java.io.IOException;
public class ExcelFromExcell {
		public String LoginId,password,Fname,Mname,Lname,Uname,pw,Cpw;
		//public String pass;
		public void readexcel(){
			try
		    {
	
			String file="C:\\Users\\2303877\\eclipse-workspace\\empdetails.com\\testdata\\Book2.xlsx";
		    int rows=ExcelUtils.getRowCount(file,"Sheet1");
		    for(int i=1;i<=rows;i++) {
				LoginId=ExcelUtils.getCellData(file,"Sheet1",i,0);
				password=ExcelUtils.getCellData(file,"Sheet1",i,1);
				Fname=ExcelUtils.getCellData(file,"Sheet1",i,2);
				Mname=ExcelUtils.getCellData(file,"Sheet1",i,3);
				Lname=ExcelUtils.getCellData(file,"Sheet1",i,4);
				Uname=ExcelUtils.getCellData(file,"Sheet1",i,5);
				pw=ExcelUtils.getCellData(file,"Sheet1",i,6);
				Cpw=ExcelUtils.getCellData(file,"Sheet1",i,7); 
				System.out.println("loginId :"+LoginId);
				System.out.println("Password :"+password);
				System.out.println("FirstName :"+Fname);
				System.out.println("MiddleName :"+Mname);
				System.out.println("LastName :"+Lname);
				System.out.println("UserName :"+Uname);
				System.out.println("Password :"+pw);
				System.out.println("ConfirmPassword :"+Cpw);
		     }
			
		    }catch(IOException e) {
		    	e.printStackTrace();
		    }
		}
}

		