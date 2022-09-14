package it.stellato.cgm.resource;

import io.quarkus.test.common.DevServicesContext;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Optional;

public class MongoResource implements QuarkusTestResourceLifecycleManager, DevServicesContext.ContextAware {

    private Optional<String> containerNetworkId;
    private MongoDBContainer container;

    @Override
    public void setIntegrationTestContext(DevServicesContext context) {
        containerNetworkId = context.containerNetworkId();
    }

    @Override
    public Map<String, String> start() {
        // start a container making sure to call withNetworkMode() with the value of containerNetworkId if present
        container = new MongoDBContainer("mongo:5.0.9").withLogConsumer(outputFrame -> {});

        // apply the network to the container
        containerNetworkId.ifPresent(container::withNetworkMode);

        // start container before retrieving its URL or other properties
        container.start();

        String connectionString = container.getConnectionString();

        // return a map containing the configuration the application needs to use the service
        return ImmutableMap.of(
                "quarkus.mongodb.connection-string", connectionString);
    }

    @Override
    public void stop() {
        // close container
    }
}
