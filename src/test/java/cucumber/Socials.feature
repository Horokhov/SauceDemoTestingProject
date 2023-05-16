Feature: Verify that social links are not broken


  @Main
  Scenario Outline: Positive test of identifying broken social links
    Given Landed on the website
    When Logged in with <username> and <password>
    Then Social links are scanned for broken links
    Examples:
      | username      | password     |  |  |  |  |
      | standard_user | secret_sauce |  |  |  |  |
      | problem_user  | secret_sauce |  |  |  |  |