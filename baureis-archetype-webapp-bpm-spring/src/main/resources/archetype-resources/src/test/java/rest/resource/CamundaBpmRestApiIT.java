package ${package}.rest.resource;

import static com.jayway.restassured.RestAssured.basic;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import java.io.File;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import ${package}.bpm.constant.TravelRequestProcessElements;
import ${package}.test.data.IntegrationTestConstants;

@RunWith(Arquillian.class)
@RunAsClient
public class CamundaBpmRestApiIT {

    public CamundaBpmRestApiIT() {
        RestAssured.baseURI = IntegrationTestConstants.SERVER_URL;
        RestAssured.port = IntegrationTestConstants.PORT;
        RestAssured.basePath = IntegrationTestConstants.BASE_PATH_REST_API;
    }

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        File webXml = new File(IntegrationTestConstants.TEST_DEPLOYMENT_DESCRIPTOR);
        WebArchive archive =
                ShrinkWrap.create(WebArchive.class, IntegrationTestConstants.TEST_APPLICATION_NAME + ".war")
                        .addPackages(true, "${package}").setWebXML(webXml);
        return archive;
    }

    @Test
    public void testAuthorizedAccessToEngine() {
        RestAssured.authentication =
                basic(IntegrationTestConstants.CAMUNDA_BPM_USERNAME, IntegrationTestConstants.CAMUNDA_BPM_PASSWORD);

        given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().get("/engine").then()
                .statusCode(HttpStatus.SC_OK).body("[0].name", equalTo("default"));
    }

    @Test
    public void testAuthorizedAccessToProcessDefinition() {
        RestAssured.authentication =
                basic(IntegrationTestConstants.CAMUNDA_BPM_USERNAME, IntegrationTestConstants.CAMUNDA_BPM_PASSWORD);

        given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().get("/process-definition/count").then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testAuthorizedAccessToEngineProcessDefinition() {
        RestAssured.authentication =
                basic(IntegrationTestConstants.CAMUNDA_BPM_USERNAME, IntegrationTestConstants.CAMUNDA_BPM_PASSWORD);

        given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when()
                .get("/engine/default/process-definition/count").then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testUnauthorizedAccessToEngine() {
        RestAssured.authentication =
                basic(IntegrationTestConstants.CAMUNDA_BPM_USERNAME, IntegrationTestConstants.CAMUNDA_BPM_PASSWORD
                        + "wrong pwd");

        given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().get("/engine").then()
                .statusCode(HttpStatus.SC_OK).body("[0].name", equalTo("default"));
    }

    @Test
    public void testUnauthorizedResourceProcessDefinition() {
        RestAssured.authentication =
                basic(IntegrationTestConstants.CAMUNDA_BPM_USERNAME, IntegrationTestConstants.CAMUNDA_BPM_PASSWORD
                        + "wrong pwd");

        given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().get("/process-definition/count").then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void testUnauthorizedResourceEngineProcessDefinition() {
        RestAssured.authentication =
                basic(IntegrationTestConstants.CAMUNDA_BPM_USERNAME, IntegrationTestConstants.CAMUNDA_BPM_PASSWORD
                        + "wrong pwd");

        given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when()
                .get("/engine/default/process-definition/count").then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void testHistoryProcessInstanceCount() {
        RestAssured.authentication =
                basic(IntegrationTestConstants.CAMUNDA_BPM_USERNAME, IntegrationTestConstants.CAMUNDA_BPM_PASSWORD);
        // min 1 instance expected - started by SimpleTravelRequestProcessStarter
        given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).and()
                .body("{ \"processDefinitionKey\" : \"" + TravelRequestProcessElements.PROCESS_ID + "\"}").with()
                .contentType(ContentType.JSON).when().post("/history/process-instance/count").then()
                .statusCode(HttpStatus.SC_OK).and().body("count", greaterThanOrEqualTo(1));
    }
}
