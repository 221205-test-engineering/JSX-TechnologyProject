Feature: Admin can promote a player

  Background:
    Given The user is logged in as admin
    Given The user is on homepage

  Scenario: Admin promotes a player as referee
    When User clicks on Manage User Roles
    Then User is on Manager View page
    When User clicks on a Promote to Referee on a player
    Then The page updates and the player role is updated

  Scenario: Admin promotes a player as admin
    When User clicks on Manage User Roles
    Then User is on Manager View page
    When User clicks on a Promote to Admin on a player
    Then The page updates and the player role is updated



