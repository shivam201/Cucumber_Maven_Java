Feature: Google Search Feature
  Verify if user is able to Search on Google

@Sprint1B
  Scenario: Valid search
    Given user is on "GoogleSearchPage"
    When user navigates to "Google"
    And user enters "Shivam Maralay" in "Google"
    And user enters EnterKey
    Then NewPage opens
    
@Sprint1B
  Scenario: Valid search
    Given user is on "GoogleSearchPage"
    When user navigates to "Google"
    And user enters "Card" in "Google"
    And user enters EnterKey
    Then NewPage opens