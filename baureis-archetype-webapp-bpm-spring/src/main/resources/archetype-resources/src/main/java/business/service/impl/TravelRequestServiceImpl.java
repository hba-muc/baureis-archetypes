package ${package}.business.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.RuntimeService;

import ${package}.bpm.constant.TravelRequestProcessElements;
import ${package}.business.domainmodel.Car;
import ${package}.business.domainmodel.Flight;
import ${package}.business.domainmodel.Hotel;
import ${package}.business.domainmodel.TravelRequest;
import ${package}.business.service.TravelRequestService;
import ${package}.business.service.TravelRequestServiceQualifier;

@Named()
@TravelRequestServiceQualifier
public class TravelRequestServiceImpl implements TravelRequestService {

    @Inject
    RuntimeService runtimeService;

    @Override
    public String createTravelRequest(final TravelRequest travelRequest) {
        Map<String, Object> variables = new HashMap<String, Object>();

        UUID travelRequestId = UUID.randomUUID();
        variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_TRAVEL_REQUEST_ID, travelRequestId);

        setHotelProcessVariables(travelRequest.getHotel(), variables);
        setFlightProcessVariables(travelRequest.getFlight(), variables);
        setCarProcessVariables(travelRequest.getCar(), variables);

        runtimeService.startProcessInstanceByKey(TravelRequestProcessElements.PROCESS_ID, variables);
        return travelRequestId.toString();
    }

    private void setHotelProcessVariables(final Hotel hotel, Map<String, Object> variables) {
        if (hotel != null && StringUtils.isNotBlank(hotel.getName())) {
            variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_HOTEL_BOOKED, Boolean.TRUE);
            variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_HOTEL, hotel);
        } else {
            variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_HOTEL_BOOKED, Boolean.FALSE);
        }
    }

    private void setFlightProcessVariables(final Flight flight, Map<String, Object> variables) {
        if (flight != null && StringUtils.isNotBlank(flight.getFlightnumber())) {
            variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_FLIGHT_BOOKED, Boolean.TRUE);
            variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_FLIGHT, flight);
        } else {
            variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_FLIGHT_BOOKED, Boolean.FALSE);
        }
    }

    private void setCarProcessVariables(final Car car, Map<String, Object> variables) {
        if (car != null && StringUtils.isNotBlank(car.getName())) {
            variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_CAR_BOOKED, Boolean.TRUE);
            variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_CAR, car);
        } else {
            variables.put(TravelRequestProcessElements.PROCESS_VARIABLE_IS_CAR_BOOKED, Boolean.FALSE);
        }
    }
}
