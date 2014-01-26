package ${package}.rest.resource;

import static com.jayway.restassured.RestAssured.basic;
import static com.jayway.restassured.RestAssured.given;
import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpStatus;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;

import ${package}.bpm.constant.TravelRequestProcessElements;
import ${package}.business.domainmodel.Car;
import ${package}.business.domainmodel.Flight;
import ${package}.business.domainmodel.Hotel;
import ${package}.business.domainmodel.TravelRequest;
import ${package}.rest.representation.CountRepresentation;
import ${package}.test.data.IntegrationTestConstants;

@RunWith(Arquillian.class)
@RunAsClient
public class TravelRequestProcessIT {

    private final static String TRAVEL_REQUEST_TEST_ID = UUID.randomUUID().toString();

    private final static TravelRequest NEW_TRAVEL_REQUEST_TEST_OBJECT = TravelRequest.valueOf(TRAVEL_REQUEST_TEST_ID,
            Hotel.valueOf("integrationtest hotel"), Flight.valueOf("integrationtest flight-number"),
            Car.valueOf("integrationtest car"));

    public TravelRequestProcessIT() {
        RestAssured.baseURI = IntegrationTestConstants.SERVER_URL;
        RestAssured.port = IntegrationTestConstants.PORT;
        RestAssured.basePath = IntegrationTestConstants.BASE_PATH_REST_API;
        RestAssured.authentication =
                basic(IntegrationTestConstants.CAMUNDA_BPM_USERNAME, IntegrationTestConstants.CAMUNDA_BPM_PASSWORD);
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
    public void createTravelRequest() throws JsonGenerationException, JsonMappingException, IOException {
        Long noStartedInstancesBeforeTest = getNoOfHistoryProcessInstances();

        given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).and().body(NEW_TRAVEL_REQUEST_TEST_OBJECT)
                .with().contentType(ContentType.JSON).when().post(TravelRequestResource.PATH).then()
                .statusCode(HttpStatus.SC_OK).body("id", notNullValue());

        Long noStartedInstancesAfterTest = getNoOfHistoryProcessInstances();
        assertThat(noStartedInstancesBeforeTest + 1).isEqualTo(noStartedInstancesAfterTest);
    }

    @Test
    public void getTravelRequest() {
        // TODO Start process, save id, getid from datasource
        given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when()
                .get(TravelRequestResource.PATH + "/" + TRAVEL_REQUEST_TEST_ID).then().statusCode(HttpStatus.SC_OK);

        String json =
                given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when()
                        .get(TravelRequestResource.PATH + "/" + TRAVEL_REQUEST_TEST_ID).asString();

        JsonPath jsonPath = new JsonPath(json);
        String id = jsonPath.getString("id");
        assertThat(id).isEqualTo(TRAVEL_REQUEST_TEST_ID);
    }

    private Long getNoOfHistoryProcessInstances() throws JsonParseException, JsonMappingException, IOException {
        String json =
                given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                        .body("{ \"processDefinitionKey\" : \"" + TravelRequestProcessElements.PROCESS_ID + "\"}")
                        .with().contentType(ContentType.JSON).post("/history/process-instance/count").asString();
        ObjectMapper objectMapper = new ObjectMapper();
        CountRepresentation count = objectMapper.readValue(json, new TypeReference<CountRepresentation>() {
        });
        return count.getCount();
    }
}
