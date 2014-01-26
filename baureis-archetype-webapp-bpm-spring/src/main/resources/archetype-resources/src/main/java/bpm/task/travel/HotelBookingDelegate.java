package ${package}.bpm.task.travel;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.bpm.constant.TravelRequestProcessElements;
import ${package}.business.domainmodel.Hotel;

public class HotelBookingDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(HotelBookingDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Hotel hotel = (Hotel) execution.getVariable(TravelRequestProcessElements.PROCESS_VARIABLE_HOTEL);
        LOGGER.info("***** Hotel: '{}' booked.", hotel);
    }
}
