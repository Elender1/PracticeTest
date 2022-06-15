@test
Feature: Sample Test by Pittala

  Scenario: Costly Item to cart
    Given the automation practice site launch
    Then verify the page title to be My Store
    When I click on the Dresses tab
    Then verify the page title to be Dresses - My Store
    When i add costliest item to cart
    And I proceed to checkout
    Then verify the page title to be Order - My Store
    And check the total price in the order summary page
