package ${package}.bpm;

import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import ${package}.bpm.constant.SampleProcessElements;

public class Starter implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(Starter.class);

    @Autowired
    private RuntimeService runtimeService;

    public void afterPropertiesSet() throws Exception {
        runtimeService.startProcessInstanceByKey(SampleProcessElements.PROCESS_ID);

        LOGGER.info("Sample Process started!");
    }

    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }
}
