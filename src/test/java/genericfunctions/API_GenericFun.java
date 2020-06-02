package genericfunctions;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.jetty.websocket.common.io.payload.PayloadProcessor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_GenericFun {
	
	
	/**
	 * Reading BasePath from API - JSON
	 * @param APIJSON
	 * @return
	 * @throws ParseException
	 */
	public String GET_BASEPATH(String APIJSON) throws ParseException {
		String BASEPATH = "";
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(APIJSON);
		JSONObject jsonobj = (JSONObject)obj;
		BASEPATH = (String) jsonobj.get("BASEURL");
		return BASEPATH;
	}
	
	/**
	 * Return ResourceURL which will add in BASEURL to form Complete URL
	 * @param APIJSON
	 * @return
	 * @throws ParseException
	 */
	public String GET_RESOURCEPATH(String APIJSON) throws ParseException {
		String RESOURCEPATH = "";
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(APIJSON);
		JSONObject jsonobj = (JSONObject)obj;
		RESOURCEPATH = (String) jsonobj.get("RESOURCEURL");
		return RESOURCEPATH;
	}
	
	/**
	 * Return Proxy IP - if required to use
	 * @param APIJSON
	 * @return
	 * @throws ParseException
	 */
	public String GET_PROXYIP(String APIJSON) throws ParseException {
		String PROXYIP = "";
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(APIJSON);
		JSONObject jsonobj = (JSONObject)obj;
		PROXYIP = (String) jsonobj.get("PROXYIP");
		return PROXYIP;
	}
	
	/**
	 * Read PAYLOAD
	 * @param APIJSON
	 * @return
	 * @throws ParseException
	 */
	public String READ_PAYLOAD(String APIJSON) throws ParseException {
		String PAYLOADFILENAME = "";
		String PAYLOAD = "";
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(APIJSON);
		JSONObject jsonobj = (JSONObject)obj;
		PAYLOADFILENAME = (String) jsonobj.get("PAYLOADFILE");
		
		if(!PAYLOADFILENAME.toLowerCase().contains(".json"))
			PAYLOADFILENAME = PAYLOADFILENAME+".json";
		
		String CurrentDir = "";
		String OS = System.getProperty("os.name");
		if(OS.toLowerCase().contains("window"))
			CurrentDir = System.getProperty("user.dir")+"\\config\\JSONFILES\\"+PAYLOADFILENAME;
		else
			CurrentDir = System.getProperty("user.dir")+"/config/JSONFILES/"+PAYLOADFILENAME;
		
		File PayloadFile = new File(CurrentDir);
		PAYLOAD = ReadJSON(PayloadFile);		
		return PAYLOAD;
	}
	
	/**
	 * Return Proxy PORT - if required to use
	 * @param APIJSON
	 * @return String
	 * @throws ParseException
	 */
	public String GET_PORT(String APIJSON) throws ParseException {
		String PORT = "";
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(APIJSON);
		JSONObject jsonobj = (JSONObject)obj;
		PORT = (String) jsonobj.get("PORT");
		return PORT;
	}
	
	/**
	 * Return all the Query Parameter from APIJSON
	 * @param APIJSON
	 * @return HASHMAP
	 * @throws ParseException
	 */
	public HashMap<String,String> GET_QUERYPARAMETER(String APIJSON) throws ParseException {
		HashMap<String, String> QUERYPARAM = new HashMap<String, String>();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(APIJSON);
		JSONObject jsonobj = (JSONObject)obj;
		JSONObject queryobj = (JSONObject) jsonobj.get("QUERYPARAM");
		Set<String> AllKey = queryobj.keySet();
		for (String Key : AllKey) {
			String Value = (String) queryobj.get(Key);
			//System.out.println("Key="+Key);
			//System.out.println("Value="+Value);
			if(Key.length()>0)
				QUERYPARAM.put(Key,Value.trim());
		}
		return QUERYPARAM;
	}
	
	/**
	 * Return all the form Parameter from API JSON
	 * @param APIJSON
	 * @return
	 * @throws ParseException
	 */
	public HashMap<String,String> GET_FORMPARAMETER(String APIJSON) throws ParseException {
		HashMap<String, String> FORMPARAM = new HashMap<String, String>();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(APIJSON);
		JSONObject jsonobj = (JSONObject)obj;
		JSONObject queryobj = (JSONObject) jsonobj.get("FORMPARAM");
		Set<String> AllKey = queryobj.keySet();
		for (String Key : AllKey) {
			String Value = (String) queryobj.get(Key);
			//System.out.println("Key="+Key);
			//System.out.println("Value="+Value);
			if(Key.length()>0)
				FORMPARAM.put(Key,Value.trim());
		}
		return FORMPARAM;
	}	

	/**
	 * Return All the Header Parameter from API JSON 
	 * @param APIJSON
	 * @return
	 * @throws ParseException
	 */
	public HashMap<String,String> GET_HEADERPARAMETER(String APIJSON) throws ParseException {
		HashMap<String, String> HEADERPARAM = new HashMap<String, String>();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(APIJSON);
		JSONObject jsonobj = (JSONObject)obj;
		JSONObject queryobj = (JSONObject) jsonobj.get("HEADERPARAM");
		Set<String> AllKey = queryobj.keySet();
		for (String Key : AllKey) {
			String Value = (String) queryobj.get(Key);
			//System.out.println("Key="+Key);
			//System.out.println("Value="+Value);
			if(Key.length()>0)
				HEADERPARAM.put(Key,Value.trim());
		}
		return HEADERPARAM;
	}
	
	/**
	 * GET Method call for Rest Assured
	 * @param BASEPATH
	 * @param RESOURCEPATH
	 * @param PROXYIP
	 * @param PORT
	 * @param QUERYPARAM
	 * @param FORMPARAM
	 * @param HEADERPARAM
	 * @return
	 * @throws ParseException 
	 */
	public String GetMethod(String APIJSON) throws ParseException
	{
		String BASEPATH = GET_BASEPATH(APIJSON);
		String RESOURCEPATH = GET_RESOURCEPATH(APIJSON);;
		String PROXYIP = GET_PROXYIP(APIJSON);
		String PORT = GET_PORT(APIJSON);		
		HashMap<String,String> QUERYPARAM = GET_QUERYPARAMETER(APIJSON);
		HashMap<String, String> HEADERPARAM = GET_HEADERPARAMETER(APIJSON);
		
		int StatusCode = 0;
		String ResponseDescription = "";
		Response response = null;
		
		// BASEPATH CHECK //
			if(BASEPATH.length()<5)
				return "FAILURE : BASEURL is blank,Kindly check and retry";
			
			RestAssured.baseURI =BASEPATH;
		
		// PROXYIP and PORT Check //	
			if(PROXYIP.length()>2 && PORT.length()>1)
				RestAssured.proxy(PROXYIP,Integer.parseInt(PORT));
			else
				System.out.println("** No Proxy IP and PORT Found so proceeding without That **");
		
		// Checking Query Parameter Since this is GET call and FORMPARAMETER cannot be used //
			if(QUERYPARAM.size()>0 && HEADERPARAM.size()>0) 
			{
				System.out.println("Requesting GET with Header and Query Parameter");
				System.out.println(HEADERPARAM.size());
				
				response = RestAssured.given()
									  .when()
									  .headers(HEADERPARAM)
									  .queryParameters(QUERYPARAM)
									  .get(BASEPATH+RESOURCEPATH); 
			}
			
		// Checking if Both Query Parameter and Header Parameter is blank //
			if(QUERYPARAM.size()==0 && HEADERPARAM.size()==0) 
			{
				System.out.println("Requesting GET with No Header and Query Parameter");
				response = RestAssured.given()
									  .when()
									  .get(BASEPATH+RESOURCEPATH); 
			}
			
		// Checking if Query Parameter is blank and not Header Parameter //
			if(QUERYPARAM.size()==0 && HEADERPARAM.size()>0) 
			{
				System.out.println("Requesting GET with Header But No Query Parameter");
				response = RestAssured.given()
									  .when()
									  .headers(HEADERPARAM)
									  .get(BASEPATH+RESOURCEPATH); 
			}	
			
		// Checking if Header Parameter is blank not query Parameter//
			if(QUERYPARAM.size()>0 && HEADERPARAM.size()==0) 
			{
				System.out.println("Requesting GET with No Header But Query Parameter");
				response = RestAssured.given()
									  .when()
									  .queryParameters(QUERYPARAM)
									  .get(BASEPATH+RESOURCEPATH); 
			}	
		
		// Taking the Status Code and Response Description//
			System.out.println("Response :" + response.asString());
			System.out.println("Status Code:" + response.getStatusCode()); 
			StatusCode = response.getStatusCode();
			ResponseDescription = response.asString();
		
		return "Code:"+StatusCode+"_Description:"+ResponseDescription;
	}
	
	public String PostMethod(String APIJSON) throws ParseException
	{
		String BASEPATH = GET_BASEPATH(APIJSON);
		String RESOURCEPATH = GET_RESOURCEPATH(APIJSON);;
		String PROXYIP = GET_PROXYIP(APIJSON);
		String PORT = GET_PORT(APIJSON);		
		HashMap<String,String> FORMPARAM = GET_FORMPARAMETER(APIJSON);
		HashMap<String, String> HEADERPARAM = GET_HEADERPARAMETER(APIJSON);
		String PAYLOAD = READ_PAYLOAD(APIJSON);
		
		
		int StatusCode = 201;
		String ResponseDescription = "Created";
		Response response = null;
		
		// BASEPATH CHECK //
			if(BASEPATH.length()<5)
				return "FAILURE : BASEURL is blank,Kindly check and retry";
			
			RestAssured.baseURI =BASEPATH;
			
		// PROXYIP and PORT Check //	
			if(PROXYIP.length()>2 && PORT.length()>1)
				RestAssured.proxy(PROXYIP,Integer.parseInt(PORT));
			else
				System.out.println("** No Proxy IP and PORT Found so proceeding without That **");
		
		// Hit when all the parameter are present//
			if(HEADERPARAM.size()>0 && FORMPARAM.size()>0 && PAYLOAD.length()>2) 
			{
				System.out.println("Requesting POST Call with Header , Form and Payload");
				response = RestAssured.given().contentType(ContentType.URLENC.withCharset("UTF-8"))
										.when()			  
										.headers(HEADERPARAM)
										.formParameters(FORMPARAM)
										.body(PAYLOAD)
									    .post(BASEPATH+RESOURCEPATH); 
			}
			// Missing Payload - payload is blank or not present//
			if(HEADERPARAM.size()>0 && FORMPARAM.size()>0 && PAYLOAD.length()<=2) 
			{
				System.out.println("Requesting POST Call with Header and Form Parameter with No Payload");
				response = RestAssured.given().contentType(ContentType.URLENC.withCharset("UTF-8"))
										.when()			  
										.headers(HEADERPARAM)
										.formParameters(FORMPARAM)
										.post(BASEPATH+RESOURCEPATH); 
			}
			
			// No header //
			if(FORMPARAM.size()>0 && PAYLOAD.length()>2) 
			{
				System.out.println("Requesting POST Call with Form Parameter and Payload");
				response = RestAssured.given().contentType(ContentType.URLENC.withCharset("UTF-8"))
										.when()			  
										.formParameters(FORMPARAM)
										.body(PAYLOAD)
									    .post(BASEPATH+RESOURCEPATH); 
			}
			
			//No Header No Payload 
			if(HEADERPARAM.size()==0 && FORMPARAM.size()>0 && PAYLOAD.length()<=2) 
			{
				System.out.println("Requesting POST Call with only Form Parameter");
				response = RestAssured.given().contentType(ContentType.URLENC.withCharset("UTF-8"))
										.when()			  
										.formParameters(FORMPARAM)
									    .post(BASEPATH+RESOURCEPATH); 
			}
			// Only Payload and header //
			if(HEADERPARAM.size()>0 && PAYLOAD.length()>2) 
			{
				System.out.println("Requesting POST Call with Header Parameter and Payload");
				response = RestAssured.given().contentType(ContentType.URLENC.withCharset("UTF-8"))
										.when()			  
										.headers(HEADERPARAM)
										.body(PAYLOAD)
										.post(BASEPATH+RESOURCEPATH); 
			}
			
			// Only Payload  //
						if(HEADERPARAM.size()==0 && PAYLOAD.length()>2) 
						{
							System.out.println("Requesting POST Call with only Payload");
							response = RestAssured.given().contentType(ContentType.URLENC.withCharset("UTF-8"))
													.when()			  
													.body(PAYLOAD)
													.post(BASEPATH+RESOURCEPATH); 
						}
			
		// Taking the Status Code and Response Description//
			System.out.println("Response :" + response.asString());
			System.out.println("Status Code:" + response.getStatusCode()); 
			StatusCode = response.getStatusCode();
			ResponseDescription = response.asString();
		
		return "Code:"+StatusCode+"_Description:"+ResponseDescription;
		
	}
	
	public String PutMethod(String Parameters)
	{
		int StatusCode = 201;
		String ResponseDescription = "Updated";
		
		// parse Parameter //
		
		// Create REST Assured call for PUT //
		
		// update Status Code and Response Description//
		
		return "Code:"+StatusCode+"_Description:"+ResponseDescription;
	}
	
	public String DeleteMethod(String Parameters)
	{
		int StatusCode = 202;
		String ResponseDescription = "Accepted";
		
		// parse Parameter //
		
		// Create REST Assured call for DELETE //
		
		// update Status Code and Response Description//
		
		return "Code:"+StatusCode+"_Description:"+ResponseDescription;
	}
	
	public String ReadJSON(File F)
	{
		 JSONParser jsonParser = new JSONParser();
         String Value = "";
	        try (FileReader reader = new FileReader(F))
	        {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	            
	            Value = obj.toString();
	        }
	        catch(Exception e)
	        {
	        	System.out.println("");
	        }
	            return Value;
		
	
	}
}
