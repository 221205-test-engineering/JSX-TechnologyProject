Feature: Admin adds a season

  Background:
    Given The user is logged in as admin
    Given The user is on homepage

    Scenario: The admin adds a new season
      When The user clicks on Add Season
      Then The user is on Season Scheduler
      When User types "Winter 2023 Outstanding Achiever Tournament" in season name
      When User clicks on submit button
      Then The new season has been added
      When User clicks back link
      Then User returns to homepage
      When User clicks on View Season
      Then The user is on Season page and can view the seasons.

