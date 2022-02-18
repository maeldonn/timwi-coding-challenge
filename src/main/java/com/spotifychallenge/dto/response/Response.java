package com.spotifychallenge.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Generic Response
 *
 * @param <T> Type of the payload
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    private Status status;
    private T payload;

    /**
     * Response for a OK Status
     *
     * @param <T> Type of the payload
     * @return a generic Response
     */
    public static <T> Response<T> ok() {
        Response<T> response = new Response<>();
        response.setStatus(Status.OK);
        return response;
    }

    /**
     * Response for a CREATED Status
     *
     * @param <T> Type of the payload
     * @return a generic Response
     */
    public static <T> Response<T> created() {
        Response<T> response = new Response<>();
        response.setStatus(Status.CREATED);
        return response;
    }

    /**
     * Response for a BAD_REQUEST Status
     *
     * @param <T> Type of the payload
     * @return a generic Response
     */
    public static <T> Response<T> badRequest() {
        Response<T> response = new Response<>();
        response.setStatus(Status.BAD_REQUEST);
        return response;
    }

    /**
     * Response for a NOT_FOUND Status
     *
     * @param <T> Type of the payload
     * @return a generic Response
     */
    public static <T> Response<T> notFound() {
        Response<T> response = new Response<>();
        response.setStatus(Status.NOT_FOUND);
        return response;
    }

    /**
     * HTTP Status Code
     */
    public enum Status {
        OK, CREATED, BAD_REQUEST, NOT_FOUND
    }
}

