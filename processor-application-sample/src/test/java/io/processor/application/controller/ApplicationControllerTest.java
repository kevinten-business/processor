package io.processor.application.controller;


import io.processor.application.Application;
import io.processor.application.entity.FlightRequest;
import io.processor.application.entity.HotelRequest;
import io.processor.application.processor.error.code.AppErrorCodes;
import io.processor.core.response.StandardProcessorResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The Application controller test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationControllerTest {

    @Autowired
    private ApplicationController controller;

    /**
     * Hotel.
     */
    @Test
    public void hotel() {
        // success process
        {
            HotelRequest hotelRequest = new HotelRequest();
            hotelRequest.setHotelId("123");
            hotelRequest.setHotelName("Halton");
            StandardProcessorResponse hotel = controller.hotel(hotelRequest);
            Assert.assertEquals(AppErrorCodes.SUCCESS.getCode(), hotel.getCode());
        }
        // failure process
        {
            HotelRequest hotelRequest = new HotelRequest();
            hotelRequest.setHotelId("123");
            hotelRequest.setHotelName("Pullman");
            StandardProcessorResponse hotel = controller.hotel(hotelRequest);
            Assert.assertEquals(AppErrorCodes.PARAMETER_HOTEL_NAME_ERROR.getCode(), hotel.getCode());
        }
    }

    /**
     * Flight.
     */
    @Test
    public void flight() {
        // success process
        {
            FlightRequest flightRequest = new FlightRequest();
            flightRequest.setFlightId("123");
            flightRequest.setFlightCity("Shanghai");
            StandardProcessorResponse flight = controller.flight(flightRequest);
            Assert.assertEquals(AppErrorCodes.SUCCESS.getCode(), flight.getCode());
        }
        // failure process
        {
            FlightRequest flightRequest = new FlightRequest();
            flightRequest.setFlightId("123");
            flightRequest.setFlightCity("Beijing");
            StandardProcessorResponse flight = controller.flight(flightRequest);
            Assert.assertEquals(AppErrorCodes.PARAMETER_FLIGHT_CITY_ERROR.getCode(), flight.getCode());
        }
    }
}