Feature: Bing Search Feature
  Verify if user is able to Search on Bing

@Sprint1B @TestCase1
  Scenario: Valid search
     Given user is on "BingSearchPage"
    When user navigates to "Bing"
    And user enters "Shivam Maralay" in "Bing"
    And user enters EnterKey
    Then NewPage opens


@Sprint1B @TestCase2	
  Scenario: Valid search
     Given user is on "BingSearchPage"
    When user navigates to "Bing"
    And user enters "NidhiShree Maralay" in "Bing"
    And user enters EnterKey
    Then NewPage opens
 
 @Sprint1B @TestCase3   
 Scenario: Valid search
     Given user is on "BingSearchPage"
    When user navigates to "Bing"
    And user enters "Other Feature Resource" in "Bing"
    And user enters EnterKey
    Then NewPage opens
  