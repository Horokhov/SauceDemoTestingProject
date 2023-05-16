Feature: Verify that product can be removed from cart

Background:
  Given Landed on the website

  @Regression
  Scenario Outline: Positive test of removing product from cart
    Given Logged in with <username> and <password>
    When Product with name <productName> is added to Cart
    Then Product with name <productName> is successfully removed from Cart
    Examples:
      | username      | password     | productName             |  |  |  |
      | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt |  |  |  |
      | problem_user  | secret_sauce | Sauce Labs Onesie       |  |  |  |