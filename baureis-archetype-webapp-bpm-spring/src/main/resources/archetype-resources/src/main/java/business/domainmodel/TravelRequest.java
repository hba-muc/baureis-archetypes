package ${package}.business.domainmodel;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@SuppressWarnings("serial")
public class TravelRequest implements Serializable {

    private String id;
    private Hotel hotel;
    private Flight flight;
    private Car car;

    public static TravelRequest valueOf(final String id, final Hotel hotel, final Flight flight, final Car car) {
        return new TravelRequest(id, hotel, flight, car);
    }

    private TravelRequest(final String id, final Hotel hotel, final Flight flight, final Car car) {
        this.id = id;
        this.hotel = hotel;
        this.flight = flight;
        this.car = car;
    }

    public TravelRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
