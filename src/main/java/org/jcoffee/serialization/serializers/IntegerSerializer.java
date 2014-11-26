package org.jcoffee.serialization.serializers;

import org.jcoffee.serialization.UnsafeMemory;
import org.jcoffee.serialization.Utils;

import static org.jcoffee.serialization.JavaTypes.JAVA_INTEGER_SIZE;

public class IntegerSerializer extends Serializer {
    @Override
    public byte[] serialize(Object baseObj, long fieldOffset) {
        return Utils.bytesFromInt(UnsafeMemory.getPrimitiveInt(baseObj, fieldOffset));
    }

    @Override
    public int deserializeAndPutValue(byte[] bytes, int offset, Object instance, long fieldOffset) {
        UnsafeMemory.getUnsafe().putInt(instance, fieldOffset, Utils.intFromBytes(bytes, offset));
        return offset + JAVA_INTEGER_SIZE;
    }
}
