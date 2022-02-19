package clone.airbnbmongo.common;

public record FixProperty(String key, Object value) {

    public static FixProperty of(String key, Object value) {
        return new FixProperty(key, value);
    }
}
