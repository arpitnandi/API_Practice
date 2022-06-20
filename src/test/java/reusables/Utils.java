package reusables ;


import java.io.* ;

import org.apache.poi.ss.usermodel.* ;
import org.apache.poi.xssf.usermodel.* ;


public class Utils {

	public void writeExcel ( String File , String Sheet , int Row , int Cell , String Value ) throws IOException {
		XSSFWorkbook Write = new XSSFWorkbook() ;
		XSSFSheet S = Write.getSheet( Sheet ) ;
		Row R = S.getRow( Row ) ;
		Cell C = R.getCell( Cell ) ;

		C.setCellValue( Value ) ;

		Write.write( new FileOutputStream( File ) ) ;
		Write.close() ;
	}

	public String readExcel ( String File , String Sheet , int Row , int Cell ) throws IOException {
		XSSFSheet S = new XSSFWorkbook( new FileInputStream( File ) ).getSheet( Sheet ) ;
		Cell C = S.getRow( Row ).getCell( Cell ) ;

		String Value = "" ;
		int Val ;

		if( C.getCellType().equals( CellType.NUMERIC ) )
		{
			Val = (int) C.getNumericCellValue();
			Value = Value + Val ;
		}
		else if( C.getCellType().equals( CellType.STRING ) )
			Value = C.getStringCellValue() ;

		return Value ;
	}

	public static String getString ( String stringType , int length ) {
		String requiredString = "" ;
		String smallLetters = "abcdefghijklmnopqrstuvwxyz" ;
		String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ;
		String numbers = "1234567890" ;
		String specialCharacters = "~`!@#$%^&*()_-+={[}]|:;'<,>.?/" ;

		for( int i = 0 ; i < length ; i++ )
		{
			if( stringType.toLowerCase().equals("random") ) {
				if( i < length * 0.25 )  {
					requiredString += smallLetters.charAt( ( int ) Math.floor( Math.random() * smallLetters.length() ) ) ;
				}
				else if( i < length * 0.5 )  {
					requiredString += capitalLetters.charAt( ( int ) Math.floor( Math.random() * capitalLetters.length() ) ) ;
				}
				else if( i < length * 0.75 )  {
					requiredString += numbers.charAt( ( int ) Math.floor( Math.random() * numbers.length() ) ) ;
				}
				else if( i < length )  {
					requiredString += specialCharacters.charAt( ( int ) Math.floor( Math.random() * specialCharacters.length() ) ) ;
				}
			}
			
			if( stringType.toLowerCase().equals("alphanumeric") ) {
				if( i < length * 0.33 )  {
					requiredString += smallLetters.charAt( ( int ) Math.floor( Math.random() * smallLetters.length() ) ) ;
				}
				else if( i < length * 0.66 )  {
					requiredString += capitalLetters.charAt( ( int ) Math.floor( Math.random() * capitalLetters.length() ) ) ;
				}
				else if( i < length )  {
					requiredString += numbers.charAt( ( int ) Math.floor( Math.random() * numbers.length() ) ) ;
				}
			}
			
			if( stringType.toLowerCase().equals("numeric") ) {
				requiredString += numbers.charAt( ( int ) Math.floor( Math.random() * numbers.length() ) ) ;
			}
			
			if( stringType.toLowerCase().equals("alphabet") ) {
				if( i < length * 0.5 )  {
					requiredString += smallLetters.charAt( ( int ) Math.floor( Math.random() * smallLetters.length() ) ) ;
				}
				if( i < length )  {
					requiredString += capitalLetters.charAt( ( int ) Math.floor( Math.random() * capitalLetters.length() ) ) ;
				}
			}
			
			if( stringType.toLowerCase().equals("specialcharacters") ) {
				requiredString += specialCharacters.charAt( ( int ) Math.floor( Math.random() * specialCharacters.length() ) ) ;
			}
		}

		return requiredString ;
	}

}