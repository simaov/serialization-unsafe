package org.jcoffee.serialization.serializers;

import org.jcoffee.serialization.UnsafeMemory;
import org.jcoffee.serialization.Utils;

import static org.jcoffee.serialization.JavaTypes.JAVA_INTEGER_SIZE;

public class CharArraySerializer extends Serializer {
    @Override
    public byte[] serialize(Object baseObj, long fieldOffset) {
        return UnsafeMemory.getBytesFromCharArray(baseObj, fieldOffset);
    }

    @Override
    public int deserializeAndPutValue(byte[] bytes, int offset, Object instance, long fieldOffset) {
        int charArraySize = Utils.intFromBytes(bytes, offset);
        UnsafeMemory.getUnsafe().putObject(instance, fieldOffset, UnsafeMemory.getCharArrayFromBytes(bytes, offset, charArraySize));
        return offset + charArraySize + JAVA_INTEGER_SIZE;
    }
}
