package org.jcoffee.serialization;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.jcoffee.serialization.JavaTypes.*;

public class SerializerUnsafe<T> implements SerializerUnsafeI<T> {

    private Class<T> tClass;

    private final Field[] declaredFields;
    private final String[] declaredFieldsNames;
    private final int[] declaredFieldsTypes;
    private final long[] declaredFieldsOffsets;

    public SerializerUnsafe(Class<T> tClass) {
        this.tClass = tClass;
        declaredFields = tClass.getDeclaredFields();
        declaredFieldsNames = new String[declaredFields.length];
        declaredFieldsTypes = new int[declaredFields.length];
        declaredFieldsOffsets = new long[declaredFields.length];

        for (int i = 0; i < declaredFields.length; i++) {
            declaredFieldsNames[i] = declaredFields[i].getName();

            if (declaredFields[i].getType().getName().equals(int.class.getName())) {
                declaredFieldsTypes[i] = JAVA_INT_TYPE;
            } else if (declaredFields[i].getType().getName().equals(long.class.getName())) {
                declaredFieldsTypes[i] = JAVA_LONG_TYPE;
            } else if (declaredFields[i].getType().getName().equals(char[].class.getName())) {
                declaredFieldsTypes[i] = JAVA_CHAR_ARRAY_TYPE;
            } else if (declaredFields[i].getType().getName().equals(Integer.class.getName())) {
                declaredFieldsTypes[i] = JAVA_INTEGER_OBJECT_TYPE;
            } else if (declaredFields[i].getType().getName().equals(Long.class.getName())) {
                declaredFieldsTypes[i] = JAVA_LONG_OBJECT_TYPE;
            } else if (declaredFields[i].getType().getName().equals(String.class.getName())) {
                declaredFieldsTypes[i] = JAVA_STRING_TYPE;
            } else if (declaredFields[i].getType().getName().equals(UUID.class.getName())) {
                declaredFieldsTypes[i] = JAVA_UUID_TYPE;
            }

            declaredFieldsOffsets[i] = UnsafeMemory.getUnsafe().objectFieldOffset(declaredFields[i]);
        }
    }

    @Override
    public byte[] serialize(T obj) throws Exception {
        int bufferSize = 0;
        final byte[][] declaredFieldsValues = new byte[declaredFields.length][];

        for (int i = 0; i < declaredFields.length; i++) {

            switch (declaredFieldsTypes[i]) {
                case JAVA_INT_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromInt(UnsafeMemory.getPrimitiveInt(obj, declaredFieldsOffsets[i]));
                    bufferSize += JAVA_INTEGER_SIZE;
                    break;
                case JAVA_LONG_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromLong(UnsafeMemory.getPrimitiveLong(obj, declaredFieldsOffsets[i]));
                    bufferSize += JAVA_LONG_SIZE;
                    break;
                case JAVA_CHAR_ARRAY_TYPE:
                    byte[] bytes = Utils.bytesFromChars(UnsafeMemory.getChars(obj, declaredFieldsOffsets[i]));
                    declaredFieldsValues[i] = bytes;
                    bufferSize += bytes.length;
                    break;
                case JAVA_INTEGER_OBJECT_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromInt(UnsafeMemory.getIntegerFieldValue(obj, declaredFields[i]));
                    bufferSize += JAVA_INTEGER_SIZE;
                    break;
                case JAVA_LONG_OBJECT_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromLong(UnsafeMemory.getLongFieldValue(obj, declaredFields[i]));
                    bufferSize += JAVA_LONG_SIZE;
                    break;
                case JAVA_STRING_TYPE:
                    byte[] b = Utils.bytesFromChars(UnsafeMemory.getStringFieldValue(obj, declaredFields[i]));
                    declaredFieldsValues[i] = b;
                    bufferSize += b.length;
                    break;
                case JAVA_UUID_TYPE:
                    byte[] uuidBytes = UnsafeMemory.getBytesFromUUID(UnsafeMemory.getUnsafe().getObject(obj, declaredFieldsOffsets[i]));
                    declaredFieldsValues[i] = uuidBytes;
                    bufferSize += uuidBytes.length;
                    break;
            }
        }

        byte[] buffer = new byte[bufferSize];
        int index = 0;
        for (int i = 0; i < declaredFieldsValues.length; i++) {
            byte[] bytes = declaredFieldsValues[i];
            System.arraycopy(bytes, 0, buffer, index, bytes.length);
            index += bytes.length;
        }

        return buffer;
    }

    @Override
    public T deserialize(byte[] bytes) throws IllegalAccessException, InstantiationException {
        int offset = 0;
        T instance = (T) UnsafeMemory.getUnsafe().allocateInstance(tClass);
        for (int i = 0; i < declaredFields.length; i++) {
            switch (declaredFieldsTypes[i]) {
                case JAVA_INT_TYPE:
                    int primInt = Utils.intFromBytes(bytes, offset);
                    UnsafeMemory.getUnsafe().putInt(instance, declaredFieldsOffsets[i], primInt);
                    offset += JAVA_INTEGER_SIZE;
                    break;
                case JAVA_LONG_TYPE:
                    long primLong = Utils.longFromBytes(bytes, offset);
                    UnsafeMemory.getUnsafe().putLong(instance, declaredFieldsOffsets[i], primLong);
                    offset += JAVA_LONG_SIZE;
                    break;
                case JAVA_INTEGER_OBJECT_TYPE:
                    Integer aInteger = Utils.intFromBytes(bytes, offset);
                    UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], aInteger);
                    offset += JAVA_INTEGER_SIZE;
                    break;
                case JAVA_LONG_OBJECT_TYPE:
                    Long aLong = Utils.longFromBytes(bytes, offset);
                    UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], aLong);
                    offset += JAVA_LONG_SIZE;
                    break;
                case JAVA_CHAR_ARRAY_TYPE:
                    int cSize = Utils.intFromBytes(bytes, offset);
                    char[] chars = Utils.charsFromBytes(bytes, cSize, offset);
                    UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], chars);
                    offset += cSize + JAVA_INTEGER_SIZE;
                    break;
                case JAVA_STRING_TYPE:
                    int size = Utils.intFromBytes(bytes, offset);
                    char[] res = Utils.charsFromBytes(bytes, size, offset);
                    Object s = UnsafeMemory.getUnsafe().allocateInstance(String.class);
                    UnsafeMemory.getUnsafe().putObject(s, UnsafeMemory.charValueFieldOffset, res);
                    UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], s);
                    offset += size + JAVA_INTEGER_SIZE;
                    break;
                case JAVA_UUID_TYPE:
                    long mostSigBits = Utils.longFromBytes(bytes, offset);
                    offset += JAVA_LONG_SIZE;
                    long leastSigBits = Utils.longFromBytes(bytes, offset);
                    offset += JAVA_LONG_SIZE;
                    Object uuid = UnsafeMemory.getUnsafe().allocateInstance(UUID.class);
                    UnsafeMemory.getUnsafe().putLong(uuid, UnsafeMemory.mostSigBitsFieldOffset, mostSigBits);
                    UnsafeMemory.getUnsafe().putLong(uuid, UnsafeMemory.leastSigBitsFieldOffset, leastSigBits);
                    UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], uuid);
                    break;
            }
        }

        return instance;
    }
}
