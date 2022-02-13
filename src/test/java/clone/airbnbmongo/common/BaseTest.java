package clone.airbnbmongo.common;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.BuilderArbitraryGenerator;

public class BaseTest {

    public <T> T createInstance(Class<T> clazz) {
        FixtureMonkey fixture = FixtureMonkey.builder()
                .putGenerator(clazz, BuilderArbitraryGenerator.INSTANCE)
                .build();

        return fixture.giveMeOne(clazz);
    }

    public <T> T createInstanceWithNullProperty(Class<T> clazz, String propertyName) {
        FixtureMonkey fixture = FixtureMonkey.builder()
                .putGenerator(clazz, BuilderArbitraryGenerator.INSTANCE)
                .build();

        return fixture.giveMeBuilder(clazz)
                .setNull(propertyName)
                .sample();
    }
}
