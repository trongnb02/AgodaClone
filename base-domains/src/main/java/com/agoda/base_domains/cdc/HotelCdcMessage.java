package com.agoda.base_domains.cdc;

import lombok.Data;

@Data
public class HotelCdcMessage {
    private String after;

    private String before;

    private Operation op;
}
