@web @smoke @technical @happy
Feature: The Internet technical lab
  Technical web examples for dropdown, iframe, wait, and introductory shadow DOM.

  Scenario: Technical lab home smoke
    Given I open The Internet technical lab home page
    Then I should see technical lab heading "Welcome to the-internet"

  Scenario: Dropdown selection
    Given I open The Internet dropdown page
    When I choose dropdown option "Option 2"
    Then I should see selected dropdown option "Option 2"

  Scenario: iframe editor interaction
    Given I open The Internet iframe page
    When I type "Training iframe text" into the iframe editor
    Then iframe editor should contain "Training iframe text"

  Scenario: Explicit wait with dynamic loading
    Given I open The Internet dynamic loading page
    When I trigger dynamic loading
    Then I should see dynamic loading result "Hello World!"

  Scenario: Introductory shadow DOM
    Given I open The Internet shadow DOM page
    Then I should read slotted shadow text containing "different text"
