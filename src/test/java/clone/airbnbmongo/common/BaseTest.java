package clone.airbnbmongo.common;

import clone.airbnbmongo.accommodation.domain.Accommodation;
import com.navercorp.fixturemonkey.ArbitraryBuilder;
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

    public <T> T createInstanceWithFixProperty(Class<T> clazz, FixProperty property) {
        FixtureMonkey fixture = FixtureMonkey.builder()
                .putGenerator(clazz, BuilderArbitraryGenerator.INSTANCE)
                .build();

        return fixture.giveMeBuilder(clazz)
                .set(property.key(), property.value())
                .sample();
    }

    public <T> T createInstanceWithFixProperties(Class<T> clazz, FixProperty firstProperty, FixProperty... properties) {
        FixtureMonkey fixture = FixtureMonkey.builder()
                .putGenerator(clazz, BuilderArbitraryGenerator.INSTANCE)
                .build();

        ArbitraryBuilder<T> builder = fixture.giveMeBuilder(clazz);

        builder.set(firstProperty.key(), firstProperty.value());

        for (FixProperty property : properties) {
            builder.set(property.key(), property.value());
        }

        return builder.sample();
    }
}
