package org.jcoffee.serialization.serializers;

import org.jcoffee.serialization.JavaTypes;
import org.jcoffee.serialization.UnsafeMemory;
import org.jcoffee.serialization.Utils;

public class LongObjectSerializer extends Serializer {
    @Override
    public byte[] serialize(Object baseObj, long fieldOffset) {
        return Utils.bytesFromLong(UnsafeMemory.getLongFieldValue(baseObj, fieldOffset));
    }

    @Override
    public void deserializeAndPutValue(byte[] bytes, int offset, Object instance, long fieldOffset) {
        UnsafeMemory.getUnsafe().putObject(instance, fieldOffset, Utils.longFromBytes(bytes, offset));
    }

    @Override
    public int increaseOffset(int offset) {
        return offset + JavaTypes.JAVA_LONG_SIZE;
    }
}
