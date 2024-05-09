package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.services.MyService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api")
public class FrontendApi {

    @Inject
    @RestClient
    MyService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String invoke() {
        return service.get();
    }

}
