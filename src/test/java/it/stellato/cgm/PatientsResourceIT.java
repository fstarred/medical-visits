package it.stellato.cgm;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.http.ContentType;
import it.stellato.cgm.entity.Patient;
import it.stellato.cgm.profile.IntegrationProfile;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

@QuarkusIntegrationTest
@TestProfile(IntegrationProfile.class)
public class PatientsResourceIT {

    @Test
    public void testCreatePatientEndpoint() {

        final var patient = new Patient();
        patient.setName("Mario");
        patient.setSurname("Rossi");
        patient.setSocialSecurityNumber("AA123456Z");

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
