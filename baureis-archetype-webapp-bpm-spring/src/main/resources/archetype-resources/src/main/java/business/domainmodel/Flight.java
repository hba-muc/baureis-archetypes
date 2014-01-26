package ${package}.business.domainmodel;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
public class Flight implements Serializable {

    private String flightnumber;

    public static Flight valueOf(final String flightnumber) {
        return new Flight(flightnumber);
    }

    private Flight(final String flightnumber) {
        this.flightnumber = flightnumber;
    }

    private Flight() {
    }

    public String getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
