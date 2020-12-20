package io.processor.application.controller;

import io.processor.application.processor.context.FlightContext;
import io.processor.application.processor.context.HotelContext;
import io.processor.application.entity.FlightRequest;
import io.processor.application.entity.HotelRequest;
import io.processor.application.processor.receiver.FlightReceiver;
import io.processor.application.processor.receiver.HotelReceiver;
import io.processor.core.response.StandardProcessorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * App网络请求接收器
 */
@RestController
public class ApplicationController {

    @Autowired
    private HotelReceiver hotelReceiver;
    @Autowired
    private FlightReceiver flightReceiver;

    @PostMapping("/hotel")
    public StandardProcessorResponse hotel(HotelRequest request) {
        HotelContext hotelContext = this.hotelReceiver.generateContext(request);
        return this.hotelReceiver.receive(hotelContext);
    }

    @PostMapping("/flight")
    public StandardProcessorResponse flight(FlightRequest request) {
        FlightContext flightContext = this.flightReceiver.generateContext(request);
        return this.flightReceiver.receive(flightContext);
    }
}
