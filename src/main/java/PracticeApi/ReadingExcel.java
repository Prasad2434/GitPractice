package PracticeApi;

import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
public class ReadingExcel
{
	@Test
	public void ReadData() throws IOException
	{
		String ExcelPath="C:\\Users\\QQA0429\\Downloads\\PracticeApi.xlsx";
		
		FileInputStream file= new FileInputStream(ExcelPath);
		
		
		XSSFWorkbook workbook= new XSSFWorkbook(file);
		
		//XSSFSheet sheet=workbook.getSheet("Sheet1");
		XSSFSheet sheet=workbook.getSheetAt(0);
		
	//Using For Loop
		
		int rows=sheet.getLastRowNum();
		int col=sheet.getRow(1).getLastCellNum();
		System.out.println(rows);
		System.out.println(col);
		/*for(int i=0; i<=rows; i++)
		{
			XSSFRow row=sheet.getRow(i);
			for(int j=0; j<col;j++)
			{
				XSSFCell cell=row.getCell(j);
				
				switch (cell.getCellType()) 
				{
				case STRING: System.out.print(cell.getStringCellValue()); break;
				case NUMERIC: System.out.print(cell.getNumericCellValue());break;
				case BOOLEAN: System.out.print(cell.getBooleanCellValue());break;
				}
				System.out.print(" | ");
			}
			System.out.println();
			
		}*/
		
		// By Using Iterator
		
		Iterator iterator=sheet.iterator();
		
		while(iterator.hasNext())
		{
			XSSFRow row =(XSSFRow)iterator.next();
	      	Iterator cellIterator=row.cellIterator();
	      	while(cellIterator.hasNext())
	      	{
	      		XSSFCell cell=(XSSFCell)cellIterator.next();
	      		switch(cell.getCellType())
	      		{
	      		case STRING: System.out.print(cell.getStringCellValue());break;
	      		case NUMERIC: System.out.print(cell.getNumericCellValue());break;
	      		case BOOLEAN: System.out.print(cell.getBooleanCellValue());break;
	      		}
	      		System.out.print(" | ");
	      	}
	      	System.out.println();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
