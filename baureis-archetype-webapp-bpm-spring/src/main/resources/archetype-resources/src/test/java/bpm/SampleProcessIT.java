package ${package}.bpm;

import static com.jayway.restassured.RestAssured.basic;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
import ${package}.testdata.IntegrationTestConstants;

@RunWith(Arquillian.class)
@RunAsClient
public class SampleProcessIT {

    public SampleProcessIT() {
        RestAssured.baseURI = IntegrationTestConstants.SERVER_URL;
        RestAssured.port = IntegrationTestConstants.PORT;
        RestAssured.basePath = IntegrationTestConstants.BASE_PATH;
        RestAssured.authentication =
                basic(IntegrationTestConstants.CAMUNDA_BPM_USERNAME, IntegrationTestConstants.CAMUNDA_BPM_PASSWORD);
    }

    @Deployment
    public static WebArchive createDeployment() {
        File webXml = new File("src/test/webapp/WEB-INF/web.xml");
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "${artifactId}.war").setWebXML(webXml);
        return archive;
    }

    @Test
    public void testDefaultProcessEngine() {
        given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().get("/engine").then()
                .statusCode(HttpStatus.SC_OK).body("[0].name", equalTo("default"));
    }

    @Test
    public void testSampleProcessStarted() {
        given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when().get("/history/process-instance/count")
                .then().statusCode(HttpStatus.SC_OK).body("count", equalTo(Integer.valueOf(1)));
    }
}
