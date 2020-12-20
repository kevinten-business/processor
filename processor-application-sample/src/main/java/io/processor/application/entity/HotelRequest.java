package io.processor.application.entity;

import lombok.Data;

/**
 * Hotel外部请求
 */
@Data
public class HotelRequest {

    private String hotelId;
    private String hotelName;
}
