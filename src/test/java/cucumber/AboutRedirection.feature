
Feature: Redirection to the main website validation when clicking on "About" field

  Background:
    Given Landed on the website

  @Regression
  Scenario Outline: Positive test of redirection to the main website
    Given Logged in with <username> and <password>
    When Menu is opened
    And "About" field is clicked
    Then Redirected to the main website saucelabs.com
    Examples:
      | username      | password     |  |  |  |  |
      | standard_user | secret_sauce |  |  |  |  |
      | problem_user  | secret_sauce |  |  |  |  |