package com.monaschinas.readr.platform.step;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monaschinas.readr.platform.publishing.domain.model.SagaStatus;
import com.monaschinas.readr.platform.publishing.mapping.SagaStatusMapper;
import com.monaschinas.readr.platform.publishing.resource.CreateSagaStatusResource;
import com.monaschinas.readr.platform.publishing.resource.SagaStatusResource;
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
public class SagaStatusDefinitions {

    private final SagaStatusMapper sagaStatusMapper;
    private final TestRestTemplate testRestTemplate = new TestRestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @LocalServerPort
    private int randomServerPort;

    private String endpointPath;

    private ResponseEntity<String> responseEntity;

    public SagaStatusDefinitions(SagaStatusMapper sagaStatusMapper) {
        this.sagaStatusMapper = sagaStatusMapper;
    }

    @Given("The Endpoint {string} is available")
    public void theEndpointIsAvailable(String endpointPath) {
        this.endpointPath = String.format(endpointPath, randomServerPort);
    }

    @When("A Post Request is sent to saga status with values {string}")
    public void aPostRequestIsSentToSagaStatusWithValues(String name) {
        CreateSagaStatusResource resource = new CreateSagaStatusResource()
                .withName(name);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateSagaStatusResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
    }
    @Given("An Saga Status Resource with values {string} is already stored")
    public void anSagaStatusResourceWithValuesIsAlreadyStored(String name) {
        CreateSagaStatusResource resource = new CreateSagaStatusResource()
                .withName(name);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateSagaStatusResource> request = new HttpEntity<>(resource, headers);
        testRestTemplate.postForEntity(endpointPath, request, String.class);
    }

    @Then("A Response is received with Status {int}")
    public void aResponseIsReceivedWithStatus(int status) {
        assertEquals(status, responseEntity.getStatusCode().value());
    }

    @And("An Saga Status Resource is included in Response Body, with values {string}")
    public void anSagaStatusResourceIsIncludedInResponseBodyWithValues(String name) throws IOException {
        SagaStatus sagaStatus = objectMapper.readValue(responseEntity.getBody(), SagaStatus.class);
        SagaStatusResource resource = sagaStatusMapper.toResource(sagaStatus);
        assertEquals(name, resource.getName());
    }

    @And("A Message is included in Response Body, with value {string}")
    public void aMessageIsIncludedInResponseBodyWithValue(String message) throws IOException {
        ErrorResponse errorResponse = objectMapper.readValue(responseEntity.getBody(), ErrorResponse.class);
        assertEquals(message, errorResponse.getDetailMessageCode());
    }
}

