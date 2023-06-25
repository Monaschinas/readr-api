Feature: Sagas Adding
  As a Developer
  I want to add Sagas through API
  So that It can be available to applications.

  Background:
    Given The Endpoint "http://localhost:%d/api/v1/students" is available
  @student-adding
  Scenario: Add Saga with unique title
    When A Post Request is sent to saga with values "Vind Land Saga", "Un verdadero guerrero no necesita una espada"
    Then A Response is received with Status 201
    And An Saga Resource is included in Response Body, with values "En Emision"

  @student-duplicated
  Scenario: Add Saga with existing title
    Given An Saga Resource with values "Vind Land Saga" is already stored
    When A Post Request is sent to saga with values "Vind Land Saga", "Un verdadero guerrero no necesita una espada"
    Then A Response is received with Status 400
    And A Message is included in Response Body, with value "Not all constraints satisfied for Saga: An Saga with the same title already exists."