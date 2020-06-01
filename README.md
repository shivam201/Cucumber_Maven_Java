# Cucumber_Maven_Java
Cucumber Maven Java Project for Beginner / Intermediate and Advance Level User 
# Author - Shivam Maralay
# Last Commit - 1-June-2020
# Agenda - BDD(Cucumber) framework for UI and API based on Selenium and Rest Assured for Java project 


# Pre-requisite:
1. Java to be installed and should be present
	-	JAVA_HOME [ Path till JDK should be created in User/System	Variables ]
	-	Java\bin path should be present in PATH system Variables.
	
2. Maven to be installed and should be present
	-	MAVEN_HOME [ Path till Maven directory]
	-	Maven\bin path should be present in PATH system Variables.

3. eclipse plugin not mandatory but to show the color highlighted for gherkins
http://cucumber.github.com/cucumber-eclipse/update-site


# Framework Distribution - Framework is divided in 2 folder structure
1. src/test/java
	-	genericfunctions Folder
				-	API_GenericFun.java [All the function about GET/POST , read query param,Form param 
					,header param are writting in this]
				-	DB_GenericFun.java [As of now its commented]
				- 	GenericFunction.java [Read the Key value from Environment Property File]
				-	SeleniumGenericFun.java	[teardown , takescreenshot , highlight elements]
	
	-	runner Folder
				-	TestRunner.java	[This is the driver file - All the Tags , feature folder and glue folder is 
					present in this, Also the information about Reporting Plugin]
	
	-	stepDefinition Folder		
				- 	AbstractDriver.java [File for selecting the driver and its configuration]
				-	TestAPI.java [A Sample step definition for API - more file can be written - 
					try to reuse these functions] more description below on how to write ...
				-	TestUI.java [A Sample Java file for UI Cases] more description below on how to write ...

2. src/test/resources
	-	API Folder
		-	FeatureAPIFile1.feature
		-	FeatureAPIFile2.feature
	
	-	DATABASE Folder
		-	DATABASE.feature
	
	-	LOGIN Folder
		-	Login.feature
	
	-	LUMINAPORTAL Folder
		-	E2E.feature
		-	Functionality.feature
		-	UI.feature
		
		

# More Description :-
1. TestAPI.java - for Demo I used - http://dummy.restapiexample.com/
	-	Try to create n number of test case w.r.to feature file rather than writting more code in step definition folder
	- 	Functions are written in the pattern where they can be reused easily
		for ex :- 3 scenario but same function is reused [GET/POST/Failure]
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
	-	Status Report will be stored in target folder

2. TestUI.java - 
	-	While creating new File if you want to use same driver again n again instead of closing the browser, 
		follow these Steps.
		a. extends AbstractDriverClass
		b. create a static Webdriver driver - So that any session change in driver will not change the driver
		c. driver = getDriver(BrowserName); call a instance and pass which Browser you wish to use.
	-	Use Softly Match[AssertJ] - instead of JUNIT assert to bypass failing step and complete the execution.
		for this follow these steps.
		a. SoftAssertions softly = new SoftAssertions(); create an obj
		b. softly.assertThat(ActualStatusCode.contains(ExpectedStatusCode)).isTrue();
		c. softly.assertAll();
	-	Use Takescreenshot before AssertThat - Screenshot will be having Name as Scenario name with no space.png

# Execution :-
1. Feature File can be run directly - Cucumber.feature file from IDE
2. Runner Can be used to run multiple Feature file at 1 go based on the Tags.
3. From CMD
	option 1 - mvn clean test [which tag we have to run should be mentioned in Runner file]
	option 2 - mvn clean test
	Single Feature Runner -
		-Dcucumber.options="feature file path"
		-Dcucumber.options="feature file path:5"  // 5 - Line number //

	Tag Wise runner - 
		-Dcucumber.options="--tags @Smoke"  // @Smoke is mentioned in top of feature file//
		-Dcucumber.options="--tags @FuntionalTest" 
