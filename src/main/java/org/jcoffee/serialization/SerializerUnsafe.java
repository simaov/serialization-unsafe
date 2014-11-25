package org.jcoffee.serialization;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.jcoffee.serialization.JavaTypes.*;
import static org.jcoffee.serialization.UnsafeMemory.*;

public class SerializerUnsafe<T> implements SerializerUnsafeI<T> {

    private final Class<T> tClass;

    private final Field[] declaredFields;
    private final int[] declaredFieldsTypes;
    private final long[] declaredFieldsOffsets;
    private int defaultSize = 0;

    public SerializerUnsafe(Class<T> tClass) {
        this.tClass = tClass;
        declaredFields = tClass.getDeclaredFields();
        declaredFieldsTypes = new int[declaredFields.length];
        declaredFieldsOffsets = new long[declaredFields.length];

        for (int i = 0; i < declaredFields.length; i++) {

            if (declaredFields[i].getType().getName().equals(boolean.class.getName())) {
                declaredFieldsTypes[i] = JAVA_BOOLEAN_TYPE;
                defaultSize += JAVA_BOOLEAN_SIZE;
            } else if (declaredFields[i].getType().getName().equals(int.class.getName())) {
                declaredFieldsTypes[i] = JAVA_INT_TYPE;
                defaultSize += JAVA_INTEGER_SIZE;
            } else if (declaredFields[i].getType().getName().equals(long.class.getName())) {
                declaredFieldsTypes[i] = JAVA_LONG_TYPE;
                defaultSize += JAVA_LONG_SIZE;
            } else if (declaredFields[i].getType().getName().equals(float.class.getName())) {
                declaredFieldsTypes[i] = JAVA_FLOAT_TYPE;
                defaultSize += JAVA_FLOAT_SIZE;
            } else if (declaredFields[i].getType().getName().equals(double.class.getName())) {
                declaredFieldsTypes[i] = JAVA_DOUBLE_TYPE;
                defaultSize += JAVA_DOUBLE_SIZE;
            } else if (declaredFields[i].getType().getName().equals(Boolean.class.getName())) {
                declaredFieldsTypes[i] = JAVA_BOOLEAN_OBJECT_TYPE;
                defaultSize += JAVA_BOOLEAN_SIZE;
            } else if (declaredFields[i].getType().getName().equals(Integer.class.getName())) {
                declaredFieldsTypes[i] = JAVA_INTEGER_OBJECT_TYPE;
                defaultSize += JAVA_INTEGER_SIZE;
            } else if (declaredFields[i].getType().getName().equals(Long.class.getName())) {
                declaredFieldsTypes[i] = JAVA_LONG_OBJECT_TYPE;
                defaultSize += JAVA_LONG_SIZE;
            } else if (declaredFields[i].getType().getName().equals(Float.class.getName())) {
                declaredFieldsTypes[i] = JAVA_FLOAT_OBJECT_TYPE;
                defaultSize += JAVA_FLOAT_SIZE;
            } else if (declaredFields[i].getType().getName().equals(Double.class.getName())) {
                declaredFieldsTypes[i] = JAVA_DOUBLE_OBJECT_TYPE;
                defaultSize += JAVA_DOUBLE_SIZE;
            } else if (declaredFields[i].getType().getName().equals(char[].class.getName())) {
                declaredFieldsTypes[i] = JAVA_CHAR_ARRAY_TYPE;
            } else if (declaredFields[i].getType().getName().equals(String.class.getName())) {
                declaredFieldsTypes[i] = JAVA_STRING_TYPE;
            } else if (declaredFields[i].getType().getName().equals(UUID.class.getName())) {
                declaredFieldsTypes[i] = JAVA_UUID_TYPE;
                defaultSize += JAVA_LONG_SIZE << 1;
            }

            declaredFieldsOffsets[i] = UnsafeMemory.getFieldOffset(declaredFields[i]);
        }
    }

    @Override
    public byte[] serialize(T obj) throws Exception {
        int bufferSize = defaultSize;
        final byte[][] declaredFieldsValues = new byte[declaredFields.length][];

        for (int i = 0; i < declaredFields.length; i++) {

            switch (declaredFieldsTypes[i]) {
                case JAVA_BOOLEAN_TYPE:
                    declaredFieldsValues[i] = Utils.byteFromBoolean(UnsafeMemory.getPrimitiveBoolean(obj, declaredFieldsOffsets[i]));
                    break;
                case JAVA_INT_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromInt(UnsafeMemory.getPrimitiveInt(obj, declaredFieldsOffsets[i]));
                    break;
                case JAVA_LONG_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromLong(UnsafeMemory.getPrimitiveLong(obj, declaredFieldsOffsets[i]));
                    break;
                case JAVA_FLOAT_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromInt(Float.floatToRawIntBits(UnsafeMemory.getPrimitiveFloat(obj, declaredFieldsOffsets[i])));
                    break;
                case JAVA_DOUBLE_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromLong(Double.doubleToRawLongBits(UnsafeMemory.getPrimitiveDouble(obj, declaredFieldsOffsets[i])));
                    break;
                case JAVA_BOOLEAN_OBJECT_TYPE:
                    declaredFieldsValues[i] = Utils.byteFromBoolean(UnsafeMemory.getBooleanFieldValue(obj, declaredFieldsOffsets[i]));
                    break;
                case JAVA_INTEGER_OBJECT_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromInt(UnsafeMemory.getIntegerFieldValue(obj, declaredFieldsOffsets[i]));
                    break;
                case JAVA_LONG_OBJECT_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromLong(UnsafeMemory.getLongFieldValue(obj, declaredFieldsOffsets[i]));
                    break;
                case JAVA_FLOAT_OBJECT_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromInt(Float.floatToRawIntBits(UnsafeMemory.getFloatFieldValue(obj, declaredFieldsOffsets[i])));
                    break;
                case JAVA_DOUBLE_OBJECT_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromLong(Double.doubleToRawLongBits(UnsafeMemory.getDoubleFieldValue(obj, declaredFieldsOffsets[i])));
                    break;
                case JAVA_CHAR_ARRAY_TYPE:
                    byte[] bytes = UnsafeMemory.getBytesFromCharArray(obj, declaredFieldsOffsets[i]);
                    declaredFieldsValues[i] = bytes;
                    bufferSize += bytes.length;
                    break;
                case JAVA_STRING_TYPE:
                    byte[] b = UnsafeMemory.getBytesFromString(obj, declaredFieldsOffsets[i]);
                    declaredFieldsValues[i] = b;
                    bufferSize += b.length;
                    break;
                case JAVA_UUID_TYPE:
                    declaredFieldsValues[i] = UnsafeMemory.getBytesFromUUID(UnsafeMemory.getFieldObject(obj, declaredFieldsOffsets[i]));
                    break;
            }
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
            switch (declaredFieldsTypes[i]) {
                case JAVA_BOOLEAN_TYPE:
                    UnsafeMemory.getUnsafe().putBoolean(instance, declaredFieldsOffsets[i], Utils.booleanFromBytes(bytes, offset));
                    offset += 1;
                    break;
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
                case JAVA_FLOAT_TYPE:
                    UnsafeMemory.getUnsafe().putFloat(instance, declaredFieldsOffsets[i], Float.intBitsToFloat(Utils.intFromBytes(bytes, offset)));
                    offset += JAVA_FLOAT_SIZE;
                    break;
                case JAVA_DOUBLE_TYPE:
                    UnsafeMemory.getUnsafe().putDouble(instance, declaredFieldsOffsets[i], Double.longBitsToDouble(Utils.longFromBytes(bytes, offset)));
                    offset += JAVA_DOUBLE_SIZE;
                    break;
                case JAVA_BOOLEAN_OBJECT_TYPE:
                    Object b = UnsafeMemory.allocateInstance(Boolean.class);
                    UnsafeMemory.getUnsafe().putBoolean(b, booleanValueFieldOffset, Utils.booleanFromBytes(bytes, offset));
                    UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], b);
                    offset += 1;
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
                case JAVA_FLOAT_OBJECT_TYPE:
                    Object floatObj = UnsafeMemory.allocateInstance(Float.class);
                    UnsafeMemory.getUnsafe().putFloat(floatObj, floatValueFieldOffset, Float.intBitsToFloat(Utils.intFromBytes(bytes, offset)));
                    UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], floatObj);
                    offset += JAVA_FLOAT_SIZE;
                    break;
                case JAVA_DOUBLE_OBJECT_TYPE:
                    Object doubleObj = UnsafeMemory.allocateInstance(Double.class);
                    UnsafeMemory.getUnsafe().putDouble(doubleObj, doubleValueFieldOffset, Double.longBitsToDouble(Utils.longFromBytes(bytes, offset)));
                    UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], doubleObj);
                    offset += JAVA_DOUBLE_SIZE;
                    break;
                case JAVA_CHAR_ARRAY_TYPE:
                    int cSize = Utils.intFromBytes(bytes, offset);
                    char[] chs = UnsafeMemory.getCharArrayFromBytes(bytes, offset, cSize);
                    UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], chs);
                    offset += cSize + JAVA_INTEGER_SIZE;
                    break;
                case JAVA_STRING_TYPE:
                    int size = Utils.intFromBytes(bytes, offset);
                    char[] res = UnsafeMemory.getCharArrayFromBytes(bytes, offset, size);
                    Object s = UnsafeMemory.allocateInstance(String.class);
                    UnsafeMemory.getUnsafe().putObject(s, charArrayValueFieldOffset, res);
                    UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], s);
                    offset += size + JAVA_INTEGER_SIZE;
                    break;
                case JAVA_UUID_TYPE:
                    long mostSigBits = Utils.longFromBytes(bytes, offset);
                    offset += JAVA_LONG_SIZE;
                    long leastSigBits = Utils.longFromBytes(bytes, offset);
                    offset += JAVA_LONG_SIZE;
                    Object uuid = UnsafeMemory.allocateInstance(UUID.class);
                    UnsafeMemory.getUnsafe().putLong(uuid, mostSigBitsFieldOffset, mostSigBits);
                    UnsafeMemory.getUnsafe().putLong(uuid, leastSigBitsFieldOffset, leastSigBits);
                    UnsafeMemory.getUnsafe().putObject(instance, declaredFieldsOffsets[i], uuid);
                    break;
            }
        }

        return (T) instance;
    }
}
