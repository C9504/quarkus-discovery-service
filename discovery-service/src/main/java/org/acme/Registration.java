package org.acme;

import io.quarkus.runtime.StartupEvent;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.ServiceOptions;
import io.vertx.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class Registration {

    @ConfigProperty(name = "consul.host") String host;
    @ConfigProperty(name = "consul.port") int port;

    @ConfigProperty(name = "customer-service-port", defaultValue = "9001")
    int customer;
    @ConfigProperty(name = "product-service-port", defaultValue = "9002")
    int product;
    @ConfigProperty(name = "order-service-port", defaultValue = "9003")
    int order;

    /**
     * Register our two services in Consul.
     *
     * Note: this method is called on a worker thread, and so it is allowed to block.
     */
    public void init(@Observes StartupEvent ev, Vertx vertx) {
        ConsulClient client = ConsulClient.create(vertx, new ConsulClientOptions()
                .setHost(host)
                .setPort(port)
        );
        client.registerService(new ServiceOptions().setPort(customer).setAddress("localhost").setName("my-service").setId("customers"));
        client.registerService(new ServiceOptions().setPort(product).setAddress("localhost").setName("my-service").setId("products"));
        client.registerService(new ServiceOptions().setPort(order).setAddress("localhost").setName("my-service").setId("orders"));
    }
}
