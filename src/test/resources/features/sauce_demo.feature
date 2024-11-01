Feature: Sauce Demo Shopping Cart

  Scenario: User adds the highest priced item to the cart
    Given I navigate to the Sauce Demo login page
    When I log in with username "standard_user" and password "secret_sauce"
    And I select the highest price item
    Then the item should be in the cart