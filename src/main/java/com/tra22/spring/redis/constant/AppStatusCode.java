package com.tra22.spring.redis.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AppStatusCode {
    UNEXPECTED_ERR("005"),
    NOT_FOUND_ENTITY("002"),
    APP_ERR("001"),
    SUCCESS("000");
    private final String code;
}
