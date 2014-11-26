package org.jcoffee.serialization.serializers;

import org.jcoffee.serialization.UnsafeMemory;
import org.jcoffee.serialization.Utils;

import static org.jcoffee.serialization.JavaTypes.JAVA_INTEGER_SIZE;

public class StringSerializer extends Serializer {
    @Override
    public byte[] serialize(Object baseObj, long fieldOffset) {
        return UnsafeMemory.getBytesFromString(baseObj, fieldOffset);
    }

    @Override
    public int deserializeAndPutValue(byte[] bytes, int offset, Object instance, long fieldOffset) {
        int size = Utils.intFromBytes(bytes, offset);
        Object string = UnsafeMemory.allocateInstance(String.class);
        UnsafeMemory.getUnsafe().putObject(string, UnsafeMemory.charArrayValueFieldOffset, UnsafeMemory.getCharArrayFromBytes(bytes, offset, size));
        UnsafeMemory.getUnsafe().putObject(instance, fieldOffset, string);
        return offset + size + JAVA_INTEGER_SIZE;
    }
}
