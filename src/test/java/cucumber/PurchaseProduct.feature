
Feature: Purchase the product from saucedemo.com website

  Background:
    Given Landed on the website

  @Regression
  Scenario Outline: Positive test of purchasing a product
    Given Logged in with <username> and <password>
    When Product with name <productName> is added to Cart
    And Checkout <productName> and submit an order using <name>, <surname> and <zip>
    Then "Thank you for your order!" header and "Your order has been dispatched, and will arrive just as fast as the pony can get there!" paragraph are displayed
    Examples:
      | username      | password     | productName             | name | surname  | zip   |
      | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Max  | Horokhov | 79020 |
      | problem_user  | secret_sauce | Sauce Labs Onesie       | Max  | Horokhov | 79020 |