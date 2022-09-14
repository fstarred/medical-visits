package it.stellato.cgm;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import it.stellato.cgm.dao.PatientRepository;
import it.stellato.cgm.entity.Patient;
import org.bson.types.ObjectId;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class PatientsResourceTest {

    @InjectMock
    PatientRepository repository;

    @Inject
    Logger logger;

    @Test
    public void testCreatePatientEndpoint() {

        final var patient = new Patient();
        patient.setName("Mario");
        patient.setSurname("Rossi");
        patient.setSocialSecurityNumber("AA123456Z");

        Mockito.when(repository.add(Mockito.isA(Patient.class))).then(i -> {
            logger.info("mocking patient");
            patient.setId(ObjectId.get());
            return patient;
        });

        given()
                .contentType(ContentType.JSON)
                .and()
                .body(patient)
                .when()
                .post("/patients")
                .then()
                .statusCode(CoreMatchers.is(Response.Status.OK.getStatusCode()))
                ;
    }

}