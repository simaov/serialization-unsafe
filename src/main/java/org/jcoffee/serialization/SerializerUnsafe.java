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

            if (declaredFields[i].getType().getName().equals(byte.class.getName())) {
                declaredFieldsTypes[i] = JAVA_BYTE_TYPE;
                defaultSize += JAVA_BYTE_SIZE;
            } else if (declaredFields[i].getType().getName().equals(short.class.getName())) {
                declaredFieldsTypes[i] = JAVA_SHORT_TYPE;
                defaultSize += JAVA_SHORT_SIZE;
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
            } else if (declaredFields[i].getType().getName().equals(boolean.class.getName())) {
                declaredFieldsTypes[i] = JAVA_BOOLEAN_TYPE;
                defaultSize += JAVA_BOOLEAN_SIZE;
            } else if (declaredFields[i].getType().getName().equals(char.class.getName())) {
                declaredFieldsTypes[i] = JAVA_CHAR_TYPE;
                defaultSize += JAVA_CHARACTER_SIZE;
            } else if (declaredFields[i].getType().getName().equals(Byte.class.getName())) {
                declaredFieldsTypes[i] = JAVA_BYTE_OBJECT_TYPE;
                defaultSize += JAVA_BYTE_SIZE;
            } else if (declaredFields[i].getType().getName().equals(Short.class.getName())) {
                declaredFieldsTypes[i] = JAVA_SHORT_OBJECT_TYPE;
                defaultSize += JAVA_SHORT_SIZE;
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
            } else if (declaredFields[i].getType().getName().equals(Boolean.class.getName())) {
                declaredFieldsTypes[i] = JAVA_BOOLEAN_OBJECT_TYPE;
                defaultSize += JAVA_BOOLEAN_SIZE;
            } else if (declaredFields[i].getType().getName().equals(Character.class.getName())) {
                declaredFieldsTypes[i] = JAVA_CHARACTER_OBJECT_TYPE;
                defaultSize += JAVA_CHARACTER_SIZE;
            } else if (declaredFields[i].getType().getName().equals(byte[].class.getName())) {
                declaredFieldsTypes[i] = JAVA_BYTE_ARRAY_TYPE;
            } else if (declaredFields[i].getType().getName().equals(short[].class.getName())) {
                declaredFieldsTypes[i] = JAVA_SHORT_ARRAY_TYPE;
            } else if (declaredFields[i].getType().getName().equals(int[].class.getName())) {
                declaredFieldsTypes[i] = JAVA_INT_ARRAY_TYPE;
            } else if (declaredFields[i].getType().getName().equals(long[].class.getName())) {
                declaredFieldsTypes[i] = JAVA_LONG_ARRAY_TYPE;
            } else if (declaredFields[i].getType().getName().equals(float[].class.getName())) {
                declaredFieldsTypes[i] = JAVA_FLOAT_ARRAY_TYPE;
            } else if (declaredFields[i].getType().getName().equals(double[].class.getName())) {
                declaredFieldsTypes[i] = JAVA_DOUBLE_ARRAY_TYPE;
            } else if (declaredFields[i].getType().getName().equals(boolean[].class.getName())) {
                declaredFieldsTypes[i] = JAVA_BOOLEAN_ARRAY_TYPE;
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
                case JAVA_BYTE_TYPE:
                    declaredFieldsValues[i] = new byte[]{UnsafeMemory.getPrimitiveByte(obj, declaredFieldsOffsets[i])};
                    break;
                case JAVA_SHORT_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromShort(UnsafeMemory.getPrimitiveShort(obj, declaredFieldsOffsets[i]));
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
                case JAVA_BOOLEAN_TYPE:
                    declaredFieldsValues[i] = Utils.byteFromBoolean(UnsafeMemory.getPrimitiveBoolean(obj, declaredFieldsOffsets[i]));
                    break;
                case JAVA_CHAR_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromChar(UnsafeMemory.getPrimitiveChar(obj, declaredFieldsOffsets[i]));
                    break;
                case JAVA_BYTE_OBJECT_TYPE:
                    declaredFieldsValues[i] = new byte[]{UnsafeMemory.getByteFieldValue(obj, declaredFieldsOffsets[i])};
                    break;
                case JAVA_SHORT_OBJECT_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromShort(UnsafeMemory.getShortFieldValue(obj, declaredFieldsOffsets[i]));
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
                case JAVA_BOOLEAN_OBJECT_TYPE:
                    declaredFieldsValues[i] = Utils.byteFromBoolean(UnsafeMemory.getBooleanFieldValue(obj, declaredFieldsOffsets[i]));
                    break;
                case JAVA_CHARACTER_OBJECT_TYPE:
                    declaredFieldsValues[i] = Utils.bytesFromChar(UnsafeMemory.getCharFieldValue(obj, declaredFieldsOffsets[i]));
                    break;
                case JAVA_BYTE_ARRAY_TYPE:
                    byte[] byteArray = UnsafeMemory.getBytesFromByteArray(obj, declaredFieldsOffsets[i]);
                    declaredFieldsValues[i] = byteArray;
                    bufferSize += byteArray.length;
                    break;
                case JAVA_SHORT_ARRAY_TYPE:
                    byte[] shortArray = UnsafeMemory.getBytesFromShortArray(obj, declaredFieldsOffsets[i]);
                    declaredFieldsValues[i] = shortArray;
                    bufferSize += shortArray.length;
                    break;
                case JAVA_INT_ARRAY_TYPE:
                    byte[] intArray = UnsafeMemory.getBytesFromIntArray(obj, declaredFieldsOffsets[i]);
                    declaredFieldsValues[i] = intArray;
                    bufferSize += intArray.length;
                    break;
                case JAVA_LONG_ARRAY_TYPE:
                    byte[] longBytes = UnsafeMemory.getBytesFromLongArray(obj, declaredFieldsOffsets[i]);
                    declaredFieldsValues[i] = longBytes;
                    bufferSize += longBytes.length;
                    break;
                case JAVA_FLOAT_ARRAY_TYPE:
                    byte[] floatArray = UnsafeMemory.getBytesFromFloatArray(obj, declaredFieldsOffsets[i]);
                    declaredFieldsValues[i] = floatArray;
                    bufferSize += floatArray.length;
                    break;
                case JAVA_DOUBLE_ARRAY_TYPE:
                    byte[] doubleBytes = UnsafeMemory.getBytesFromDoubleArray(obj, declaredFieldsOffsets[i]);
                    declaredFieldsValues[i] = doubleBytes;
                    bufferSize += doubleBytes.length;
                    break;
                case JAVA_BOOLEAN_ARRAY_TYPE:
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
                case JAVA_BYTE_TYPE:
                    UnsafeMemory.putByte(instance, declaredFieldsOffsets[i], bytes[offset]);
                    offset += JAVA_BYTE_SIZE;
                    break;
                case JAVA_SHORT_TYPE:
                    UnsafeMemory.putShort(instance, declaredFieldsOffsets[i], Utils.shortFromBytes(bytes, offset));
                    offset += JAVA_SHORT_SIZE;
                    break;
                case JAVA_INT_TYPE:
                    UnsafeMemory.putInt(instance, declaredFieldsOffsets[i], Utils.intFromBytes(bytes, offset));
                    offset += JAVA_INTEGER_SIZE;
                    break;
                case JAVA_LONG_TYPE:
                    UnsafeMemory.putLong(instance, declaredFieldsOffsets[i], Utils.longFromBytes(bytes, offset));
                    offset += JAVA_LONG_SIZE;
                    break;
                case JAVA_FLOAT_TYPE:
                    UnsafeMemory.putFloat(instance, declaredFieldsOffsets[i], Float.intBitsToFloat(Utils.intFromBytes(bytes, offset)));
                    offset += JAVA_FLOAT_SIZE;
                    break;
                case JAVA_DOUBLE_TYPE:
                    UnsafeMemory.putDouble(instance, declaredFieldsOffsets[i], Double.longBitsToDouble(Utils.longFromBytes(bytes, offset)));
                    offset += JAVA_DOUBLE_SIZE;
                    break;
                case JAVA_BOOLEAN_TYPE:
                    UnsafeMemory.putBoolean(instance, declaredFieldsOffsets[i], Utils.booleanFromBytes(bytes, offset));
                    offset += JAVA_BOOLEAN_SIZE;
                    break;
                case JAVA_CHAR_TYPE:
                    UnsafeMemory.putChar(instance, declaredFieldsOffsets[i], Utils.charFromBytes(bytes, offset));
                    offset += JAVA_BOOLEAN_SIZE;
                    break;
                case JAVA_BYTE_OBJECT_TYPE:
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], bytes[offset]);
                    offset += JAVA_BYTE_SIZE;
                    break;
                case JAVA_SHORT_OBJECT_TYPE:
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], Utils.shortFromBytes(bytes, offset));
                    offset += JAVA_SHORT_SIZE;
                    break;
                case JAVA_INTEGER_OBJECT_TYPE:
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], Utils.intFromBytes(bytes, offset));
                    offset += JAVA_INTEGER_SIZE;
                    break;
                case JAVA_LONG_OBJECT_TYPE:
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], Utils.longFromBytes(bytes, offset));
                    offset += JAVA_LONG_SIZE;
                    break;
                case JAVA_FLOAT_OBJECT_TYPE:
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], Float.intBitsToFloat(Utils.intFromBytes(bytes, offset)));
                    offset += JAVA_FLOAT_SIZE;
                    break;
                case JAVA_DOUBLE_OBJECT_TYPE:
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], Double.longBitsToDouble(Utils.longFromBytes(bytes, offset)));
                    offset += JAVA_DOUBLE_SIZE;
                    break;
                case JAVA_BOOLEAN_OBJECT_TYPE:
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], Utils.booleanFromBytes(bytes, offset));
                    offset += JAVA_BOOLEAN_SIZE;
                    break;
                case JAVA_CHARACTER_OBJECT_TYPE:
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], Utils.charFromBytes(bytes, offset));
                    offset += JAVA_CHARACTER_SIZE;
                    break;
                case JAVA_BYTE_ARRAY_TYPE:
                    int byteArraySize = Utils.intFromBytes(bytes, offset);
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], UnsafeMemory.getByteArrayFromBytes(bytes, offset, byteArraySize));
                    offset += byteArraySize + JAVA_INTEGER_SIZE;
                    break;
                case JAVA_SHORT_ARRAY_TYPE:
                    int shortArraySize = Utils.intFromBytes(bytes, offset);
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], UnsafeMemory.getShortArrayFromBytes(bytes, offset, shortArraySize));
                    offset += shortArraySize + JAVA_INTEGER_SIZE;
                    break;
                case JAVA_INT_ARRAY_TYPE:
                    int intArraySize = Utils.intFromBytes(bytes, offset);
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], UnsafeMemory.getIntArrayFromBytes(bytes, offset, intArraySize));
                    offset += intArraySize + JAVA_INTEGER_SIZE;
                    break;
                case JAVA_LONG_ARRAY_TYPE:
                    int longArraySize = Utils.intFromBytes(bytes, offset);
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], UnsafeMemory.getLongArrayFromBytes(bytes, offset, longArraySize));
                    offset += longArraySize + JAVA_INTEGER_SIZE;
                    break;
                case JAVA_FLOAT_ARRAY_TYPE:
                    int floatArraySize = Utils.intFromBytes(bytes, offset);
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], UnsafeMemory.getFloatArrayFromBytes(bytes, offset, floatArraySize));
                    offset += floatArraySize + JAVA_INTEGER_SIZE;
                    break;
                case JAVA_DOUBLE_ARRAY_TYPE:
                    int doubleArraySize = Utils.intFromBytes(bytes, offset);
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], UnsafeMemory.getDoubleArrayFromBytes(bytes, offset, doubleArraySize));
                    offset += doubleArraySize + JAVA_INTEGER_SIZE;
                    break;
                case JAVA_BOOLEAN_ARRAY_TYPE:
                    break;
                case JAVA_CHAR_ARRAY_TYPE:
                    int charArraySize = Utils.intFromBytes(bytes, offset);
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], UnsafeMemory.getCharArrayFromBytes(bytes, offset, charArraySize));
                    offset += charArraySize + JAVA_INTEGER_SIZE;
                    break;
                case JAVA_STRING_TYPE:
                    int size = Utils.intFromBytes(bytes, offset);
                    Object string = UnsafeMemory.allocateInstance(String.class);
                    UnsafeMemory.putObject(string, charArrayValueFieldOffset, UnsafeMemory.getCharArrayFromBytes(bytes, offset, size));
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], string);
                    offset += size + JAVA_INTEGER_SIZE;
                    break;
                case JAVA_UUID_TYPE:
                    Object uuid = UnsafeMemory.allocateInstance(UUID.class);
                    UnsafeMemory.putLong(uuid, mostSigBitsFieldOffset, Utils.longFromBytes(bytes, offset));
                    UnsafeMemory.putLong(uuid, leastSigBitsFieldOffset, Utils.longFromBytes(bytes, offset + JAVA_LONG_SIZE));
                    UnsafeMemory.putObject(instance, declaredFieldsOffsets[i], uuid);
                    offset += JAVA_LONG_SIZE << 1;
                    break;
            }
        }

        return (T) instance;
    }
}
