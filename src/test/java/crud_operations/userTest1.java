package crud_operations ;


import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

import java.io.* ;

import org.testng.annotations.* ;
import org.testng.* ;

import io.restassured.http.ContentType ;
import io.restassured.response.Response ;

import reusables.* ;


public class userTest1 extends baseClass
{

	String dataFileName = "userData" ;
	
	int page1 = 1 ;
	Response Resp1 = given().when().get( "https://reqres.in/api/users?page=" + page1 ) ;

	@Test ( priority = 1 )
	public void ValidateResponse_for_Page_1 ()
	{
		Resp1.
		then().
		assertThat().
		statusCode( 200 ).
		and().
		contentType( ContentType.JSON ).
		and().
		time( lessThan( ( long ) 10000 ) ) ;
	}
	
	@Test( priority = 2 , dataProvider = "page1data" )
	public void ValidateData_for_Page_1 ( String[] data )
	{
		int index = ( Integer.parseInt( data[0] ) - ( ( page1 - 1 ) * 6 ) - 1) ;

		Resp1.
		then().
		assertThat().
		body( "data[" + index  + "].id", equalTo( Integer.parseInt( data[0] ) ) ).
		and().
		body( "data[" + index + "].email", equalTo( data[1] ) ).
		and().
		body( "data[" + index + "].first_name", equalTo( data[2] ) ).
		and().
		body( "data[" + index + "].last_name", equalTo( data[3] ) ).
		and().
		body(  "data[" + index + "].avatar", equalTo( data[4] ) ) ;
	}
	
	@DataProvider( name = "page1data" )
	public Object[][] getPage1Data() throws IOException
	{
		Utils U = new Utils() ;
		Object[][] data = new Object[6][5] ;
		String dataFile = testDataLocation + "\\" + dataFileName + ".xlsx" ;
		
		for( int i = 0 ; i <= 5 ; i++ )
		{
			data[i][0] = U.readExcel( dataFile, "page" + page1 , i + 1 , 0 ) ;
			data[i][1] = U.readExcel( dataFile, "page" + page1 , i + 1 , 1 ) ;
			data[i][2] = U.readExcel( dataFile, "page" + page1 , i + 1 , 2 ) ;
			data[i][3] = U.readExcel( dataFile, "page" + page1 , i + 1 , 3 ) ;
			data[i][4] = U.readExcel( dataFile, "page" + page1 , i + 1 , 4 ) ;
		}
		
		return data ;
	}

}