package org.acme.services;

import io.quarkus.runtime.StartupEvent;
import io.vertx.mutiny.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CustomerService {

    @ConfigProperty(name = "red-service-port", defaultValue = "9001")
    int port;

    /**
     * Start an HTTP server for the red service.
     *
     * Note: this method is called on a worker thread, and so it is allowed to block.
     */
    public void init(@Observes StartupEvent ev, Vertx vertx) {
        vertx.createHttpServer()
                .requestHandler(req -> req.response().endAndForget("Hello from Customer!"))
                .listenAndAwait(port);
    }

}
