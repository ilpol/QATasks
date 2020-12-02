Feature: Task20

  Scenario: Check basket
    When we open shop
    And add items in basket
    And checkout basket
    Then we empty basket