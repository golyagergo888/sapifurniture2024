# Created by Gólya Gergő at 2025. 01. 07.
Feature: Check if the find all endpoints works
  As furniture tool user I want to be able to see all the bunk beds

  Scenario: One new element in the database
    Given that we have the following furniture bunk beds:
    | height    | width       | depth       | material  | type      |
    | 100 		| 150 		  | 120 		| WOOD      | TWO_LEVEL |
    When I invoke the furniture bunk bed all endpoint
    Then I should get the material "WOOD" for the last bunk bed in the response