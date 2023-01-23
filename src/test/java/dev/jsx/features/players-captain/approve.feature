Feature: Captain can filter which player will make it onto their team

  Background:
  Given The player logged in is a captain
  Given The player is on home page

  Scenario: The Captain can approve of player
    Given The player is on Approve or Deny Team Requests Page
    When The player clicks on approve
    Then An alert appears saying the player has been approved
    Then The alert confirmation disappears and the captain sees the request status

