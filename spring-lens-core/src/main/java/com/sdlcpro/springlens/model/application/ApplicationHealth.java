package com.sdlcpro.springlens.model.application;

import com.sdlcpro.springlens.annotation.SpringLensWritable;
/**
 * Health status of the application, as reported by the health endpoint.
 *
 * <p>This type is a response model marked with {@link SpringLensWritable}, so it is
 * serialized as the body of Spring-Lens REST responses using the
 * {@code application/vnd.sdlc-pro.spring-lens.v2+json} media type.
*/
@SpringLensWritable
public record ApplicationHealth(HealthStatus status) {

}
