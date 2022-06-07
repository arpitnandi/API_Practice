package crud_methods;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.*;

import org.testng.annotations.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class Get
{

	int page = 2 ;
	String testDataDirectory = "D:\\Coading\\API_Practice\\src\\test\\java\\testData\\" ;

	public Response Resp = given().when().get( "https://reqres.in/api/users?page=" + page );
	
	
	@Test()
	public void ValidateResponse()
	{
		Resp.
		then().
		assertThat().
		statusCode( 200 ).
		and().
		contentType( ContentType.JSON ).
		and().
		time( lessThan( ( long ) 10000) );
	}
	
	@Test( dataProvider = "Data" )
	public void ValidateData(String[] data)
	{
		int index = ( Integer.parseInt( data[0] ) - ( ( page - 1 ) * 6 ) - 1);

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
		body(  "data[" + index + "].avatar", equalTo( data[4] ) );
	}
	
	@DataProvider( name = "Data" )
	public Object[][] getData() throws IOException
	{
		Utils U = new Utils();
		Object[][] data = new Object[6][5];
		String dataFile = testDataDirectory + "Data.xlsx";
		
		for( int i = 0 ; i <= 5 ; i++ )
		{
			data[i][0] = U.readExcel(dataFile, "Users", 7+i, 0);
			data[i][1] = U.readExcel(dataFile, "Users", 7+i, 1);
			data[i][2] = U.readExcel(dataFile, "Users", 7+i, 2);
			data[i][3] = U.readExcel(dataFile, "Users", 7+i, 3);
			data[i][4] = U.readExcel(dataFile, "Users", 7+i, 4);
		}
		
		return data;
	}
}