Feature: Sign in

  Scenario Outline: Success login
    Given browser started
    And mail.ru page is loaded
    When I type "<name>" to id input
    And I select "<domain>" in dropdown list
    And I click password button
    And I type "<password>" to password input
    And I click submit button
    Then I see my "<name>" "<domain>"
    Examples:
      | name          | domain  | password   |
      |be.always.happy| @bk.ru  |P8IPS3)mptuo|
