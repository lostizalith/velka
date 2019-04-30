package com.github.lostizalith.velka.common.error;

import lombok.Value;

/**
 * Error entity.
 */
@Value(staticConstructor = "aErrorResponse")
public final class ErrorResponse {

    private final int status;
    private final String message;
}
