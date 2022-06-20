package crud_operations ;


import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

import java.io.* ;

import org.testng.annotations.* ;
import org.testng.* ;

import io.restassured.http.ContentType ;
import io.restassured.response.Response ;
import io.restassured.specification.* ;

import org.json.simple.*;
import org.json.simple.parser.*;

import reusables.* ;


public class addTest extends baseClass {

	// Utils u = new Utils();

	String dataFileName = "userData" ;
	String apiName = "/api/users/2" ;

	Response Resp ;
	JSONParser parser = new JSONParser() ;
	JSONObject obj ;


	@Test
	public void Add_Test () {
		String newDesignation = this.getString( "random" , 15 );

		JSONObject parameters = new JSONObject() ;

		parameters.put( "name" , employeeName ) ;
		parameters.put( "job" , newDesignation ) ;

		Resp = given().
			contentType( ContentType.JSON ).
			body( parameters ).
			when().
			patch( baseURI + apiName ) ;

		System.out.println( "\nOperation => PATCH" ) ;
		System.out.println( "Request => " + parameters.toJSONString() ) ;
		System.out.println( "Responce => " + Resp.getBody().asString() ) ;
		System.out.println( "Statuscode => " + Resp.statusCode() ) ;
		System.out.println( "Responce time => " + Resp.time() ) ;

		Resp.	
		then().
		assertThat().
		statusCode( 200 ).
		and().
		time( lessThan( ( long ) 5000 ) ).
		and().
		body(
			"name" , is( employeeName ) ,
			"job" , is( newDesignation ) ,
			"updatedAt" , notNullValue()
		) ;
	}
}