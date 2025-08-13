package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ReadXLSdata {

	@DataProvider(name="login_testdata")	
	public String[][] getData(Method m) throws EncryptedDocumentException, IOException {
		
		String excelSheetName = m.getName();
//		File f = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\LoginData.xlsx");
		File f = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\LoginData.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheetName = wb.getSheet(excelSheetName);
		
		int totalRows = sheetName.getLastRowNum();
		System.out.println(totalRows);
		
		Row rowCells = sheetName.getRow(0);
		int totalcols = rowCells.getLastCellNum();
		System.out.println(totalcols);
		
		DataFormatter  format = new DataFormatter();
		String testData[][]= new String[totalRows][totalcols];
		for(int i=1; i<=totalRows; i++)
		{
			for(int j=0;j<totalcols;j++) {
				testData[i-1][j]=format.formatCellValue(sheetName.getRow(i).getCell(j));
				System.out.println(testData[i-1][j]);
			}
		}
		return testData;
	
	}
	
	// Add this method outside of getData(), inside the class
	public static String getCellData(String sheetName, int rowNum, int colNum) {
	    try {
	        File f = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\LoginData.xlsx");
	        FileInputStream fis = new FileInputStream(f);
	        Workbook wb = WorkbookFactory.create(fis);
	        Sheet sheet = wb.getSheet(sheetName);

	        DataFormatter format = new DataFormatter();
	        String cellValue = format.formatCellValue(sheet.getRow(rowNum).getCell(colNum));

	        wb.close();
	        return cellValue;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	 // ✅ Method to get row count
    public static int getRowCount(String sheetName) {
        try {
            File f = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\LoginData.xlsx");
            FileInputStream fis = new FileInputStream(f);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum() + 1;
            wb.close();
            return rowCount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ✅ Method to get column count
    public static int getColumnCount(String sheetName) {
        try {
            File f = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\LoginData.xlsx");
            FileInputStream fis = new FileInputStream(f);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(sheetName);
            Row row = sheet.getRow(0);
            int colCount = row.getLastCellNum();
            wb.close();
            return colCount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
