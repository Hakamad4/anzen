package com.hakamada.anzen.model;

public class CacheLimitSizeCantBeZeroExcetion extends RuntimeException {
    public CacheLimitSizeCantBeZeroExcetion() {
        super("cache should be have a limit > 0");
    }

    public CacheLimitSizeCantBeZeroExcetion(String message) {
        super(message);
    }

    public CacheLimitSizeCantBeZeroExcetion(String message, Throwable cause) {
        super(message, cause);
    }
}
