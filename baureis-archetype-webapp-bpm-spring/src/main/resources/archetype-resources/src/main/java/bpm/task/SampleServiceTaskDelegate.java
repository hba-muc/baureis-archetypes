package ${package}.bpm.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleServiceTaskDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleServiceTaskDelegate.class);

    public void execute(DelegateExecution delegate) throws Exception {
        LOGGER.info("Spring Bean invoked.");
    }
}