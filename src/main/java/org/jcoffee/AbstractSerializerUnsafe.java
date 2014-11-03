package org.jcoffee;

import java.lang.reflect.Field;

import static org.jcoffee.JavaTypes.*;
import static org.jcoffee.JavaTypes.JAVA_LONG_SIZE;
import static org.jcoffee.JavaTypes.JAVA_LONG_TYPE;

public class AbstractSerializerUnsafe<T> implements SerializerUnsafeI<T> {

    private Class<T> tClass;

    private final Field[] declaredFields;
    private final String[] declaredFieldsNames;
    private final String[] declaredFieldsTypes;
    private final long[] declaredFieldsOffsets;

    public AbstractSerializerUnsafe(Class<T> tClass) {
        this.tClass = tClass;
        declaredFields = tClass.getDeclaredFields();
        declaredFieldsNames = new String[declaredFields.length];
        declaredFieldsTypes = new String[declaredFields.length];
        declaredFieldsOffsets = new long[declaredFields.length];

        for (int i = 0; i < declaredFields.length; i++) {
            declaredFieldsNames[i] = declaredFields[i].getName();
            declaredFieldsTypes[i] = declaredFields[i].getType().getName();
            declaredFieldsOffsets[i] = UnsafeMemory.getUnsafe().objectFieldOffset(declaredFields[i]);
        }
    }

    @Override
    public byte[] serialize(T obj) throws Exception {
        int bufferSize = 0;
        byte[] buffer;
        final Object[] declaredFieldsValues = new Object[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            String type = declaredFieldsTypes[i];
            if (type.equals(JAVA_LONG_TYPE)) {
                declaredFieldsValues[i] = UnsafeMemory.getLongFieldValue(obj, declaredFields[i]);
                bufferSize += JAVA_LONG_SIZE;
            } else if (type.equals(JAVA_INTEGER_TYPE)){
                declaredFieldsValues[i] = UnsafeMemory.getIntegerFieldValue(obj, declaredFields[i]);
                bufferSize += JAVA_INTEGER_SIZE;
            } else if (type.equals(JAVA_STRING_TYPE)){
                byte[] bytes = Utils.bytesFromChars(UnsafeMemory.getStringFieldValue(obj, declaredFields[i]));
                declaredFieldsValues[i] = bytes;
                bufferSize += bytes.length;
            }
        }

        buffer = new byte[bufferSize];
        int index = 0;
        for (int i = 0; i < declaredFieldsValues.length; i++) {
            byte[] bytes = (byte[]) declaredFieldsValues[i];
            for (int j = 0; j < bytes.length; j++) {
                buffer[index++] = bytes[j];
            }
        }

        return buffer;
    }

    @Override
    public T deserialize(byte[] bytes) throws IllegalAccessException, InstantiationException {
        int offset = 0;
        T instance = (T) UnsafeMemory.getUnsafe().allocateInstance(tClass);
        for (int i = 0; i < declaredFields.length; i++) {
            String type = declaredFieldsTypes[i];
            if (type.equals(JAVA_LONG_TYPE)) {
                Long aLong = Utils.longFromBytes(bytes, offset);
                UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], aLong);
                offset += 8;
            } else if (type.equals(JAVA_INTEGER_TYPE)){
                Integer aInteger  = Utils.intFromBytes(bytes, offset);
                UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], aInteger);
                offset += 4;
            } else if (type.equals(JAVA_STRING_TYPE)){
                int size  = Utils.intFromBytes(bytes, offset);
                char[] res = Utils.charsFromBytes(bytes, size, offset);
                System.out.println(new String(res));
            }
        }

        return instance;
    }
}
