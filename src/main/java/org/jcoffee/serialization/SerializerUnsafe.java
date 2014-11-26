package org.jcoffee.serialization;

import org.jcoffee.serialization.serializers.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;

import static org.jcoffee.serialization.JavaTypes.*;

public class SerializerUnsafe<T> implements SerializerUnsafeI<T> {

    private final Class<T> tClass;

    private final Field[] declaredFields;
    private final HashMap<Integer, Serializer> serializers;
    private final int[] declaredFieldsTypes;
    private final long[] declaredFieldsOffsets;

    public SerializerUnsafe(Class<T> tClass) {
        this.tClass = tClass;
        declaredFields = tClass.getDeclaredFields();
        declaredFieldsTypes = new int[declaredFields.length];
        declaredFieldsOffsets = new long[declaredFields.length];
        serializers = new HashMap<>();

        for (int i = 0; i < declaredFields.length; i++) {
            if (declaredFields[i].getType().getName().equals(boolean.class.getName())) {
                declaredFieldsTypes[i] = JAVA_BOOLEAN_TYPE;
                serializers.put(JAVA_BOOLEAN_TYPE, new BooleanSerializer());
            } else if (declaredFields[i].getType().getName().equals(int.class.getName())) {
                declaredFieldsTypes[i] = JAVA_INT_TYPE;
                serializers.put(JAVA_INT_TYPE, new IntegerSerializer());
            } else if (declaredFields[i].getType().getName().equals(long.class.getName())) {
                declaredFieldsTypes[i] = JAVA_LONG_TYPE;
                serializers.put(JAVA_LONG_TYPE, new LongSerializer());
            } else if (declaredFields[i].getType().getName().equals(float.class.getName())) {
                declaredFieldsTypes[i] = JAVA_FLOAT_TYPE;
                serializers.put(JAVA_FLOAT_TYPE, new FloatSerializer());
            } else if (declaredFields[i].getType().getName().equals(double.class.getName())) {
                declaredFieldsTypes[i] = JAVA_DOUBLE_TYPE;
                serializers.put(JAVA_DOUBLE_TYPE, new DoubleSerializer());
            } else if (declaredFields[i].getType().getName().equals(Boolean.class.getName())) {
                declaredFieldsTypes[i] = JAVA_BOOLEAN_OBJECT_TYPE;
                serializers.put(JAVA_BOOLEAN_OBJECT_TYPE, new BooleanObjectSerializer());
            } else if (declaredFields[i].getType().getName().equals(Integer.class.getName())) {
                declaredFieldsTypes[i] = JAVA_INTEGER_OBJECT_TYPE;
                serializers.put(JAVA_INTEGER_OBJECT_TYPE, new IntegerObjectSerializer());
            } else if (declaredFields[i].getType().getName().equals(Long.class.getName())) {
                declaredFieldsTypes[i] = JAVA_LONG_OBJECT_TYPE;
                serializers.put(JAVA_LONG_OBJECT_TYPE, new LongObjectSerializer());
            } else if (declaredFields[i].getType().getName().equals(Float.class.getName())) {
                declaredFieldsTypes[i] = JAVA_FLOAT_OBJECT_TYPE;
                serializers.put(JAVA_FLOAT_OBJECT_TYPE, new FloatObjectSerializer());
            } else if (declaredFields[i].getType().getName().equals(Double.class.getName())) {
                declaredFieldsTypes[i] = JAVA_DOUBLE_OBJECT_TYPE;
                serializers.put(JAVA_DOUBLE_OBJECT_TYPE, new DoubleObjectSerializer());
            } else if (declaredFields[i].getType().getName().equals(char[].class.getName())) {
                declaredFieldsTypes[i] = JAVA_CHAR_ARRAY_TYPE;
                serializers.put(JAVA_CHAR_ARRAY_TYPE, new CharArraySerializer());
            } else if (declaredFields[i].getType().getName().equals(long[].class.getName())) {
                declaredFieldsTypes[i] = JAVA_LONG_ARRAY_TYPE;
                serializers.put(JAVA_LONG_ARRAY_TYPE, new LongArraySerializer());
            } else if (declaredFields[i].getType().getName().equals(double[].class.getName())) {
                declaredFieldsTypes[i] = JAVA_DOUBLE_ARRAY_TYPE;
                serializers.put(JAVA_DOUBLE_ARRAY_TYPE, new DoubleArraySerializer());
            } else if (declaredFields[i].getType().getName().equals(String.class.getName())) {
                declaredFieldsTypes[i] = JAVA_STRING_TYPE;
                serializers.put(JAVA_STRING_TYPE, new StringSerializer());
            } else if (declaredFields[i].getType().getName().equals(UUID.class.getName())) {
                declaredFieldsTypes[i] = JAVA_UUID_TYPE;
                serializers.put(JAVA_UUID_TYPE, new UuidSerializer());
            }
            declaredFieldsOffsets[i] = UnsafeMemory.getFieldOffset(declaredFields[i]);
        }
    }

    @Override
    public byte[] serialize(T obj) throws Exception {
        int bufferSize = 0;
        final byte[][] declaredFieldsValues = new byte[declaredFields.length][];

        for (int i = 0; i < declaredFields.length; i++) {
            byte[] bytes = serializers.get(declaredFieldsTypes[i]).serialize(obj, declaredFieldsOffsets[i]);
            declaredFieldsValues[i] = bytes;
            bufferSize += bytes.length;
        }

        byte[] buffer = new byte[bufferSize];
        int index = 0;
        for (byte[] bytes : declaredFieldsValues) {
            System.arraycopy(bytes, 0, buffer, index, bytes.length);
            index += bytes.length;
        }

        return buffer;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(byte[] bytes) throws IllegalAccessException, InstantiationException {
        int offset = 0;
        Object instance = UnsafeMemory.allocateInstance(tClass);
        for (int i = 0; i < declaredFields.length; i++) {
            offset = serializers.get(declaredFieldsTypes[i]).deserializeAndPutValue(bytes, offset, instance, declaredFieldsOffsets[i]);
        }
        return (T) instance;
    }
}
