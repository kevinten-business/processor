package io.processor.application.entity;

import lombok.Data;

/**
 * Flight外部请求
 */
@Data
public class FlightRequest {

    private String flightId;
    private String flightCity;
}
