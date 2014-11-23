package org.jcoffee.serialization;

import java.util.HashMap;
import java.util.Map;

public class SerializerFactory {

    private static final Map<Class, SerializerUnsafe> SERIALIZER_UNSAFE_MAP = new HashMap<>();

    private SerializerFactory() {}

    public static <T> SerializerUnsafe<T> getSerializer(Class<T> clazz) {
        if (!SERIALIZER_UNSAFE_MAP.containsKey(clazz)) {
            synchronized (SERIALIZER_UNSAFE_MAP) {
                if (!SERIALIZER_UNSAFE_MAP.containsKey(clazz)) {
                    SERIALIZER_UNSAFE_MAP.put(clazz, new SerializerUnsafe<>(clazz));
                }
            }
        }
        return SERIALIZER_UNSAFE_MAP.get(clazz);
    }
}
