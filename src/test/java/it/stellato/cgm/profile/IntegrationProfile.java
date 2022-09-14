package it.stellato.cgm.profile;

import io.quarkus.test.junit.QuarkusTestProfile;
import it.stellato.cgm.resource.MongoResource;

import java.util.Collections;
import java.util.List;

public class IntegrationProfile implements QuarkusTestProfile {

    @Override
    public String getConfigProfile() {
        return "emb";
    }

    @Override
    public List<TestResourceEntry> testResources() {

        return Collections.singletonList(new TestResourceEntry(MongoResource.class));

    }
}
