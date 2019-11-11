package crud_methods;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import org.json.simple.parser.ParseException;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Test
{
	public static void main(String[] args) throws IOException, ParseException
	{
		Response Resp = given().when().get("https://reqres.in/api/users?page="+2);

		Resp.
		then().
		assertThat().
		statusCode(200).
		and().
		contentType(ContentType.JSON).
		and().
		time(lessThan((long)10000));
		
		Utils U = new Utils();
		String dataFile = "C:\\Users\\Arpith\\eclipse-workspace\\API_Practice\\Data.xlsx";
		
		for( int i = 0 ; i <= 5 ; i++ )
		{
			Resp.
			then().
			assertThat().
			body("data["+i+"].id", equalTo((U.readExcel(dataFile, "Users", (i+7), 0)))).
			and().
			body("data["+i+"].email", equalTo(U.readExcel(dataFile, "Users", (i+7), 1))).
			and().
			body("data["+i+"].first_name", equalTo(U.readExcel(dataFile, "Users", (i+7), 2))).
			and().
			body("data["+i+"].last_name", equalTo(U.readExcel(dataFile, "Users", (i+7), 3))).
			and().
			body("data["+i+"].avatar", equalTo(U.readExcel(dataFile, "Users", (i+7), 4)));	
		}
	}
}
