package ${package}.bpm.task.travel;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.bpm.constant.TravelRequestProcessElements;
import ${package}.business.domainmodel.Flight;

public class FlightBookingDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightBookingDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Flight flight = (Flight) execution.getVariable(TravelRequestProcessElements.PROCESS_VARIABLE_FLIGHT);
        LOGGER.info("***** Flight: '{}' booked.", flight);
    }
}
