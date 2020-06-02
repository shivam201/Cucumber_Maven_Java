Feature: Cosmos  DB Test
  Verify CRUD operation in COSMOS DB

@Sprint2B 
  Scenario: Valid search
     Given user is on "BingSearchPage"
    When user navigates to "Bing"
    And user enters "Shivam Maralay" in "Bing"
    And user enters EnterKey
    Then NewPage opens


@Sprint2B @TestCase2	
  Scenario: Valid search
     Given user is on "BingSearchPage"
    When user navigates to "Bing"
    And user enters "NidhiShree Maralay" in "Bing"
    And user enters EnterKey
    Then NewPage opens
 
 @Sprint2B @TestCase3   
 Scenario: Valid search
     Given user is on "BingSearchPage"
    When user navigates to "Bing"
    And user enters "Other Feature Resource" in "Bing"
    And user enters EnterKey
    Then NewPage opens
  