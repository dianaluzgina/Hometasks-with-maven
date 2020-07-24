Feature: Sign in failed because of invalid password

  Scenario Outline: Login with invalid password
    Given browser started
    And mail.ru page is loaded
    When I type "<name>" to id input
    And I select "<domain>" in dropdown list
    And I click password button
    And I type "<password>" to password input
    And I click submit button
    Then I see "<errorMessage>"
    Examples:
      | name          | domain   | password | errorMessage          |
      |be.always.happy| @bk.ru   | tygygftd |Неверное имя или пароль|