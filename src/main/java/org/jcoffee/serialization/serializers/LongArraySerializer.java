package org.jcoffee.serialization.serializers;

import org.jcoffee.serialization.UnsafeMemory;
import org.jcoffee.serialization.Utils;

import static org.jcoffee.serialization.JavaTypes.JAVA_INTEGER_SIZE;

public class LongArraySerializer extends Serializer {
    @Override
    public byte[] serialize(Object baseObj, long fieldOffset) {
        return UnsafeMemory.getBytesFromLongArray(baseObj, fieldOffset);
    }

    @Override
    public int deserializeAndPutValue(byte[] bytes, int offset, Object instance, long fieldOffset) {
        int longArraySize = Utils.intFromBytes(bytes, offset);
        UnsafeMemory.getUnsafe().putObject(instance, fieldOffset, UnsafeMemory.getLongArrayFromBytes(bytes, offset, longArraySize));
        return offset + longArraySize + JAVA_INTEGER_SIZE;
    }
}
