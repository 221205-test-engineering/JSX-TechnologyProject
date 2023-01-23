Feature: A user can view their game stats

  Scenario Outline: User checks their game stats
    Given The user is on login page
    When  The user types "<username>" into username input
    When The user types "<password>" into password input
    When The employee clicks on the login button
    Then User is on homepage
    When User clicks on Games
    Then User is on Game Scheduled page with stats
    When User click on show Referee
    Then User can see the Referee for that game

  Examples:

    | username    | password  |
    | cindy101    | pass123   |
    | Candice202   | pass123  |
    | odowellf  | MOXvFNcR |