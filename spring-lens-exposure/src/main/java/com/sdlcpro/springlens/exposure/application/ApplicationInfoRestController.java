package com.sdlcpro.springlens.exposure.application;

import com.sdlcpro.springlens.annotation.SpringLensEndpoint;
import com.sdlcpro.springlens.annotation.SpringLensInternalComponent;
import com.sdlcpro.springlens.exposure.ApiResponseHandler;
import com.sdlcpro.springlens.exposure.SpringLensMediaType;
import com.sdlcpro.springlens.inspector.application.ApplicationInfoInspector;
import com.sdlcpro.springlens.model.application.ApplicationHealth;
import com.sdlcpro.springlens.model.application.HealthStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that exposes metadata for the current Spring application.
 *
 * @author Ixtiyorxon
 * @since 2026-08-28
 */
@RestController
@SpringLensEndpoint
@SpringLensInternalComponent
@RequestMapping("/spring-lens/api/application")
public class ApplicationInfoRestController {

    private final ApplicationInfoInspector applicationInfoInspector;

    /**
     * Creates a controller backed by the application metadata inspector.
     *
     * @param applicationInfoInspector inspector used to retrieve the current application metadata
     */
    public ApplicationInfoRestController(ApplicationInfoInspector applicationInfoInspector) {
        this.applicationInfoInspector = applicationInfoInspector;
    }

    /**
     * Returns a snapshot of the current Spring application's environment and startup metadata.
     *
     * <p>Produces {@link SpringLensMediaType#APPLICATION_SPRING_LENS_JSON_VALUE}.
     *
     * @return an HTTP response containing application metadata, or a standardized error response when unavailable
     */
    @GetMapping(produces = SpringLensMediaType.APPLICATION_SPRING_LENS_JSON_VALUE)
    public ResponseEntity<?> getApplicationInfo() {
        return ApiResponseHandler.handle(
                this.applicationInfoInspector::inspect,
                "ApplicationInfo not found!"
        );
    }

    /**
     * Simply return the application health status
     *
     * <p>Produces {@link SpringLensMediaType#APPLICATION_SPRING_LENS_JSON_VALUE}.
     *
     * @return {@link ResponseEntity} by wrapping the {@link ApplicationHealth}
     */
    @GetMapping(value = "/health", produces = SpringLensMediaType.APPLICATION_SPRING_LENS_JSON_VALUE)
    public ResponseEntity<ApplicationHealth> getApplicationHealth() {
        return ResponseEntity.ok(new ApplicationHealth(HealthStatus.UP));
    }
}
