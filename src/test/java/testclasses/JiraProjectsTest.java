package testclasses;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import HelperClasses.ExcelHandler;
import HelperClasses.JsonHandler;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

public class JiraProjectsTest extends JsonHandler {

	@BeforeMethod
	public void getValuesFromExcel() throws IOException {
		ExcelHandler.handleExcel();
	}

	@Test(priority = 1)
	public void getAllProjectsTest() {
		given().auth().preemptive().basic(ExcelHandler.username, ExcelHandler.token).when()
				.get(ExcelHandler.baseurl + "/" + "search").then().statusCode(200).log().all();
	}

	@Test(priority = 2)
	public void addProjectTest() {
		try {
			given().auth().preemptive().basic(ExcelHandler.username, ExcelHandler.token)
					.header("Accept", "application/json").header("Content-Type", "application/json")
					.body(generatePayloadForProjects("POST")).when().post(ExcelHandler.baseurl).then().statusCode(201)
					.body("key", equalTo(ExcelHandler.projectkey)).log().all();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void modifyProjectTest() {
		try {
			given().auth().preemptive().basic(ExcelHandler.username, ExcelHandler.token)
					.header("Content-Type", "application/json").body(generatePayloadForProjects("PUT")).when()
					.put(ExcelHandler.baseurl + "/" + ExcelHandler.projectkey).then().statusCode(200)
					.body("name", equalTo(ExcelHandler.projectmodifiedname)).log().all();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 4)
	public void deleteProjectTest() {
		given().auth().preemptive().basic(ExcelHandler.username, ExcelHandler.token).when()
				.delete(ExcelHandler.baseurl + "/" + ExcelHandler.projectkey).then().statusCode(204).log().all();
	}
}
