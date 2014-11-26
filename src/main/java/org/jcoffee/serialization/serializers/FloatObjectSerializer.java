package org.jcoffee.serialization.serializers;

import org.jcoffee.serialization.UnsafeMemory;
import org.jcoffee.serialization.Utils;

import static org.jcoffee.serialization.JavaTypes.JAVA_FLOAT_SIZE;

public class FloatObjectSerializer extends Serializer {
    @Override
    public byte[] serialize(Object baseObj, long fieldOffset) {
        return Utils.bytesFromInt(Float.floatToRawIntBits(UnsafeMemory.getFloatFieldValue(baseObj, fieldOffset)));
    }

    @Override
    public int deserializeAndPutValue(byte[] bytes, int offset, Object instance, long fieldOffset) {
        UnsafeMemory.getUnsafe().putObject(instance, fieldOffset, Float.intBitsToFloat(Utils.intFromBytes(bytes, offset)));
        return offset + JAVA_FLOAT_SIZE;
    }
}
