package ${package}.bpm.task.travel;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.bpm.constant.TravelRequestProcessElements;
import ${package}.business.domainmodel.Car;

public class CarBookingDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarBookingDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Car car = (Car) execution.getVariable(TravelRequestProcessElements.PROCESS_VARIABLE_CAR);
        LOGGER.info("***** Car: '{}' booked.", car);
    }
}
