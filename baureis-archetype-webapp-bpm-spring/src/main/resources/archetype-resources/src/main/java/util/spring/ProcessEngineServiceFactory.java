package ${package}.util.spring;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;

public class ProcessEngineServiceFactory {

    public static RepositoryService createRepositoryService(final ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    public static RuntimeService createRuntimeService(final ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    public static TaskService createTaskService(final ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    public static HistoryService createHistoryService(final ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    public static ManagementService createManagementService(final ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    private ProcessEngineServiceFactory() {
    }
}
