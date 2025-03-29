package com.agoda.booking_service.client;

import com.agoda.booking_service.exception.HotelServiceException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CustomFeignDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (methodKey.contains("HotelServiceClient")) {
            return handleHotelServiceError(response);
        }else {
            return handleGenericError(response);
        }
    }

    private Exception handleHotelServiceError(Response response) {
        HotelServiceException ex = formatHotelServiceException(response);
        switch (response.status()) {
            case 400 -> {
                log.error("Error in request through feign client {}" , ex.getMessage()
                        + "-" + ex.getCode());
                return ex;
            }
            case 401 -> {
                log.error("Unauthorized Request through feign");
                return new Exception("Unauthorized Request through feign");
            }
            case 404 -> {
                log.error("Unidentified Request through feign");
                return new Exception("Unidentified Request through feign");
            }
            default -> {
                log.error("Error in request through feign client ");
                return new Exception("Error in request through feign client");
            }
        }
    }

    private HotelServiceException formatHotelServiceException(Response response) {
        HotelServiceException ex = null;
        Reader reader = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper().disable(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
            );
            reader = response.body().asReader(StandardCharsets.UTF_8);
            String result = IOUtils.toString(reader);
            ex = objectMapper.readValue(result, HotelServiceException.class);
        } catch (IOException e) {
            log.error("IO Exception on reading exception message in feign client", e);
        } finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            }catch (IOException e) {
                log.error("IO Exception on reading exception message in feign client");
            }
        }
        return ex;
    }

    private Exception handleGenericError(Response response) {
        log.error("Generic error in Feign request: {}", response.status());
        return new Exception("Generic error in Feign request");
    }
}
