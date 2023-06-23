Feature: book Adding
  As a developer
  I want to add books through the API
  To enable management of books and relationships in our application.

  Background:
    Given The Endpoint "http://localhost:%d/api/v1/profiling/profiles" is available

  @profile-adding
  Scenario: Add Book with unique title
    When A Post Request is sent with values "Vind Land Saga", 1, "The series centers on Thorfinn, a young Viking who wishes to avenge his father, Thors, who was killed by his current superior, Askeladd", "2023-06-23T20:52:27.935Z", 1, 1, 1
    Then A Response is received with Status 201
    And An Student Resource is included in Response Body, with values "Vind Land Saga", 1, "The series centers on Thorfinn, a young Viking who wishes to avenge his father, Thors, who was killed by his current superior, Askeladd", "2023-06-23T20:52:27.935Z", 1, 1, 1
  @student-duplicated
  Scenario: Add Book with existing title
    Given An Student Resource with values "Vind Land Saga", 1, "The series centers on Thorfinn, a young Viking who wishes to avenge his father, Thors, who was killed by his current superior, Askeladd", "2023-06-23T20:52:27.935Z", 1, 1, 1 is already stored
    When A Post Request is sent with values "Vind Land Saga", 1, "The series centers on Thorfinn", "2023-06-23T20:52:27.935Z", 2, 2, 2
    Then A Response is received with Status 400
    And A Message is included in Response Body, with value "Not all constraints satisfied for Books: An book with the same title already exists."

