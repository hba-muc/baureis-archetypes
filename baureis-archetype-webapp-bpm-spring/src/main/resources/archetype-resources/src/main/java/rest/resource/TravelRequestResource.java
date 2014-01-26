package ${package}.rest.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.business.domainmodel.Car;
import ${package}.business.domainmodel.Flight;
import ${package}.business.domainmodel.Hotel;
import ${package}.business.domainmodel.TravelRequest;
import ${package}.business.service.TravelRequestService;
import ${package}.business.service.TravelRequestServiceQualifier;

@Named
@Path(TravelRequestResource.PATH)
public class TravelRequestResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelRequestResource.class);

    public final static String PATH = "/travel-request";

    public final static String PATH_PARAM_ID = "id";

    @Inject
    @TravelRequestServiceQualifier
    TravelRequestService travelRequestService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTravelRequest(@Context final UriInfo uriInfo, TravelRequest travelRequest) {
        LOGGER.info("Call createTravelRequest(travelRequest={})", travelRequest);

        String id = travelRequestService.createTravelRequest(travelRequest);
        travelRequest.setId(id);

        return Response.ok().entity(travelRequest).build();
    }

    @GET
    @Path("/{" + TravelRequestResource.PATH_PARAM_ID + "}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTravelRequest(@Context final UriInfo uriInfo,
            @PathParam(TravelRequestResource.PATH_PARAM_ID) final String id) {
        LOGGER.info("Call getTravelRequest(id='{}')", id);

        TravelRequest travelRequest = getDummyTravelRequest(id);

        return Response.ok().entity(travelRequest).build();
    }

    private TravelRequest getDummyTravelRequest(String id) {
        return TravelRequest.valueOf(id, Hotel.valueOf("dummy hotel"), Flight.valueOf("dummy flight-number"),
                Car.valueOf("dummy car"));
    }
}
