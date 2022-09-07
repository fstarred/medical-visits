package it.stellato.cgm;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import it.stellato.cgm.entity.Patient;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.Month;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class PatientsResourceTest {

    @Test
    public void testCreatePatientEndpoint() {

        final var patient = new Patient();
        patient.setName("Mario");
        patient.setSurname("Rossi");
        patient.setBirthDate(LocalDate.of(1979, Month.APRIL, 1));
        patient.setSocialSecurityNumber("AA123456Z");

        given()
                .contentType(ContentType.JSON)
                .and()
                .body(patient)
                .when()
                .post("/patients")
                .then()
                .statusCode(CoreMatchers.is(Response.Status.OK.getStatusCode()));
    }

}