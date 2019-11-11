package crud_methods;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Get
{
	Response Resp = given().when().get("https://reqres.in/api/users?page="+2);
	
	@Test()
	public void ValidateResponse()
	{
		Resp.
		then().
		assertThat().
		statusCode(200).
		and().
		contentType(ContentType.JSON).
		and().
		time(lessThan((long)10000));
	}
	
	@Test( dataProvider = "Data" )
	public void ValidateData(String[][] data)
	{
		for(int i = 1 ; i <= 6 ; i++ )
		{
			Resp.then().assertThat().body("data["+(i-1)+"].id", equalTo(Integer.parseInt(data[i][0]))).
			and().
			body("data["+(i-1)+"].email", equalTo(data[i][1])).
			and().
			body("data["+(i-1)+"].first_name", equalTo(data[i][2])).
			and().
			body("data["+(i-1)+"].last_name", equalTo(data[i][3])).
			and().
			body("data["+(i-1)+"].avatar", equalTo(data[i][4]));
		}
	}
	
	@DataProvider( name = "Data" )
	public String[][] getData() throws IOException
	{
		Utils U = new Utils();
		String[][] data = new String[6][5];
		String dataFile = "C:\\Users\\Arpith\\eclipse-workspace\\API_Practice\\Data.xlsx";
		
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