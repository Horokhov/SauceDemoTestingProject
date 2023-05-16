Feature: Verify that all catalogue filters are displayed and functional



  @Regression
  Scenario Outline: Positive test of catalogue filters
    Given Landed on the website
    When Logged in with <username> and <password>
    Then Switched every filter "Name (Z to A)", "Name (A to Z)", "Price (low to high)", "Price (high to low)" and verified that it is displayed
    Examples:
      | username      | password     |  |  |  |  |
      | standard_user | secret_sauce |  |  |  |  |
      | problem_user  | secret_sauce |  |  |  |  |