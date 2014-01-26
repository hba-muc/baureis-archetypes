package ${package}.test.starter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import ${package}.bpm.constant.TravelRequestProcessElements;
import ${package}.business.domainmodel.Car;

public class SimpleTravelRequestProcessStarter implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTravelRequestProcessStarter.class);

    @Inject
    private RuntimeService runtimeService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Car car = Car.valueOf("starter car");

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_HOTEL_BOOKED, Boolean.FALSE);
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_FLIGHT_BOOKED, Boolean.FALSE);
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_CAR_BOOKED, Boolean.TRUE);
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_CAR, car);

        runtimeService.startProcessInstanceByKey(TravelRequestProcessElements.PROCESS_ID, variables);

        LOGGER.info("Sample Process with process id: {} started!", TravelRequestProcessElements.PROCESS_ID);
    }
}
