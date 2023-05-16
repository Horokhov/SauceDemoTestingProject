Feature: Verify user can log out from website



  @Regression
  Scenario Outline: Positive test of logging out
    Given Landed on the website
    When Logged in with <username> and <password>
    And Menu is opened
    Then Log out using "Log out" field in menu
    Examples:
      | username      | password     |  |  |  |  |
      | standard_user | secret_sauce |  |  |  |  |
      | problem_user  | secret_sauce |  |  |  |  |