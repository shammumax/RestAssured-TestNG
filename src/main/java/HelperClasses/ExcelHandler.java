package HelperClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

//import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHandler {
	
	public static String username,token,baseurl,projectname,projectkey,projectmodifiedname,baseurlticket,tickettypeid,ticketname,ticketmodifiedname,ticketkey;
	public static HashMap<String, String> mymapcredential,mymapprojects,mymaptickets;
	
	public static void handleExcel() throws IOException {
	FileInputStream fio = new FileInputStream("C:\\Users\\HP\\eclipse-workspace1\\RestAssured\\src\\test\\resources\\Testdata.xlsx");
	XSSFWorkbook xw=new XSSFWorkbook(fio);
	
	//credential sheet reading and assigning values....
	XSSFSheet xs = xw.getSheet("credentials");
	//xs.cell.setcell
	mymapcredential=new HashMap<String, String>();

	for(int r=0;r<=xs.getLastRowNum();r++) {
		//mymapcredential.put(xs.getRow(r).getCell(0).getStringCellValue(), xs.getRow(r).getCell(1).getStringCellValue());
			if(xs.getRow(r).getCell(1).getCellType()==CellType.STRING) {
				mymapcredential.put(xs.getRow(r).getCell(0).getStringCellValue(), xs.getRow(r).getCell(1).getStringCellValue());
				}
			else {
				double d=xs.getRow(r).getCell(1).getNumericCellValue();
				String s=String.valueOf(Math.ceil(d));
				mymapcredential.put(xs.getRow(r).getCell(0).getStringCellValue(), s);
			}
			
	}
	username=mymapcredential.get("username");
	token=mymapcredential.get("token");
	baseurl=mymapcredential.get("baseurl");
	
	
	//project sheet reading and assigning values....
	xs=xw.getSheet("projects");
	mymapprojects=new HashMap<String, String>();
	for(int r=0;r<=xs.getLastRowNum();r++) {
		if(xs.getRow(r).getCell(1).getCellType()==CellType.STRING) {
			mymapprojects.put(xs.getRow(r).getCell(0).getStringCellValue(), xs.getRow(r).getCell(1).getStringCellValue());
			}
		else {
			double d=xs.getRow(r).getCell(1).getNumericCellValue();
			String s=String.valueOf(Math.ceil(d));
			mymapprojects.put(xs.getRow(r).getCell(0).getStringCellValue(), s);
		}

	}
	projectkey=mymapprojects.get("projectkey");
	projectmodifiedname=mymapprojects.get("projectmodifiedname");
	projectname=mymapprojects.get("projectname");
	
	
	//ticket sheet reading and assigning values....
		xs=xw.getSheet("tickets");
		mymaptickets=new HashMap<String, String>();
		for(int r=0;r<=xs.getLastRowNum();r++) {
			if(xs.getRow(r).getCell(1).getCellType()==CellType.STRING) {
				mymaptickets.put(xs.getRow(r).getCell(0).getStringCellValue(), xs.getRow(r).getCell(1).getStringCellValue());
				}
			else {
				int d=(int)xs.getRow(r).getCell(1).getNumericCellValue();
				String s=String.valueOf(d);
				mymaptickets.put(xs.getRow(r).getCell(0).getStringCellValue(), s);
			}

		}
		tickettypeid=mymaptickets.get("tickettypeid");
		ticketname=mymaptickets.get("ticketname");
		ticketmodifiedname=mymaptickets.get("ticketmodifiedname");
		baseurlticket=mymaptickets.get("baseurlticket");
		ticketkey=mymaptickets.get("ticketkey");
	
	}
	
}
