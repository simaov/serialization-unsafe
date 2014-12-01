package org.jcoffee.serialization;

public interface SerializerUnsafeI<T> {
    byte[] serialize(T obj);
    T deserialize(byte[] bytes) throws Exception;
}
