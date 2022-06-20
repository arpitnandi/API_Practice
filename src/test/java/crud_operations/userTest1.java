package crud_operations ;


import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

import java.io.* ;

import org.testng.annotations.* ;
import org.testng.* ;

import io.restassured.http.ContentType ;
import io.restassured.response.Response ;

import reusables.* ;


public class userTest1 extends baseClass {

	String dataFileName = "userData" ;
	
	int page = 1 ;
	Response Resp = given().when().get( baseURI + "/api/users?page=" + page ) ;

	@Test ( priority = 1 )
	public void ValidateResponse_for_Page_1 () {

		System.out.println( "\nOperation => GET" ) ;
		System.out.println( "Responce => " + Resp.getBody().asString() ) ;
		System.out.println( "Statuscode => " + Resp.statusCode() ) ;
		System.out.println( "Responce time => " + Resp.time() ) ;

		Resp.
		then().
		assertThat().
		statusCode( 200 ).
		and().
		contentType( ContentType.JSON ).
		and().
		time( lessThan( ( long ) 10000 ) ) ;
	}
	
	@Test( priority = 2 , dataProvider = "pagedata" )
	public void ValidateData_for_Page_1 ( String[] data ) {
		int index = ( Integer.parseInt( data[0] ) - ( ( page - 1 ) * 6 ) - 1) ;

		Resp.
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
	
	@DataProvider( name = "pagedata" )
	public Object[][] getPage1Data() throws IOException {
		Utils U = new Utils() ;
		Object[][] data = new Object[6][5] ;
		String dataFile = testDataLocation + "\\" + dataFileName + ".xlsx" ;
		
		for( int i = 0 ; i <= 5 ; i++ )
		{
			data[i][0] = this.readExcel( dataFile, "page" + page , i + 1 , 0 ) ;
			data[i][1] = this.readExcel( dataFile, "page" + page , i + 1 , 1 ) ;
			data[i][2] = this.readExcel( dataFile, "page" + page , i + 1 , 2 ) ;
			data[i][3] = this.readExcel( dataFile, "page" + page , i + 1 , 3 ) ;
			data[i][4] = this.readExcel( dataFile, "page" + page , i + 1 , 4 ) ;
		}
		
		return data ;
	}

}