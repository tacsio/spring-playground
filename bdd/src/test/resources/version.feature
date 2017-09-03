Feature: The version can be retrived
  I want to make rest requests

  Scenario: Client makes call to GET /version
    When The client calls /version
    Then The client receives status code of 200
    And The client receives server version "1.0"