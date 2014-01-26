package ${package}.bpm.constant;

public final class TravelRequestProcessElements {

    public final static String PROCESS_ID = "simpleTravelRequestProcess";

    public final static String PROCESS_VARIABLE_TRAVEL_REQUEST_ID = "travelRequestId";

    public final static String PROCESS_VARIABLE_IS_HOTEL_BOOKED = "isHotelBooked";

    public final static String PROCESS_VARIABLE_IS_CAR_BOOKED = "isCarBooked";

    public final static String PROCESS_VARIABLE_IS_FLIGHT_BOOKED = "isFlightBooked";

    public final static String PROCESS_VARIABLE_HOTEL = "hotel";

    public final static String PROCESS_VARIABLE_FLIGHT = "flight";

    public final static String PROCESS_VARIABLE_CAR = "car";

    public final static String ACTIVITY_ID_BOOK_HOTEL = "BookHotel_ServiceTask";

    public final static String ACTIVITY_ID_BOOK_FLIGHT = "BookFlight_ServiceTask";

    public final static String ACTIVITY_ID_BOOK_CAR = "BookCar_ServiceTask";

    public final static String ACTIVITY_ID_SEND_BOOKING_NOTIFICATION = "SendBookingNotification_ServiceTask";

    public final static String ACTIVITY_ID_START_EVENT = "TravelRequestAvailable_StartEvent";

    public final static String ACTIVITY_ID_END_EVENT = "TravelRequestProcessedSuccessfully_EndEvent";

    // prevent instantiation
    private TravelRequestProcessElements() {
    }
}
