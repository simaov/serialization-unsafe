package org.jcoffee.serialization.serializers;

public abstract class Serializer {
    public abstract byte[] serialize(Object baseObj, long fieldOffset);
    public abstract void deserializeAndPutValue(byte[] bytes, int offset, Object instance, long fieldOffset);
    public abstract int increaseOffset(int offset);
}
