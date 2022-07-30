package PracticeApi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class DataDriven
{
	@Test
	public ArrayList<String> getDataFromExcel(String Country ) throws IOException
	{
		FileInputStream file= new FileInputStream("C:\\Users\\QQA0429\\Downloads\\PracticeApi.xlsx");
		ArrayList<String> a= new ArrayList<>();
		XSSFWorkbook workbook= new XSSFWorkbook(file);
		
		int sheets=workbook.getNumberOfSheets();
		
		for(int i=0; i<sheets; i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("Sheet1"));
			{  
				XSSFSheet sheet=workbook.getSheetAt(i);
			        Iterator<Row> rows=sheet.iterator();
			       Row firstrow=rows.next();
			       
			      Iterator<Cell> cell=firstrow.cellIterator();
			       int k=0;
			       int column=0;
			       while(cell.hasNext())
			       {
			    	   Cell value=cell.next();
			    	   if(value.getStringCellValue().equalsIgnoreCase(Country))
			    	   {
			    		   column=k;
			    	   }
			    	   k++;
			       }
			       System.out.println(column);
			       while(rows.hasNext())
			       {
			    	   Row r=rows.next();
			    	   if(r.getCell(column).getStringCellValue().equalsIgnoreCase("France"))
			    			   {
			    		   Iterator<Cell> cv=r.cellIterator();
			    		   while(cv.hasNext())
			    		   {
			    			   Cell c=cv.next();
			    			   if(c.getCellType()==CellType.STRING)
			    			   {
			    				   a.add(c.getStringCellValue());
			    			   }
			    			   else {
			    				   a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
			    				   
			    			   }
			    			   
			    			   }
			    			   System.out.println(" ");
			    		   }
			    			   }		   
			    	   
			    	   
			       }
			       
			       
			}return a;
		}
		
		
		
		
	}


