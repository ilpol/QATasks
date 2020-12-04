Feature: Task20

  Scenario: Check basket
    When we open shop
    And add 3 items in basket
    And checkout basket
    Then we empty basket