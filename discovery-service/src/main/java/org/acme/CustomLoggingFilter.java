package org.acme;

import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.client.spi.ResteasyReactiveClientRequestContext;
import org.jboss.resteasy.reactive.client.spi.ResteasyReactiveClientRequestFilter;

@Provider
public class CustomLoggingFilter implements ResteasyReactiveClientRequestFilter {

    private static final Logger LOG = Logger.getLogger(CustomLoggingFilter.class);

    @Override
    public void filter(ResteasyReactiveClientRequestContext requestContext) {
        LOG.infof("Resolved address by Stork: %s", requestContext.getUri().toString());
    }
}