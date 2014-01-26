package ${package}.test.data;

public final class IntegrationTestConstants {

    public final static String SERVER_NAME = "localhost";

    public final static String SERVER_URL = "http://" + SERVER_NAME;

    public final static int PORT = 9090;

    public final static String TEST_APPLICATION_NAME = "${artifactId}";

    public final static String BASE_PATH_REST_API = "/" + TEST_APPLICATION_NAME + "/rest";

    public final static String BASE_URL = SERVER_URL + ":" + String.valueOf(PORT) + BASE_PATH_REST_API;

    public final static String CAMUNDA_BPM_USERNAME = "demo";

    public final static String CAMUNDA_BPM_PASSWORD = "demo";

    public final static String TEST_DEPLOYMENT_DESCRIPTOR = "src/test/webapp/WEB-INF/web.xml";

    private IntegrationTestConstants() {
    }
}
