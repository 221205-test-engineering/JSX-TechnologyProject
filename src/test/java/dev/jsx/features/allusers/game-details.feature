Feature: user can see details of games
  Scenario Outline: user views games details
    Given the user logs in using the username "<username>" and  the password "<password>"
    And   the user is on the homepage
    When  the user clicks on Games
    Then  the user should be on the Game Schedules page
    And   the user should see details of games

    Examples:
      | username    | password    |
      | gatorFan99  | chomp!!     |
      | mandy101    | pass123     |
      | Candice202    | pass123     |
      | eegdell0    | DyAU3y5hLA  |
