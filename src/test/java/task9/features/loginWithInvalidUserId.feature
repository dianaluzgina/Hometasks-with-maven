Feature:  Sign in failed because of invalid user id

  Scenario Outline: Login with invalid user id
    Given browser started
    And mail.ru page is loaded
    When I type "<name>" to id input
    And I select "<domain>" in dropdown list
    And I click password button
    Then I see "<errorMessage>"
    Examples:
      | name            | domain   |errorMessage      |
      |ufrdrsews89776553| @list.ru |Неверное имя ящика|
