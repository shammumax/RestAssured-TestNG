package testclasses;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

import HelperClasses.ExcelHandler;
import HelperClasses.JsonHandler;

public class JiraTicketsTest extends JsonHandler {
	public static String ticketkey;

	@BeforeMethod
	public void setExceldata() throws IOException {
		ExcelHandler.handleExcel();
	}

	@Test(priority = 1)
	public void addTicketTest() throws IOException {
		ticketkey = given().auth().preemptive().basic(ExcelHandler.username, ExcelHandler.token)
				.header("Content-Type", "application/json").body(generatePayloadForTicket("POST")).when()
				.post(ExcelHandler.baseurlticket).then().statusCode(201).log().all().extract().path("key");
	}

	@Test(priority = 2)
	public void getTicketToCheckPostTest() {

		given().auth().preemptive().basic(ExcelHandler.username, ExcelHandler.token).when()
				.get(ExcelHandler.baseurlticket + "/" + ticketkey).then().statusCode(200).log().all()
				.body("key", equalTo(ticketkey)).body("fields.summary", equalTo(ExcelHandler.ticketname));

	}

	@Test(priority = 3)
	public void modifyTicketTest() throws IOException {
		given().auth().preemptive().basic(ExcelHandler.username, ExcelHandler.token)
				.header("Content-Type", "application/json").body(generatePayloadForTicket("PUT")).when()
				.put(ExcelHandler.baseurlticket + "/" + ticketkey).then().statusCode(204).log().all();
	}

	@Test(priority = 4)
	public void getTicketToCheckPutTest() {

		given().auth().preemptive().basic(ExcelHandler.username, ExcelHandler.token).when()
				.get(ExcelHandler.baseurlticket + "/" + ticketkey).then().statusCode(200).log().all()
				.body("fields.summary", equalTo(ExcelHandler.ticketmodifiedname));

	}

	@Test(priority = 5)
	public void deleteTicketTest() throws IOException {
		given().auth().preemptive().basic(ExcelHandler.username, ExcelHandler.token).when()
				.delete(ExcelHandler.baseurlticket + "/" + ticketkey).then().statusCode(204).log().all();
	}

	@Test(priority = 6)
	public void getTicketToCheckDeleteTest() {
		Response res = given().auth().preemptive().basic(ExcelHandler.username, ExcelHandler.token).when()
				.get(ExcelHandler.baseurlticket + "/" + ticketkey).then().statusCode(404).log().all().extract()
				.response();
		assertTrue(res.asString().contains("Issue does not exist or you do not have permission to see it"));

	}
}
