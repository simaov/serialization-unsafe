package org.jcoffee.serialization.serializers;

import org.jcoffee.serialization.UnsafeMemory;
import org.jcoffee.serialization.Utils;

import static org.jcoffee.serialization.JavaTypes.JAVA_BOOLEAN_SIZE;

public class BooleanSerializer extends Serializer {
    @Override
    public byte[] serialize(Object baseObj, long fieldOffset) {
        return Utils.byteFromBoolean(UnsafeMemory.getPrimitiveBoolean(baseObj, fieldOffset));
    }

    @Override
    public int deserializeAndPutValue(byte[] bytes, int offset, Object instance, long fieldOffset) {
        UnsafeMemory.getUnsafe().putBoolean(instance, fieldOffset, Utils.booleanFromBytes(bytes, offset));
        return offset + JAVA_BOOLEAN_SIZE;
    }
}
