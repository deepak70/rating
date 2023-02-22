package com.ates.rating.app;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GenericResponse<T> {
    private final Boolean success;
    private final T data;
    private final String message;

}
