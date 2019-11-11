package crud_methods;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utils 
{
	public void writeExcel(String File, String Sheet, int Row, int Cell, String Value) throws IOException
	{
		XSSFWorkbook Write = new XSSFWorkbook();
		XSSFSheet S = Write.getSheet(Sheet);
		Row R = S.getRow(Row);
		Cell C = R.getCell(Cell);
		
		C.setCellValue(Value);
		
		Write.write(new FileOutputStream(File));
		Write.close();
	}
	
	public String readExcel(String File, String Sheet, int Row, int Cell) throws IOException
	{
		@SuppressWarnings("resource")
		XSSFSheet S = new XSSFWorkbook(new FileInputStream(File)).getSheet(Sheet);
		Cell C = S.getRow(Row).getCell(Cell);
		
		String Value = "";
		double Val;
		
		if( C.getCellType().equals(CellType.NUMERIC))
		{
			Val = C.getNumericCellValue();
			Value = (Value+Val).replace(".0", "");
		}
		else if( C.getCellType().equals(CellType.STRING))
			Value = C.getStringCellValue();
		    
		return Value;
	}
}
