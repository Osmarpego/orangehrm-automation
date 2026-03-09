Feature: Employee management in OrangeHRM

  Scenario: Add a new employee and validate it in Directory
    Given the user opens OrangeHRM login page
    When the user logs in with admin credentials
    And the user navigates to the PIM module
    And the user adds a new employee with first name "Laura" and last name "Ibanez"
    And the user uploads a profile photo
    And the user saves the employee information
    And the saved employee information should be displayed correctly
    And the user navigates to the Directory module
    And the user searches the employee by full name
    Then the employee information should be displayed correctly in Directory