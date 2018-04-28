package me.shufork.common.advice;

import lombok.extern.slf4j.Slf4j;
import me.shufork.common.dto.misc.ReplyBody;
import me.shufork.common.enums.ErrorCodeEnums;
import me.shufork.common.exceptions.BaseException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

/**
 * Centralized exception handling across all @RequestMapping methods through @ExceptionHandler methods.
 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
 *
 */
@RestControllerAdvice("me.shufork.biz.controller")
@Slf4j
public class ControllerExceptionAdvice {

    private boolean isBadRequest(Exception exception){
        return exception instanceof MethodArgumentNotValidException ||
                exception instanceof HttpMediaTypeNotSupportedException ||
                exception instanceof HttpRequestMethodNotSupportedException ||
                exception instanceof MethodArgumentTypeMismatchException ||
                exception instanceof MissingServletRequestParameterException;
    }
    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<ReplyBody> handleException(Exception exception) {
        log.info("handling exception:"+getClass().getName());
        log.warn(exception.getMessage(),exception);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");

        ReplyBody<?> replyBody = new ReplyBody<>();
        HttpStatus httpStatus;

        if (exception instanceof BaseException) {
            BaseException baseException = (BaseException) exception;

            httpStatus = HttpStatus.valueOf(baseException.httpCodeAdvice());

            replyBody.setCode(baseException.errorCode());
            replyBody.setMessage(baseException.getMessage());
            replyBody.setMoreInfo(baseException.detailInfo());
        } else if (isBadRequest(exception)) {
            httpStatus = HttpStatus.BAD_REQUEST;

            String message = exception.getMessage();
            if(exception instanceof MethodArgumentNotValidException){
                MethodArgumentNotValidException validException = (MethodArgumentNotValidException) exception;
                message = validException.getBindingResult().getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining(", "));
            }

            replyBody.setCode(ErrorCodeEnums.BAD_REQUEST.getValue());
            replyBody.setMessage(message);
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

            replyBody.setCode(ErrorCodeEnums.SERVER_ERROR.getValue());
            replyBody.setMessage(exception.getMessage());
        }

        ResponseEntity<ReplyBody> responseEntity;
        responseEntity = new ResponseEntity<>(replyBody, headers, httpStatus);
        return responseEntity;
    }

    @PostConstruct
    void onInit(){
        log.info("{} init",getClass().getSimpleName());
    }
}
