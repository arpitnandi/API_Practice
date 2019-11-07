package crud_methods;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import org.json.simple.parser.ParseException;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
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
		
		for( int i = 1 ; i <= 6 ; i++ )
		{
			JsonPath path = Resp.jsonPath();
			Resp.
			then().
			assertThat().
			body("data["+(i-1)+"].id", equalTo((Integer.parseInt(U.readExcel(dataFile, "Users", i, 0))))).
			and().
			body("data["+(i-1)+"].email", equalTo(U.readExcel(dataFile, "Users", i, 1))).
			and().
			body("data["+(i-1)+"].first_name", equalTo(U.readExcel(dataFile, "Users", i, 2))).
			and().
			body("data["+(i-1)+"].last_name", equalTo(U.readExcel(dataFile, "Users", i, 3))).
			and().
			body("data["+(i-1)+"].avatar", equalTo(U.readExcel(dataFile, "Users", i, 4)));	
		}
	}
}
