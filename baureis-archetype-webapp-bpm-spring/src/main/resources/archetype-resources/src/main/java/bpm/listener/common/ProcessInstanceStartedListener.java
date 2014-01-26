package ${package}.bpm.listener.common;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessInstanceStartedListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessInstanceStartedListener.class);

    public void writeEvent(DelegateExecution execution) {
        LOGGER.info("***** {} process instance with id: {} started.", execution.getProcessDefinitionId(),
                execution.getProcessInstanceId());
    }
}
