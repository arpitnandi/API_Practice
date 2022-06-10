package reusables;


import java.io.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;


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
		XSSFSheet S = new XSSFWorkbook(new FileInputStream(File)).getSheet(Sheet);
		Cell C = S.getRow(Row).getCell(Cell);

		String Value = "";
		int Val;

		if( C.getCellType().equals(CellType.NUMERIC))
		{
			Val = (int) C.getNumericCellValue();
			Value = Value+Val;
		}
		else if( C.getCellType().equals(CellType.STRING))
			Value = C.getStringCellValue();

		return Value;
	}

}