package ${package}.testdata;

public final class IntegrationTestConstants {

    public final static String SERVER_NAME = "localhost";

    public final static String SERVER_URL = "http://" + SERVER_NAME;

    public final static int PORT = 9090;

    public final static String TEST_APPLICATION_NAME = "${artifactId}";

    public final static String BASE_PATH = "/" + TEST_APPLICATION_NAME;

    public final static String BASE_URL = SERVER_URL + ":" + String.valueOf(PORT) + BASE_PATH;

    public final static String CAMUNDA_BPM_USERNAME = "demo";

    public final static String CAMUNDA_BPM_PASSWORD = "demo";

    private IntegrationTestConstants() {
    }
}
