package org.jcoffee.serialization;

/**
 * Created by Aleksandr Simonchuk on 19.11.14.
 */
public interface SerializerUnsafeI<T> {
    byte[] serialize(T obj);
    T deserialize(byte[] bytes) throws Exception;
}
