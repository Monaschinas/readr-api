Feature: Saga Statuses Adding
  As a Developer
  I want to add Saga Statuses through API
  So that It can be available to applications.

  Background:
    Given The Endpoint "http://localhost:%d/api/v1/students" is available
  @student-adding
  Scenario: Add Saga Status with unique name
    When A Post Request is sent to saga status with values "En Emision"
    Then A Response is received with Status 201
    And An Saga Status Resource is included in Response Body, with values "En Emision"

  @student-duplicated
  Scenario: Add Saga Status with existing name
    Given An Saga Status Resource with values "En Emision" is already stored
    When A Post Request is sent to saga status with values "En Emision"
    Then A Response is received with Status 400
    And A Message is included in Response Body, with value "Not all constraints satisfied for Saga Status: An Saga Status with the same name already exists."