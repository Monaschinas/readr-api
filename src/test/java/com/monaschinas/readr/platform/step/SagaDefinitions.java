package com.monaschinas.readr.platform.step;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monaschinas.readr.platform.publishing.domain.model.Saga;
import com.monaschinas.readr.platform.publishing.mapping.SagaMapper;
import com.monaschinas.readr.platform.publishing.resource.CreateSagaResource;
import com.monaschinas.readr.platform.publishing.resource.SagaResource;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest
public class SagaDefinitions {

    private final SagaMapper sagaMapper;
    private final TestRestTemplate testRestTemplate = new TestRestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @LocalServerPort
    private int randomServerPort;

    private String endpointPath;

    private ResponseEntity<String> responseEntity;

    public SagaDefinitions(SagaMapper sagaMapper) {
        this.sagaMapper = sagaMapper;
    }

    @Given("The Endpoint {string} is available")
    public void theEndpointIsAvailable(String endpointPath) {
        this.endpointPath = String.format(endpointPath, randomServerPort);
    }

    @When("A Post Request is sent to saga with values {string}, {string}")
    public void aPostRequestIsSentToSagaWithValues(String title, String synopsis) {
        CreateSagaResource resource = new CreateSagaResource()
                .withTitle(title)
                .withSynopsis(synopsis);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateSagaResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
    }

    @Then("A Response is received with Status {int}")
    public void aResponseIsReceivedWithStatus(int status) {
        assertEquals(status, responseEntity.getStatusCode().value());
    }

    @And("An Saga Resource is included in Response Body, with values {string}")
    public void anSagaResourceIsIncludedInResponseBodyWithValues(String title) throws IOException {
        Saga saga = objectMapper.readValue(responseEntity.getBody(), Saga.class);
        SagaResource resource = sagaMapper.toResource(saga);
        assertEquals(title, resource.getTitle());
    }

    @And("A Message is included in Response Body, with value {string}")
    public void aMessageIsIncludedInResponseBodyWithValue(String message) throws IOException {
        ErrorResponse errorResponse = objectMapper.readValue(responseEntity.getBody(), ErrorResponse.class);
        assertEquals(message, errorResponse.getDetailMessageCode());
    }

    @Given("An Saga Resource with values {string} is already stored")
    public void anSagaResourceWithValuesIsAlreadyStored(String title) {
        CreateSagaResource resource = new CreateSagaResource()
                .withTitle(title);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateSagaResource> request = new HttpEntity<>(resource, headers);
        testRestTemplate.postForEntity(endpointPath, request, String.class);
    }
}
