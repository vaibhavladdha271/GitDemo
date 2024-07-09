@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

  @tag1
  Scenario: Submit the order
    Given Logged in with <username> and <password>
    And some other precondition
    When I complete action
    And some other action
    And yet another action
    Then I validate the outcomes
    And check more outcomes

Examples: 
      | username  			 | password | status  |
      | shetty@gmail.com |  "IamKing@000" | success |
      | name2 |     7 | Fail    |

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    