Feature: Verify Login Functionality

  Background: User navigates to the login page
    Given the user has opened the browser
    And the user is on the sign in page "https://healthapp.yaksha.com/"

  Scenario: TC-01 Check the username button clickable or not
    When the user clicks on the username button
    Then the username button should be clickable
