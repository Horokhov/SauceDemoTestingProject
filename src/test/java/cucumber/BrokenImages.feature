Feature: Validation that images in the product catalogue are not broken



  @Regression
  Scenario Outline: Negative test of broken images
    Given Landed on the website
    When Logged in with <username> and <password>
    Then Verified that images are displayed on the page in Base64 format
    Examples:
      | username      | password     |  |  |  |  |
      | standard_user | secret_sauce |  |  |  |  |
      | problem_user  | secret_sauce |  |  |  |  |