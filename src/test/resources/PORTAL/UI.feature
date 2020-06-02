Feature: Lumina Portal UI Test 
  Verify Lumina Portal for the UI Elements

@Sprint1A	
  Scenario: Check COMPANY Portal Page
    Given User Open "Chrome" Browser 
    When User Navigate to "CompanyPortal"
    Then "CompanyPortal" Opens
 
   
@Sprint1A @TestCase8453
  Scenario: Check Company Header
    Given "Portal" Opens
    When User Navigate to "Portal"
    Then "COMPANY_HEADER_LOGO" and "COMPANY_HEADER_LOG_CLASS" and "COMPANY_HEADER_LOGIN_IMG" and "COMPANY_HEADER_SEARCH_CLASS" is Displayed
    And "COMPANY_HEADER_SHIPMENTS_TEXT" and "COMPANY_HEADER_TRACKING_TEXT" and "COMPANY_HEADER_DESIGNPRINT_TEXT" and "COMPANY_HEADER_OFFICES_TEXT" and "COMPANY_HEADER_SUPPORT_TEXT" in Span "COMPANY_HEADER_SPAN_CLASS" is displayed