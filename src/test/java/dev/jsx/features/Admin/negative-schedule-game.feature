Feature: Admin attempts to schedule a game

  Background:
    Given The user is logged in as admin
    Given The user is on homepage

  Scenario: Admin schedules game series with only the defaults
    When The user clicks on Schedule game
    Then The user is on Game Scheduling Form
    When User clicks schedule button
    Then User gets a popup of empty fields

  Scenario: Admin schedule game series with no date selected
    When The user clicks on Schedule game
    Then The user is on Game Scheduling Form
    When User selects a venue
    When User selects a season basketball
    When User selects Sport as "basketball"
    When User selects a Home Team
    When User selects an Away Team
    And User clicks schedule button
    Then User gets a popup of empty date field

  Scenario: Admin schedule game series with a past date selected
    When The user clicks on Schedule game
    Then The user is on Game Scheduling Form
    When User selects a venue
    When User selects a past date
    When User selects a season basketball
    When User selects Sport as "basketball"
    When User selects a Home Team
    When User selects an Away Team
    And User clicks schedule button
    Then User gets a popup of selecting a future date


    Scenario: Admin schedule game series with no sports selected
      When The user clicks on Schedule game
      Then The user is on Game Scheduling Form
      When User selects a venue
      When User selects a time and date
      When User selects a season basketball
      And User clicks schedule button
      Then User gets a popup of selecting sports field





