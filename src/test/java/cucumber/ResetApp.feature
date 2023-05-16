Feature: Verify that app can be resseted to initial cart state

  Background:
    Given Landed on the website

  @Regression
  Scenario Outline: Positive test of resetting an application
    Given Logged in with <username> and <password>
    When Product with name <productName> is added to Cart
    And Menu is opened
    Then "Reset App" field is used and product with <productName> in cart is deleted
    Examples:
      | username      | password     | productName       |  |  |  |
      | standard_user | secret_sauce | Sauce Labs Bolt T |  |  |  |
      | problem_user  | secret_sauce | Sauce Labs Onesie |  |  |  |