package com.sicred.votacao.infra.exception;

import com.sicred.votacao.infra.menssage.MessageTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {


    private static final HttpStatus BASE_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    private final MessageTranslator messageTranslator;

    public ControllerExceptionHandler(MessageTranslator messageTranslator) {
        this.messageTranslator = messageTranslator;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionMessage> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        String path = request.getRequestURI();
        List<ObjectError> fieldErrors = exception.getBindingResult().getAllErrors();
        String message = fieldErrors.stream()
                .map(objectErrorObjectFunction())
                .collect(Collectors.joining(", "));
        ExceptionMessage exceptionMessage = ExceptionMessage.of(path, message, HttpStatus.BAD_REQUEST);
        logger.error("A bean validation failed while trying parse to requested method: {}", exceptionMessage);
        return exceptionMessage.toResponseEntity();
    }

    private Function<ObjectError, String> objectErrorObjectFunction() {
        return objectError -> {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                return String.format("[%s]: %s", fieldError.getField(), messageTranslator.getMessage(fieldError.getDefaultMessage()));
            }
            return messageTranslator.getMessage(objectError.getDefaultMessage());
        };
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionMessage> handleBindException(HttpServletRequest request, BindException exception) {
        String path = request.getRequestURI();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream()
                .map(it -> String.format("[%s]: %s", it.getField(), messageTranslator.getMessage(it.getDefaultMessage())))
                .collect(Collectors.joining(", "));
        ExceptionMessage exceptionMessage = ExceptionMessage.of(path, message, HttpStatus.BAD_REQUEST);
        logger.error("Could not bind the parameters to requested method: {}", exceptionMessage);
        return exceptionMessage.toResponseEntity();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ExceptionMessage> handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException exception) {
        String path = request.getRequestURI();
        String message = extractAndSplitMessage(exception);
        ExceptionMessage exceptionMessage = ExceptionMessage.of(path, message, HttpStatus.BAD_REQUEST);
        logger.error("Could not read the object passed as parameter to requested method: {}", exceptionMessage);
        return exceptionMessage.toResponseEntity();
    }

    private String extractAndSplitMessage(HttpMessageNotReadableException exception) {
        String message = exception.getMessage();
        if (message == null) return "";
        String separator = ":";
        return message.split(separator)[0];
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessage> handleException(HttpServletRequest request, Exception exception) {
        String path = request.getRequestURI();
        HttpStatus httpStatus = httpStatus(exception);
        String[] arguments = extractArguments(exception);
        String message = messageTranslator.getMessage(exception.getMessage(), arguments);
        ExceptionMessage exceptionMessage = ExceptionMessage.of(path, message, httpStatus);
        logger.error("An exception occurred: {}", exceptionMessage);
        return exceptionMessage.toResponseEntity();
    }

    private String[] extractArguments(Exception exception) {
        if (exception instanceof TranslatableArguments) {
            return ((TranslatableArguments) exception).arguments();
        }
        return null;
    }

    private HttpStatus httpStatus(Exception exception) {
        ResponseStatus responseStatus = getResponseStatusAnnotation(exception);
        if (responseStatus == null) return BASE_HTTP_STATUS;
        if (responseStatus.value() == BASE_HTTP_STATUS) return responseStatus.code();
        return responseStatus.value();
    }

    private ResponseStatus getResponseStatusAnnotation(Exception exception) {
        return exception.getClass().getAnnotation(ResponseStatus.class);
    }
}
