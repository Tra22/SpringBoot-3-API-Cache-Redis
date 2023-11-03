package com.tra22.spring.redis.payload.global;

import com.tra22.spring.redis.constant.AppStatusCode;
import com.tra22.spring.redis.exception.AppException;
import com.tra22.spring.redis.exception.NotFoundEntityException;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {
    private T data;
    private MetaData metadata;
    private String message;
    private String code;
    public static Response<?> ok(Object data){
        return Response
                .builder()
                .data(data)
                .message("Success to get response.")
                .code(AppStatusCode.SUCCESS.getCode())
                .build();
    }
    public static Response<?> okWithNoContent(){
        return Response
                .builder()
                .message("Success with no content.")
                .code(AppStatusCode.SUCCESS.getCode())
                .build();
    }
    public static Response<?> notFoundEntityResponse(NotFoundEntityException exception, HttpServletRequest httpServletRequest){
        return Response
                .builder()
                .metadata(getCurrentMetaData(httpServletRequest))
                .message(exception.getMessage())
                .code(AppStatusCode.NOT_FOUND_ENTITY.getCode())
                .build();
    }
    public static Response<?> handleAppException(AppException exception, HttpServletRequest httpServletRequest){
        return Response
                .builder()
                .metadata(getCurrentMetaData(httpServletRequest))
                .message(exception.getMessage())
                .code(AppStatusCode.APP_ERR.getCode())
                .build();
    }
    public static Response<?> unhandledException(Exception exception, HttpServletRequest httpServletRequest){
        return Response
                .builder()
                .metadata(getCurrentMetaData(httpServletRequest))
                .message("Unexpected error.")
                .code(AppStatusCode.UNEXPECTED_ERR.getCode())
                .build();
    }
    public static Response<?> unhandledException(HttpServletRequest httpServletRequest){
        return Response
                .builder()
                .metadata(getCurrentMetaData(httpServletRequest))
                .message("Unexpected error.")
                .code(AppStatusCode.UNEXPECTED_ERR.getCode())
                .build();
    }
    private static MetaData getCurrentMetaData(HttpServletRequest httpServletRequest){
        return  MetaData
                        .builder()
                        .path(httpServletRequest.getServletPath())
                        .timestamp(new Date())
                        .build();
    }
}
