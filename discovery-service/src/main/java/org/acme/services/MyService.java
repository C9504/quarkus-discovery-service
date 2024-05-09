package org.acme.services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient(baseUri = "stork://my-service")
public interface MyService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String get();

}
