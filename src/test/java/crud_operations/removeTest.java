package crud_operations ;


import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

import java.io.* ;

import org.testng.annotations.* ;
import org.testng.* ;

import io.restassured.http.ContentType ;
import io.restassured.response.Response ;

import reusables.* ;


public class removeTest extends baseClass
{

	String apiName = "/api/users/2" ;

	Response Resp ;
	

	@Test
	public void Remove_Test () {
		
		Resp = given().
			when().
			delete( baseURI + apiName ) ;

		System.out.println( "\nOperation => DELETE" ) ;
		System.out.println( "Statuscode => " + Resp.statusCode() ) ;
		System.out.println( "Responce time => " + Resp.time() ) ;

		Resp.	
		then().
		assertThat().
		statusCode( 204 ).
		and().
		time( lessThan( ( long ) 1000 ) );
	}
}