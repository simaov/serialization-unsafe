package org.jcoffee.serialization;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Aleksandr Simonchuk on 19.11.14.
 */
public class SerializerFactory {

    private static final Map<Class, SerializerUnsafeI> SERIALIZER_UNSAFE_MAP = new HashMap<>();

    private static final Lock LOCK = new ReentrantLock();

    private SerializerFactory() {
    }

    @SuppressWarnings("unchecked")
    public static <T> SerializerUnsafeI<T> getSerializer(Class<T> clazz) {
        if (!SERIALIZER_UNSAFE_MAP.containsKey(clazz)) {
            try {
                LOCK.lock();
                if (!SERIALIZER_UNSAFE_MAP.containsKey(clazz)) {
                    SERIALIZER_UNSAFE_MAP.put(clazz, new SerializerUnsafe<>(clazz));
                }
            } finally {
                LOCK.unlock();
            }
        }
        return SERIALIZER_UNSAFE_MAP.get(clazz);
    }
}
