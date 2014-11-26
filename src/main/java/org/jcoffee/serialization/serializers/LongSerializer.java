package org.jcoffee.serialization.serializers;

import org.jcoffee.serialization.UnsafeMemory;
import org.jcoffee.serialization.Utils;

import static org.jcoffee.serialization.JavaTypes.JAVA_LONG_SIZE;

public class LongSerializer extends Serializer {
    @Override
    public byte[] serialize(Object baseObj, long fieldOffset) {
        return Utils.bytesFromLong(UnsafeMemory.getPrimitiveLong(baseObj, fieldOffset));
    }

    @Override
    public int deserializeAndPutValue(byte[] bytes, int offset, Object instance, long fieldOffset) {
        UnsafeMemory.getUnsafe().putLong(instance, fieldOffset, Utils.longFromBytes(bytes, offset));
        return offset + JAVA_LONG_SIZE;
    }
}
