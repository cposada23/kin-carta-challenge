Feature: Amazon search
  As a Customer when I search for Alexa, I want to see if the third option on the second page is
  available for purchase and can be added to the cart.

  Scenario: Search for Alexa
    Given the user navigates to "https://www.amazon.com"
    When the user searches for "Alexa"
    And navigates to the second page
    And selects the third item
    Then the user is able to add the item to the cart
