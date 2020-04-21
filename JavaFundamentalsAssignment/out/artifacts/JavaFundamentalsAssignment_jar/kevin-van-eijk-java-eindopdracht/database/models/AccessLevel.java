package database.models;

import java.util.HashMap;
import java.util.Map;

public enum AccessLevel {
    BASIC(1),
    EDITOR(2),
    ADMIN(3);

    private int value;
    private static Map map = new HashMap<>();

    AccessLevel(int value) {
        this.value = value;
    }

    static {
        for (AccessLevel pageType : AccessLevel.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static AccessLevel valueOf(int pageType) {
        return (AccessLevel) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
