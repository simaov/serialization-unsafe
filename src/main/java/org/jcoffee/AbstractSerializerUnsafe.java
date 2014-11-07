package org.jcoffee;

import java.lang.reflect.Field;

import static org.jcoffee.JavaTypes.*;

public class AbstractSerializerUnsafe<T> implements SerializerUnsafeI<T> {

    private Class<T> tClass;

    private final Field[] declaredFields;
    private final String[] declaredFieldsNames;
    private final int[] declaredFieldsTypes;
    private final long[] declaredFieldsOffsets;

    public AbstractSerializerUnsafe(Class<T> tClass) {
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
                case JAVA_LONG_OBJECT_TYPE:
                    Long aLong = Utils.longFromBytes(bytes, offset);
                    UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], aLong);
                    offset += 8;
                    break;
                case JAVA_INTEGER_OBJECT_TYPE:
                    Integer aInteger = Utils.intFromBytes(bytes, offset);
                    UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], aInteger);
                    offset += 4;
                    break;
                case JAVA_STRING_TYPE:
                    int size = Utils.intFromBytes(bytes, offset);
                    char[] res = Utils.charsFromBytes(bytes, size, offset);
                    String s = (String) UnsafeMemory.getUnsafe().allocateInstance(String.class);
                    UnsafeMemory.getUnsafe().putObject(s, UnsafeMemory.charValueFieldOffset, res);
                    UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], s);
                    break;
            }
        }

        return instance;
    }
}
