package org.jcoffee.serialization.serializers;

import org.jcoffee.serialization.UnsafeMemory;
import org.jcoffee.serialization.Utils;

import java.util.UUID;

import static org.jcoffee.serialization.JavaTypes.JAVA_LONG_SIZE;

public class UuidSerializer extends Serializer {
    @Override
    public byte[] serialize(Object baseObj, long fieldOffset) {
        return UnsafeMemory.getBytesFromUUID(UnsafeMemory.getFieldObject(baseObj, fieldOffset));
    }

    @Override
    public int deserializeAndPutValue(byte[] bytes, int offset, Object instance, long fieldOffset) {
        Object uuid = UnsafeMemory.allocateInstance(UUID.class);
        UnsafeMemory.getUnsafe().putLong(uuid, UnsafeMemory.mostSigBitsFieldOffset, Utils.longFromBytes(bytes, offset));
        UnsafeMemory.getUnsafe().putLong(uuid, UnsafeMemory.leastSigBitsFieldOffset, Utils.longFromBytes(bytes, offset + JAVA_LONG_SIZE));
        UnsafeMemory.getUnsafe().putObject(instance, fieldOffset, uuid);
        return offset + (JAVA_LONG_SIZE << 1);
    }
}
