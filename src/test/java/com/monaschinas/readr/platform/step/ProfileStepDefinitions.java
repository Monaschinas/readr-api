package com.monaschinas.readr.platform.step;

import com.monaschinas.readr.platform.profiling.resource.CreateProfileResource;
import com.monaschinas.readr.platform.publishing.resource.CreateBookResource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ProfileStepDefinitions {
    private final TestRestTemplate testRestTemplate = new TestRestTemplate();

    @LocalServerPort
    private int randomServerPort;

    private String endpointPath;

    private ResponseEntity<String> responseEntity;

    @Given("The Endpoint {string} is available")
    public void theEndpointIsAvailable(String endpointPath) {
        this.endpointPath = String.format(endpointPath, randomServerPort);
    }

    @When("A Post Request is sent with values {string}, {string}, {long}")
    public void aPostRequestIsSentWithValues(String firstName, String lastName, Long roleId) {
        CreateProfileResource resource = new CreateProfileResource()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withRoleId(roleId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateProfileResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
    }

    @When("A Post Request is sent with values {string}, {long}, {string}, {string}, {long}, {long}, {long}")
    public void aPostRequestIsSentWithValues(String title, Long AuthorId, String synopsis, Date publishAt, Long languageId, Long bookStatusId, Long sagaId) {
        CreateBookResource resource = new CreateBookResource()
                .withTitle(title)
                .withAuthorId(AuthorId)
                .withSynopsis(synopsis)
                .withPublishedAt(publishAt)
                .withLanguageId(languageId)
                .withLanguageId(languageId)
                .withBookStatusId(bookStatusId)
                .withSagaId(sagaId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateBookResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
    }
}
