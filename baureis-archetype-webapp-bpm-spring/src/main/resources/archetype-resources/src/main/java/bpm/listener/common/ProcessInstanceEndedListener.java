package ${package}.bpm.listener.common;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessInstanceEndedListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessInstanceEndedListener.class);

    public void writeEvent(DelegateExecution execution) {
        LOGGER.info("***** {} process instance with id: {} ended.", execution.getProcessDefinitionId(),
                execution.getProcessInstanceId());
    }

}
