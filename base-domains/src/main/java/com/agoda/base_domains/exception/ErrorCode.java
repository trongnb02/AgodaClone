package com.agoda.base_domains.exception;

public class ErrorCode {
    public static final String INTERNAL_SERVER_ERROR_USER_SERVICE = "USER-SERVICE-1000";
    public static final String EMAIL_NOT_FOUND = "USER-SERVICE-1001";
    public static final String USER_NOT_FOUND = "USER-SERVICE-1002";
    public static final String ALREADY_EXISTS = "USER-SERVICE-1003";
    public static final String UNAUTHORIZED_USER_SERVICE = "USER-SERVICE-1004";

    public static final String INTERNAL_SERVER_ERROR_AUTH_SERVICE = "AUTH-SERVICE-1000";
    public static final String INVALID_TOKEN = "AUTH-SERVICE-1001";

    public static final String INTERNAL_SERVER_ERROR_NOTIFICATION_SERVICE = "NOTIFICATION-SERVICE-1000";
    public static final String NOTIFICATION_NOT_FOUND = "NOTIFICATION-SERVICE-1001";
}
