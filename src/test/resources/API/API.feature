Feature: Search API check
  Verify Search Service functionality

@GETAPIFAILTEST
  Scenario: Invalid GET API
     Given Use "GET_PARAMETER" Parameter for API Call
     When User request a "GET" call 
     Then I will receive response code as "400"
     	And response description contains "FailedRD" 	 
    	
@GETAPITEST
  Scenario: Valid positive GET API
     Given Use "GET_PARAMETER" Parameter for API Call
     When User request a "GET" call 
     Then I will receive response code as "200"
     	And response description contains "employee"    


@POSTAPITEST
  Scenario: Valid positive POST API
     Given Use "POST_PARAMETER" Parameter for API Call
     When User request a "POST" call 
     Then I will receive response code as "200"
     	And response description contains "status"
     	And response description contains "success"  	