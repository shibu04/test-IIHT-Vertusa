Feature: Patient Registration Form Validation of Mandatory Fields

  Background: User is on the Registration Page
    Given the user logs into the healthcare portal
    And the user is on the dashboard
    When the user clicks on Patient in the left side menu
    Then the Patient Registration page is displayed
    And the user navigates to the Register Patient page

  #		Scenario: TC-01 Verify the First Name field error message
  #		When the user attempts to submit the registration form with the First Name field empty
  #		Then the First Name field should display the error message "First Name is required"
  #
  #		Scenario: TC-02 Verify the Last Name field error message
  #		When the user attempts to submit the registration form with the Last Name field empty
  #		Then the Last Name field should display the error message "Last Name is required"
  #
  Scenario Outline: TC-01 Verify all fields related to registration are present and required on the registration form
    When the user attempts to submit the registration form with all mandatory fields empty
    Then the '<Field Name>' field should display the error message '<Error Message>'

    Examples: 
      | Field Name   | Error Message             |
      | First Name   | First Name is required    |
      | Last Name    | Last Name is required     |
      | Phone number | Primary Phone is required |
      | Gender       | Gender is required        |

   Scenario Outline: User navigates from Basic Info to Insurance and verifies mandatory fields
    # User navigates to the Insurance tab
    Given the user is on the Basic Info tab
    When the user clicks on the Insurance tab
    Then the user should be redirected to the Insurance page

    # User tries to submit an empty insurance form to trigger validation errors
    When the user attempts to submit the insurance form with all mandatory fields empty
    Then the '<Field Name>' field should display the error message '<Error Message>'

    Examples: 
      | Field Name        | Error Message                |
      | Card Number       | Card Number is required      |
      | Insurance Number  | Insurance Number is required |
      | Facility Code     | Facility Code is required    |