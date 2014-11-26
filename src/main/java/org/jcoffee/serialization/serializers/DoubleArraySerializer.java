package org.jcoffee.serialization.serializers;

import org.jcoffee.serialization.UnsafeMemory;
import org.jcoffee.serialization.Utils;

import static org.jcoffee.serialization.JavaTypes.JAVA_INTEGER_SIZE;

public class DoubleArraySerializer extends Serializer {
    @Override
    public byte[] serialize(Object baseObj, long fieldOffset) {
        return UnsafeMemory.getBytesFromDoubleArray(baseObj, fieldOffset);
    }

    @Override
    public int deserializeAndPutValue(byte[] bytes, int offset, Object instance, long fieldOffset) {
        int doubleArraySize = Utils.intFromBytes(bytes, offset);
        UnsafeMemory.getUnsafe().putObject(instance, fieldOffset, UnsafeMemory.getDoubleArrayFromBytes(bytes, offset, doubleArraySize));
        return offset + doubleArraySize + JAVA_INTEGER_SIZE;
    }
}
