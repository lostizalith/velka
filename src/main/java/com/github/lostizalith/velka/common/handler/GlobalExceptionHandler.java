package com.github.lostizalith.velka.common.handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.lostizalith.velka.common.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ValidationException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.joining;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle validation exception.
     *
     * @param exception an exception when constraint validation fails
     * @return a message error
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity handleValidationError(final MethodArgumentNotValidException exception) {
        log.warn(exception.getMessage());
        final BindingResult result = exception.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    private ResponseEntity processFieldErrors(final List<FieldError> fieldErrors) {
        final StringBuilder sb = new StringBuilder();
        final int oneFieldCount = 1;
        if (oneFieldCount == fieldErrors.size()) {
            sb.append(getErrorDescription(fieldErrors.get(0)));
        } else {
            sb.append(fieldErrors.stream()
                .sorted(Comparator.comparing(FieldError::getField)
                    .thenComparing(DefaultMessageSourceResolvable::getDefaultMessage))
                .map(this::getErrorDescription)
                .collect(joining(". ", "", ".")));
        }
        log.warn("Field validation errors: {}", sb.toString());

        return ResponseEntity.badRequest()
            .body(ErrorResponse.aErrorResponse(HttpStatus.BAD_REQUEST.value(), sb.toString()));
    }

    private String getErrorDescription(final FieldError fieldError) {
        return fieldError.getDefaultMessage();
    }

    /**
     * Handle exception when intended handler are not found.
     *
     * @param exception an exception
     * @return an error message
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity handleAnException(final Exception exception) {
        final UUID errorUUID = UUID.randomUUID();
        log.error("Error-ID: {} - {}", errorUUID, exception.getMessage(), exception);

        final String message = "An error occurred. Please contact support. Error-ID:" + errorUUID;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse.aErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message));
    }

    /**
     * Handle {@link ValidationException}.
     *
     * @param exception an exception
     * @return an error message
     */
    @ExceptionHandler({ValidationException.class})
    @ResponseBody
    public ResponseEntity handleAnValidationException(final ValidationException exception) {

        final String message = exception.getMessage();

        log.warn("Caught ValidationException: {}", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.aErrorResponse(HttpStatus.BAD_REQUEST.value(), message));
    }

    /**
     * Handle {@link HttpMediaTypeNotSupportedException}.
     *
     * @param exception the target exception
     * @return an error message
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ResponseEntity handleHttpMediaTypeNotSupportedException(final HttpMediaTypeNotSupportedException exception) {

        final String message = exception.getMessage();

        log.warn("Caught HttpMediaTypeNotSupportedException: {} ", message);

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
            .body(ErrorResponse.aErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), message));
    }

    /**
     * Handle {@link IllegalArgumentException}.
     *
     * @param exception an exception
     * @return an error message
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public ResponseEntity handleIllegalArgumentException(final IllegalArgumentException exception) {

        final String message = exception.getMessage();

        log.warn("Caught IllegalArgumentException: {}", message);

        return ResponseEntity.badRequest().body(ErrorResponse.aErrorResponse(HttpStatus.BAD_REQUEST.value(), message));
    }

    /**
     * Handle converter exception.
     *
     * @param exception an exception when httpMessageConverter loadMeta method fails
     * @return an error message
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public ResponseEntity handleMessageNotReadableException(final HttpMessageNotReadableException exception) {
        log.warn("caught HttpMessageNotReadableException: {}", exception.getMessage());

        final Throwable cause = exception.getCause();
        if (cause == null) {
            if (exception.getMessage().startsWith("Required request body is missing")) {
                final String message = "Required request body is missing.";
                log.warn("Answer to the caller: {}", message);

                return ResponseEntity.badRequest()
                    .body(ErrorResponse.aErrorResponse(HttpStatus.BAD_REQUEST.value(), message));
            } else {
                return handleAnException(exception);
            }
        }

        if (cause instanceof JsonMappingException) {
            if (exception.getMessage().contains("Unexpected token (END_OBJECT), expected FIELD_NAME: need JSON String that contains type id")) {
                final String message = "Required request body is missing.";
                log.warn("Answer to the caller: {}", message);

                return ResponseEntity.badRequest()
                    .body(ErrorResponse.aErrorResponse(HttpStatus.BAD_REQUEST.value(), message));
            } else {
                // Jackson provide wrapped original exception and no guarantees for getting an original message
                final JsonMappingException wrapper = (JsonMappingException) cause;
                final String message = wrapper.getCause() == null ? wrapper.getOriginalMessage() : wrapper.getCause().getMessage();

                return ResponseEntity.badRequest()
                    .body(ErrorResponse.aErrorResponse(HttpStatus.BAD_REQUEST.value(), message));
            }
        }

        return handleAnException(exception);
    }
}
