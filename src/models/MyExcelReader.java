package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import models.Staff;





public class MyExcelReader {

	private	File excelFile;
	private Workbook workbook;
	private XSSFSheet sheet;
	private Iterator<Row> itr;
	
	public MyExcelReader(File excelFile) throws IOException
	{
		FileInputStream file = new FileInputStream(excelFile);
		Workbook sheety = new XSSFWorkbook(file);
		this.setWorkbook(sheety);
		this.setSheet(this.getWorkbook().getSheetAt(0));
		this.setItr( this.sheet.iterator());
	}
	
	
	public MyExcelReader() {
		// TODO Auto-generated constructor stub
	}


	public ArrayList<Staff> ReadAndGetList()
	{
		ArrayList<Staff> staffs = new ArrayList<Staff>();
		int i = 0;
		while (this.itr.hasNext())
		{
			Row row = itr.next();
			//System.out.println(getStaff(row));
			if (i > 0)
				staffs.add(getStaff(row));
			else
				i++;
		
				
			}
		
		return staffs;
		
	}

	
	

	
	   private Staff getStaff(Row row )
	   {
		   Staff staff = new Staff();
		  
		   DataFormatter dataFormatter = new DataFormatter();
		   Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext())
			{
				Cell cell = cellIterator.next();
				String cellValue = dataFormatter.formatCellValue(cell);
				//System.out.println(cellValue);
				
				switch (cell.getColumnIndex())
				{
			   		case 0:
			   		{
			   			staff.setId(atoi(cellValue));
			   			break;
			   		}
			   		case 1:
						   staff.setBirth_day(cellValue);
			   			break;
			   		case 2:
			   		{
			   				
			   					staff.setBirth_place(cellValue);
			   		
			   			break;
			   		}
			   		
			   		case 3:
						   staff.setSex(cellValue.charAt(0));
			   			break;
			   		case 4:
			   			staff.setCin(cellValue);
			   			break;
					case 5:
						staff.setSit_fam(cellValue);
						break;
					case 6:
						staff.setNationalite(cellValue);
						break;
					case 7:
						staff.setDate_embauche(cellValue);
						break;
					case 8:
						staff.setGrade(cellValue);
						break;
					case 9:
						staff.setFunctio_n(cellValue);
						break;
					case 10:
						staff.setPost(cellValue);
						break;
					case 11:
						staff.setCategorie(cellValue);
						break;
					case 12:
						staff.setEchelon(atoi(cellValue));
						break;
					case 13:
						staff.setEnt_effect(cellValue);
						break;
					case 14:
						staff.setSection_analytique(cellValue);
						break;
					case 15:
						staff.setRegime_retraite(cellValue);
					case 16 :
						staff.setAffil_recore(atoi(cellValue));
						break;
					case 17 :
						staff.setDate_der_promo(cellValue);
				
				}
		   }
		   return staff;
	   }
	 	
	public static void Free()
	{
		System.gc();
	}
	
	public int atoi(String str) {
		
		if (str == null || str.length() < 1)
			return 0;
	 
		// trim white spaces
		str = str.trim();
	 
		char flag = '+';
	 
		// check negative or positive
		int i = 0;
		if (str.charAt(0) == '-') {
			flag = '-';
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}
		// use double to store result
		double result = 0;
	 
		// calculate value
		while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			result = result * 10 + (str.charAt(i) - '0');
			i++;
		}
	 
		if (flag == '-')
			result = -result;
	 
		// handle max and min
		if (result > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
	 
		if (result < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
	 
		return (int) result;
	}
	
	public void setExcelFile(File excelFile)
	{
		if (excelFile != null)
			this.excelFile = excelFile;
		else
			throw new IllegalArgumentException("invalid file");
	}
	
	public void setWorkbook(Workbook sheet)
	{
		if (sheet != null)
			this.workbook = sheet;
		else
			throw new IllegalArgumentException("invalid file");
	}
	public File getExcelFile()
	{
		return excelFile;
	}
	public Workbook getWorkbook()
	{
		return workbook;
	}


	public XSSFSheet getSheet() {
		return sheet;
	}


	public void setSheet(Sheet sheet2) {
		this.sheet = (XSSFSheet) sheet2;
	}


	public Iterator<Row> getItr() {
		return itr;
	}


	public void setItr(Iterator<Row> itr) {
		this.itr = itr;
	}
	
}