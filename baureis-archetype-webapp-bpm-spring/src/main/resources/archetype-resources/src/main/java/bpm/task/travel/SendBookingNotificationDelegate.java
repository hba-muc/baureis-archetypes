package ${package}.bpm.task.travel;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendBookingNotificationDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendBookingNotificationDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("***** Booking notification sent");
    }
}
