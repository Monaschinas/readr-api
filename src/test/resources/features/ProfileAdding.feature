Feature: profile Adding
  As a Developer
  I want to add profiles through API
  To enable the management of profiles and roles in our application

  Background:
    Given The Endpoint "http://localhost:%d/api/v1/profiling/profiles" is available

    @profile-adding
    Scenario: Add profile with reader role
      When A Post Request is sent with values "Johan", "Huanca", 1
      Then A status 201 response is received and the profile should be created with the reader role
      And A profile resource is included in the response body, with values "Johan", "Huanca",1
    @profile-adding
    Scenario: Add profile with author role
      When A Post Request is sent with values "Luis", "Reyes", 2
      Then A status 201 response is received and the profile should be created with the author role
      And A profile resource is included in the response body, with values "Luis", "Reyes", 2
    @profile-invalid
    Scenario: Attempting to create a new profile with an invalid role
      Given That there are only the roles "reader", "author"
      When A Post Request is sent with values "Jhon", "Smith", 3
      Then You receive error 500



