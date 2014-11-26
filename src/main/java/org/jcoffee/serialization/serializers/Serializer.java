package org.jcoffee.serialization.serializers;

public abstract class Serializer {
    public abstract byte[] serialize(Object baseObj, long fieldOffset);
    public abstract int deserializeAndPutValue(byte[] bytes, int offset, Object instance, long fieldOffset);
}
