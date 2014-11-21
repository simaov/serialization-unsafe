package org.jcoffee.serialization;

public interface SerializerUnsafeI<T> {
    byte[] serialize(T obj) throws Exception;
    T deserialize(byte[] bytes) throws Exception;
}