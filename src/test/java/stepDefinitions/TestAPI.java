package stepDefinitions;

import java.util.HashMap;

import org.assertj.core.api.SoftAssertions;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import genericfunctions.API_GenericFun;
import genericfunctions.GenericFunction;

public class TestAPI {
	API_GenericFun apiobj = new API_GenericFun();
	GenericFunction gfobj = new GenericFunction();
	static String APIResponse = "";
	static String APIJSON = "";
	SoftAssertions softly = new SoftAssertions();
	
	
	@Given("^Use \"([^\"]*)\" Parameter for API Call$")
	public void use_Parameter_for_API_Call(String APIJSON_ALLPARAM) throws Throwable {
		// Read the API parameter //
				APIJSON = gfobj.ReadConfigFile(APIJSON_ALLPARAM);
				System.out.println("Given step completed with these Values"+APIJSON);
	}	
	
	@When("^User request a \"([^\"]*)\" call$")
	public void user_request_a_call(String METHODNAME) throws Throwable {
		System.out.println("Method Name ="+METHODNAME);
		if(METHODNAME.toUpperCase().contains("GET"))
			APIResponse = apiobj.GetMethod(APIJSON);
		
		if(METHODNAME.toUpperCase().contains("POST"))
			APIResponse = apiobj.PostMethod(APIJSON);
	}


	@Then("^I will receive response code as \"([^\"]*)\"$")
	public void i_will_receive_response_code_as(String ExpectedStatusCode) throws Throwable {
		String ActualStatusCode = APIResponse.split("_")[0].trim();
		softly.assertThat(ActualStatusCode.contains(ExpectedStatusCode)).isTrue();
		softly.assertAll();
	}

	@Then("^response description contains \"([^\"]*)\"$")
	public void response_description_contains(String ExpectedResponseDescription) throws Throwable {
	    String ActualResponseDescrition = APIResponse.split("_")[1].trim();
	    softly.assertThat(ActualResponseDescrition.contains(ExpectedResponseDescription)).isTrue();
	    softly.assertAll();	    
	}	
}
