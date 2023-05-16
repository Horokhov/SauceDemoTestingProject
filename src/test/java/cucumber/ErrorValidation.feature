Feature: Validation errors during loggination

  @Regression
  Scenario Outline: Negative tests of loggination
    Given Landed on the website
    When Logged in with <username> and <password>
    Then Unique error <message> is displayed
    Examples:
      | username        | password     | message                                                                   |  |  |  |
      | invalid         | invalid      | Epic sadface: Username and password do not match any user in this service |  |  |  |
      |                 |              | Epic sadface: Username is required                                        |  |  |  |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |  |  |  |