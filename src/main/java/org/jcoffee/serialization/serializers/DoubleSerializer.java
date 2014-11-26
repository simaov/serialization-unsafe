package org.jcoffee.serialization.serializers;

import org.jcoffee.serialization.UnsafeMemory;
import org.jcoffee.serialization.Utils;

import static org.jcoffee.serialization.JavaTypes.JAVA_DOUBLE_SIZE;

public class DoubleSerializer extends Serializer {
    @Override
    public byte[] serialize(Object baseObj, long fieldOffset) {
        return Utils.bytesFromLong(Double.doubleToRawLongBits(UnsafeMemory.getPrimitiveDouble(baseObj, fieldOffset)));
    }

    @Override
    public int deserializeAndPutValue(byte[] bytes, int offset, Object instance, long fieldOffset) {
        UnsafeMemory.getUnsafe().putDouble(instance, fieldOffset, Double.longBitsToDouble(Utils.longFromBytes(bytes, offset)));
        return offset + JAVA_DOUBLE_SIZE;
    }
}
