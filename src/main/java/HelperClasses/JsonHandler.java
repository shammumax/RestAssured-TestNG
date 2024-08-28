package HelperClasses;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.json.JSONTokener;

import groovy.lang.GString;

public class JsonHandler {

	//below is the generic method to get string from file resource ..
	
	
	public static String generateStringFromResourceFile(String path) throws IOException
	{
		File f = new File(path); // referred to a file
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject jo = new JSONObject(jt);
		return jo.toString();
	}
	public static String generatePayloadForProjects(String requestType) throws IOException {
		String finalbody=null;
		switch(requestType) {
			case "POST":
				String filepath="C:\\Users\\HP\\eclipse-workspace1\\RestAssured\\src\\test\\resources\\Payloads\\addProjectPayload.json";
				String body = generateStringFromResourceFile(filepath);
				body=body.replace("$projectkey", ExcelHandler.projectkey);
				body=body.replace("$projectname",ExcelHandler.projectname);
				finalbody=body;
				break;
				
			case "PUT":
				String filepath2="C:\\Users\\HP\\eclipse-workspace1\\RestAssured\\src\\test\\resources\\Payloads\\modifyProjectPayload.json";
				String body2 = generateStringFromResourceFile(filepath2);
				body2=body2.replace("$projectkey", ExcelHandler.projectkey);
				body2=body2.replace("$projectname",ExcelHandler.projectmodifiedname);
				finalbody=body2;
				break;
		}
		return finalbody;
	}
	
	public static String generatePayloadForTicket(String requestType) throws IOException {
		String finalbody=null;
		switch(requestType) {
			case "POST":
				String filepath="C:\\Users\\HP\\eclipse-workspace1\\RestAssured\\src\\test\\resources\\Payloads\\addTicketPayload.json";
				String body = generateStringFromResourceFile(filepath);
				body=body.replace("$tickettypeid", ExcelHandler.tickettypeid);
				body=body.replace("$ticketname",ExcelHandler.ticketname);
				System.out.println(body);
				finalbody=body;
				break;
				
			case "PUT":
				String filepath2="C:\\Users\\HP\\eclipse-workspace1\\RestAssured\\src\\test\\resources\\Payloads\\addTicketPayload.json";
				String body2 = generateStringFromResourceFile(filepath2);
				body2=body2.replace("$tickettypeid", ExcelHandler.tickettypeid);
				body2=body2.replace("$ticketname",ExcelHandler.ticketmodifiedname);
				System.out.println(body2);
				finalbody=body2;
				break;
				}
		return finalbody;
	}


	
}
