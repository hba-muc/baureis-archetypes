package ${package}.bpm;

import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ${package}.bpm.constant.SampleProcessElements;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/applicationContext.xml")
@ActiveProfiles("test")
public class SampleProcessTest {

    @Inject
    @Rule
    public ProcessEngineRule processEngineRule;

    @Inject
    private ProcessEngine processEngine;

    @Inject
    private RuntimeService runtimeService;

    @Inject
    private HistoryService historyService;

    @After
    public void closeProcessEngine() {
        // Required, since all the other tests seem to do a specific drop on the end
        processEngine.close();
    }

    @Test
    public void testInjection() {
        assertThat(processEngine).isNotNull();
        assertThat(processEngineRule).isNotNull();
        assertThat(runtimeService).isNotNull();
        assertThat(historyService).isNotNull();
    }

    @Test
    public void testSampleProcessStart() {
        long noProcessInstancesBeforeProcessStart = historyService.createHistoricProcessInstanceQuery().count();
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey(SampleProcessElements.PROCESS_ID);
        long noProcessInstancesAfterProcessStart = historyService.createHistoricProcessInstanceQuery().count();

        assertThat(noProcessInstancesAfterProcessStart - noProcessInstancesBeforeProcessStart).isEqualTo(1);

        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId())
                        .singleResult();

        assertThat(historicProcessInstance.getProcessDefinitionId()).isNotEmpty();
        assertThat(historicProcessInstance.getProcessDefinitionId()).contains(SampleProcessElements.PROCESS_ID);
    }
}
