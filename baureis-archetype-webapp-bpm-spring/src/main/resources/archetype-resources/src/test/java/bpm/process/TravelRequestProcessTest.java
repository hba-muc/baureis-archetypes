package ${package}.bpm.process;

import static org.fest.assertions.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
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

import ${package}.bpm.constant.TravelRequestProcessElements;
import ${package}.business.domainmodel.Car;
import ${package}.business.domainmodel.Flight;
import ${package}.business.domainmodel.Hotel;
import ${package}.business.domainmodel.TravelRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/applicationContext.xml")
@ActiveProfiles("test")
public class TravelRequestProcessTest {

    private final static String TRAVEL_REQUEST_TEST_ID = UUID.randomUUID().toString();

    private final static TravelRequest NEW_TRAVEL_REQUEST_TEST_OBJECT = TravelRequest.valueOf(TRAVEL_REQUEST_TEST_ID,
            Hotel.valueOf("test hotel"), Flight.valueOf("test flight-number"), Car.valueOf("test car"));

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
    public void testStartCarTravelRequestProcess() {
        long noProcessInstancesBeforeProcessStart = historyService.createHistoricProcessInstanceQuery().count();

        TravelRequest travelRequest = NEW_TRAVEL_REQUEST_TEST_OBJECT;

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_TRAVEL_REQUEST_ID, travelRequest.getId());
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_HOTEL_BOOKED, Boolean.FALSE);
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_FLIGHT_BOOKED, Boolean.FALSE);
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_CAR_BOOKED, Boolean.TRUE);
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_CAR, travelRequest.getCar());

        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey(TravelRequestProcessElements.PROCESS_ID, variables);
        long noProcessInstancesAfterProcessStart = historyService.createHistoricProcessInstanceQuery().count();
        assertThat(noProcessInstancesAfterProcessStart - noProcessInstancesBeforeProcessStart).isEqualTo(1);

        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId())
                        .singleResult();

        assertThat(historicProcessInstance.getProcessDefinitionId()).isNotEmpty();
        assertThat(historicProcessInstance.getProcessDefinitionId()).contains(TravelRequestProcessElements.PROCESS_ID);

        HistoricActivityInstance startEventInstance =
                historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId())
                        .activityId(TravelRequestProcessElements.ACTIVITY_ID_START_EVENT).singleResult();
        assertThat(startEventInstance.getActivityId()).isEqualTo(TravelRequestProcessElements.ACTIVITY_ID_START_EVENT);

        HistoricActivityInstance carInstance =
                historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId())
                        .activityId(TravelRequestProcessElements.ACTIVITY_ID_BOOK_CAR).singleResult();
        assertThat(carInstance.getActivityId()).isEqualTo(TravelRequestProcessElements.ACTIVITY_ID_BOOK_CAR);

        HistoricActivityInstance sendBookingNotificationInstance =
                historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId())
                        .activityId(TravelRequestProcessElements.ACTIVITY_ID_SEND_BOOKING_NOTIFICATION).singleResult();
        assertThat(sendBookingNotificationInstance.getActivityId()).isEqualTo(
                TravelRequestProcessElements.ACTIVITY_ID_SEND_BOOKING_NOTIFICATION);

        HistoricActivityInstance endEventInstance =
                historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId())
                        .activityId(TravelRequestProcessElements.ACTIVITY_ID_END_EVENT).singleResult();
        assertThat(endEventInstance.getActivityId()).isEqualTo(TravelRequestProcessElements.ACTIVITY_ID_END_EVENT);

        HistoricActivityInstance hotelInstance =
                historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId())
                        .activityId(TravelRequestProcessElements.ACTIVITY_ID_BOOK_HOTEL).singleResult();
        assertThat(hotelInstance).isNull();

        HistoricActivityInstance flightInstance =
                historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId())
                        .activityId(TravelRequestProcessElements.ACTIVITY_ID_BOOK_FLIGHT).singleResult();
        assertThat(flightInstance).isNull();
    }

    @Test
    public void testStartHotelFlightCarTravelRequestProcess() {
        long noProcessInstancesBeforeProcessStart = historyService.createHistoricProcessInstanceQuery().count();

        TravelRequest travelRequest = NEW_TRAVEL_REQUEST_TEST_OBJECT;

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_TRAVEL_REQUEST_ID, travelRequest.getId());
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_HOTEL_BOOKED, Boolean.TRUE);
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_HOTEL, travelRequest.getHotel());
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_FLIGHT_BOOKED, Boolean.TRUE);
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_FLIGHT, travelRequest.getFlight());
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_CAR_BOOKED, Boolean.TRUE);
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_CAR, travelRequest.getCar());

        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey(TravelRequestProcessElements.PROCESS_ID, variables);
        long noProcessInstancesAfterProcessStart = historyService.createHistoricProcessInstanceQuery().count();
        assertThat(noProcessInstancesAfterProcessStart - noProcessInstancesBeforeProcessStart).isEqualTo(1);

        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId())
                        .singleResult();

        assertThat(historicProcessInstance.getProcessDefinitionId()).isNotEmpty();
        assertThat(historicProcessInstance.getProcessDefinitionId()).contains(TravelRequestProcessElements.PROCESS_ID);

        HistoricActivityInstance startEventInstance =
                historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId())
                        .activityId(TravelRequestProcessElements.ACTIVITY_ID_START_EVENT).singleResult();
        assertThat(startEventInstance.getActivityId()).isEqualTo(TravelRequestProcessElements.ACTIVITY_ID_START_EVENT);

        HistoricActivityInstance carInstance =
                historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId())
                        .activityId(TravelRequestProcessElements.ACTIVITY_ID_BOOK_CAR).singleResult();
        assertThat(carInstance.getActivityId()).isEqualTo(TravelRequestProcessElements.ACTIVITY_ID_BOOK_CAR);

        HistoricActivityInstance sendBookingNotificationInstance =
                historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId())
                        .activityId(TravelRequestProcessElements.ACTIVITY_ID_SEND_BOOKING_NOTIFICATION).singleResult();
        assertThat(sendBookingNotificationInstance.getActivityId()).isEqualTo(
                TravelRequestProcessElements.ACTIVITY_ID_SEND_BOOKING_NOTIFICATION);

        HistoricActivityInstance endEventInstance =
                historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId())
                        .activityId(TravelRequestProcessElements.ACTIVITY_ID_END_EVENT).singleResult();
        assertThat(endEventInstance.getActivityId()).isEqualTo(TravelRequestProcessElements.ACTIVITY_ID_END_EVENT);

        HistoricActivityInstance hotelInstance =
                historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId())
                        .activityId(TravelRequestProcessElements.ACTIVITY_ID_BOOK_HOTEL).singleResult();
        assertThat(hotelInstance.getActivityId()).isEqualTo(TravelRequestProcessElements.ACTIVITY_ID_BOOK_HOTEL);

        HistoricActivityInstance flightInstance =
                historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId())
                        .activityId(TravelRequestProcessElements.ACTIVITY_ID_BOOK_FLIGHT).singleResult();
        assertThat(flightInstance.getActivityId()).isEqualTo(TravelRequestProcessElements.ACTIVITY_ID_BOOK_FLIGHT);
    }
}
