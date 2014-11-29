package org.jcoffee.serialization;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.jcoffee.serialization.JavaTypes.*;

public class UnsafeMemory {

    private static final Unsafe UNSAFE;
    private static final String VALUE_FIELD = "value";

    public static final long byteValueFieldOffset;
    public static final long shortValueFieldOffset;
    public static final long intValueFieldOffset;
    public static final long longValueFieldOffset;
    public static final long floatValueFieldOffset;
    public static final long doubleValueFieldOffset;
    public static final long booleanValueFieldOffset;
    public static final long charValueFieldOffset;

    public static final long baseByteArrayOffset;
    public static final long baseShortArrayOffset;
    public static final long baseIntArrayOffset;
    public static final long baseLongArrayOffset;
    public static final long baseFloatArrayOffset;
    public static final long baseDoubleArrayOffset;
    public static final long baseBooleanArrayOffset;
    public static final long baseCharArrayOffset;

    public static final long charArrayValueFieldOffset;
    public static final long mostSigBitsFieldOffset;
    public static final long leastSigBitsFieldOffset;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);

            byteValueFieldOffset = UNSAFE.objectFieldOffset(Byte.class.getDeclaredField(VALUE_FIELD));
            shortValueFieldOffset = UNSAFE.objectFieldOffset(Short.class.getDeclaredField(VALUE_FIELD));
            intValueFieldOffset = UNSAFE.objectFieldOffset(Integer.class.getDeclaredField(VALUE_FIELD));
            longValueFieldOffset = UNSAFE.objectFieldOffset(Long.class.getDeclaredField(VALUE_FIELD));
            floatValueFieldOffset = UNSAFE.objectFieldOffset(Float.class.getDeclaredField(VALUE_FIELD));
            doubleValueFieldOffset = UNSAFE.objectFieldOffset(Double.class.getDeclaredField(VALUE_FIELD));
            booleanValueFieldOffset = UNSAFE.objectFieldOffset(Boolean.class.getDeclaredField(VALUE_FIELD));
            charValueFieldOffset = UNSAFE.objectFieldOffset(Character.class.getDeclaredField(VALUE_FIELD));

            baseByteArrayOffset = UNSAFE.arrayBaseOffset(byte[].class);
            baseShortArrayOffset = UNSAFE.arrayBaseOffset(short[].class);
            baseIntArrayOffset = UNSAFE.arrayBaseOffset(int[].class);
            baseLongArrayOffset = UNSAFE.arrayBaseOffset(long[].class);
            baseFloatArrayOffset = UNSAFE.arrayBaseOffset(float[].class);
            baseDoubleArrayOffset = UNSAFE.arrayBaseOffset(double[].class);
            baseBooleanArrayOffset = UNSAFE.arrayBaseOffset(boolean[].class);
            baseCharArrayOffset = UNSAFE.arrayBaseOffset(char[].class);

            charArrayValueFieldOffset = UNSAFE.objectFieldOffset(String.class.getDeclaredField(VALUE_FIELD));
            mostSigBitsFieldOffset = UNSAFE.objectFieldOffset(UUID.class.getDeclaredField("mostSigBits"));
            leastSigBitsFieldOffset = UNSAFE.objectFieldOffset(UUID.class.getDeclaredField("leastSigBits"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object allocateInstance(Class aClass) throws InstantiationException {
        return UNSAFE.allocateInstance(aClass);
    }

    public static Object getFieldObject(Object baseObject, long offset) {
        return UNSAFE.getObject(baseObject, offset);
    }

    public static long getFieldOffset(Field field) {
        return UNSAFE.objectFieldOffset(field);
    }

    public static void putByte(Object instance, long fieldOffset, byte b) {
        UNSAFE.putByte(instance, fieldOffset, b);
    }

    public static void putShort(Object instance, long fieldOffset, short sh) {
        UNSAFE.putShort(instance, fieldOffset, sh);
    }

    public static void putInt(Object instance, long fieldOffset, int i) {
        UNSAFE.putInt(instance, fieldOffset, i);
    }

    public static void putLong(Object instance, long fieldOffset, long l) {
        UNSAFE.putLong(instance, fieldOffset, l);
    }

    public static void putFloat(Object instance, long fieldOffset, float f) {
        UNSAFE.putFloat(instance, fieldOffset, f);
    }

    public static void putDouble(Object instance, long fieldOffset, double d) {
        UNSAFE.putDouble(instance, fieldOffset, d);
    }

    public static void putBoolean(Object instance, long fieldOffset, boolean b) {
        UNSAFE.putBoolean(instance, fieldOffset, b);
    }

    public static void putChar(Object instance, long fieldOffset, char c) {
        UNSAFE.putChar(instance, fieldOffset, c);
    }

    public static void putObject(Object instance, long fieldOffset, Object object) {
        UNSAFE.putObject(instance, fieldOffset, object);
    }

    public static void copyMemory(Object src, long srcOffset, Object dst, long dstOffset, long size) {
        UNSAFE.copyMemory(src, srcOffset, dst, dstOffset, size);
    }

    /*
     *  Primitive types
     */

    public static byte[] getPrimitiveInBytes(Object baseObject, long fieldOffset, int typeSize) {
        byte[] bytes = new byte[typeSize];
        UNSAFE.copyMemory(baseObject, fieldOffset, bytes, baseByteArrayOffset, typeSize);
        return bytes;
    }

    public static byte getPrimitiveByte(Object baseObject, long fieldOffset) {
        if (baseObject == null) {
            return 0;
        }
        return UNSAFE.getByte(baseObject, fieldOffset);
    }

    public static short getPrimitiveShort(Object baseObject, long fieldOffset) {
        if (baseObject == null) {
            return 0;
        }
        return UNSAFE.getShort(baseObject, fieldOffset);
    }

    public static int getPrimitiveInt(Object baseObj, long intFieldOffset) {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getInt(baseObj, intFieldOffset);
    }

    public static long getPrimitiveLong(Object baseObj, long longFieldOffset) {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getLong(baseObj, longFieldOffset);
    }

    public static float getPrimitiveFloat(Object baseObj, long floatFieldOffset) {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getFloat(baseObj, floatFieldOffset);
    }

    public static double getPrimitiveDouble(Object baseObj, long doubleFieldOffset) {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getDouble(baseObj, doubleFieldOffset);
    }

    public static boolean getPrimitiveBoolean(Object baseObject, long fieldOffset) {
        if (baseObject == null) {
            return false;
        }
        return UNSAFE.getBoolean(baseObject, fieldOffset);
    }

    public static char getPrimitiveChar(Object baseObject, long fieldOffset) {
        if (baseObject == null) {
            return 0;
        }
        return UNSAFE.getChar(baseObject, fieldOffset);
    }

    /*
     *  Primitive wrapper types
     */

    public static byte getByteFieldValue(Object baseObj, long byteFieldOffset) {
        return getPrimitiveByte(getFieldObject(baseObj, byteFieldOffset), byteValueFieldOffset);
    }

    public static short getShortFieldValue(Object baseObj, long shortFieldOffset) {
        return getPrimitiveShort(getFieldObject(baseObj, shortFieldOffset), shortValueFieldOffset);
    }

    public static byte[] getShortFieldValueInBytes(Object baseObj, long shortFieldOffset) {
        return getPrimitiveInBytes(getFieldObject(baseObj, shortFieldOffset), shortValueFieldOffset, JAVA_SHORT_SIZE);
    }

    public static int getIntegerFieldValue(Object baseObj, long integerFieldOffset) {
        return getPrimitiveInt(getFieldObject(baseObj, integerFieldOffset), intValueFieldOffset);
    }

    public static byte[] getIntegerFieldValueInBytes(Object baseObj, long integerFieldOffset) {
        return getPrimitiveInBytes(getFieldObject(baseObj, integerFieldOffset), intValueFieldOffset, JAVA_INTEGER_SIZE);
    }

    public static long getLongFieldValue(Object baseObj, long longFieldOffset) {
        return getPrimitiveLong(getFieldObject(baseObj, longFieldOffset), longValueFieldOffset);
    }

    public static byte[] getLongFieldValueInBytes(Object baseObj, long longFieldOffset) {
        return getPrimitiveInBytes(getFieldObject(baseObj, longFieldOffset), longValueFieldOffset, JAVA_LONG_SIZE);
    }

    public static float getFloatFieldValue(Object baseObj, long floatFieldOffset) {
        return getPrimitiveFloat(getFieldObject(baseObj, floatFieldOffset), floatValueFieldOffset);
    }

    public static byte[] getFloatFieldValueInBytes(Object baseObj, long floatFieldOffset) {
        return getPrimitiveInBytes(getFieldObject(baseObj, floatFieldOffset), floatValueFieldOffset, JAVA_FLOAT_SIZE);
    }

    public static double getDoubleFieldValue(Object baseObj, long doubleFieldOffset) {
        return getPrimitiveDouble(getFieldObject(baseObj, doubleFieldOffset), doubleValueFieldOffset);
    }

    public static byte[] getDoubleFieldValueInBytes(Object baseObj, long doubleFieldOffset) {
        return getPrimitiveInBytes(getFieldObject(baseObj, doubleFieldOffset), doubleValueFieldOffset, JAVA_DOUBLE_SIZE);
    }

    public static boolean getBooleanFieldValue(Object baseObj, long booleanFieldOffset) {
        return getPrimitiveBoolean(getFieldObject(baseObj, booleanFieldOffset), booleanValueFieldOffset);
    }

    public static byte[] getBooleanFieldValueInBytes(Object baseObj, long booleanFieldOffset) {
        return getPrimitiveInBytes(getFieldObject(baseObj, booleanFieldOffset), booleanValueFieldOffset, JAVA_BOOLEAN_SIZE);
    }

    public static char getCharFieldValue(Object baseObj, long charFieldOffset) {
        return getPrimitiveChar(getFieldObject(baseObj, charFieldOffset), charValueFieldOffset);
    }

    public static byte[] getCharFieldValueInBytes(Object baseObj, long charFieldOffset) {
        return getPrimitiveInBytes(getFieldObject(baseObj, charFieldOffset), charValueFieldOffset, JAVA_CHARACTER_SIZE);
    }

    /*
     *  Arrays of primitives
     */

    // byte
    public static byte[] getBytesFromByteArray(Object baseObj, long byteArrayOffset) {
        byte[] bytes = (byte[]) getFieldObject(baseObj, byteArrayOffset);
        int len = bytes.length;
        byte[] bb = new byte[len + JAVA_INTEGER_SIZE];
        for (int i = 0; i < JAVA_INTEGER_SIZE; i++) {
            bb[i] = (byte) (len >> (i << 3));
        }
        UNSAFE.copyMemory(bytes, baseByteArrayOffset, bb, baseByteArrayOffset + JAVA_INTEGER_SIZE, len);
        return bb;
    }

    public static byte[] getByteArrayFromBytes(byte[] bytes, int offset, int sizeInBytes) {
        byte[] b = new byte[sizeInBytes];
        UNSAFE.copyMemory(bytes, baseByteArrayOffset + offset + JAVA_INTEGER_SIZE, b, baseByteArrayOffset, sizeInBytes);
        return b;
    }

    // short
    public static byte[] getBytesFromShortArray(Object baseObj, long shortArrayOffset) {
        short[] shorts = (short[]) getFieldObject(baseObj, shortArrayOffset);
        int len = shorts.length << 1;
        byte[] shb = new byte[len + JAVA_INTEGER_SIZE];
        for (int i = 0; i < JAVA_INTEGER_SIZE; i++) {
            shb[i] = (byte) (len >> (i << 3));
        }
        UNSAFE.copyMemory(shorts, baseShortArrayOffset, shb, baseByteArrayOffset + JAVA_INTEGER_SIZE, len);
        return shb;
    }

    public static short[] getShortArrayFromBytes(byte[] bytes, int offset, int sizeInBytes) {
        short[] shorts = new short[sizeInBytes >> 1];
        UNSAFE.copyMemory(bytes, baseByteArrayOffset + offset + JAVA_INTEGER_SIZE, shorts, baseShortArrayOffset, sizeInBytes);
        return shorts;
    }

    // int
    public static byte[] getBytesFromIntArray(Object baseObj, long intArrayOffset) {
        int[] longs = (int[]) getFieldObject(baseObj, intArrayOffset);
        int len = longs.length << 2;
        byte[] ib = new byte[len + JAVA_INTEGER_SIZE];
        for (int i = 0; i < JAVA_INTEGER_SIZE; i++) {
            ib[i] = (byte) (len >> (i << 3));
        }
        UNSAFE.copyMemory(longs, baseIntArrayOffset, ib, baseByteArrayOffset + JAVA_INTEGER_SIZE, len);
        return ib;
    }

    public static int[] getIntArrayFromBytes(byte[] bytes, int offset, int sizeInBytes) {
        int[] ints = new int[sizeInBytes >> 2];
        UNSAFE.copyMemory(bytes, baseByteArrayOffset + offset + JAVA_INTEGER_SIZE, ints, baseLongArrayOffset, sizeInBytes);
        return ints;
    }

    // long
    public static byte[] getBytesFromLongArray(Object baseObj, long longArrayOffset) {
        long[] longs = (long[]) getFieldObject(baseObj, longArrayOffset);
        int len = longs.length << 3;
        byte[] lb = new byte[len + JAVA_INTEGER_SIZE];
        for (int i = 0; i < JAVA_INTEGER_SIZE; i++) {
            lb[i] = (byte) (len >> (i << 3));
        }
        UNSAFE.copyMemory(longs, baseLongArrayOffset, lb, baseByteArrayOffset + JAVA_INTEGER_SIZE, len);
        return lb;
    }

    public static long[] getLongArrayFromBytes(byte[] bytes, int offset, int sizeInBytes) {
        long[] longs = new long[sizeInBytes >> 3];
        UNSAFE.copyMemory(bytes, baseByteArrayOffset + offset + JAVA_INTEGER_SIZE, longs, baseLongArrayOffset, sizeInBytes);
        return longs;
    }

    // float
    public static byte[] getBytesFromFloatArray(Object baseObj, long floatArrayOffset) {
        float[] floats = (float[]) getFieldObject(baseObj, floatArrayOffset);
        int len = floats.length << 2;
        byte[] fb = new byte[len + JAVA_INTEGER_SIZE];
        for (int i = 0; i < JAVA_INTEGER_SIZE; i++) {
            fb[i] = (byte) (len >> (i << 3));
        }
        UNSAFE.copyMemory(floats, baseFloatArrayOffset, fb, baseByteArrayOffset + JAVA_INTEGER_SIZE, len);
        return fb;
    }

    public static float[] getFloatArrayFromBytes(byte[] bytes, int offset, int sizeInBytes) {
        float[] floats = new float[sizeInBytes >> 2];
        UNSAFE.copyMemory(bytes, baseByteArrayOffset + offset + JAVA_INTEGER_SIZE, floats, baseFloatArrayOffset, sizeInBytes);
        return floats;
    }

    // double
    public static byte[] getBytesFromDoubleArray(Object baseObj, long doubleArrayOffset) {
        double[] doubles = (double[]) getFieldObject(baseObj, doubleArrayOffset);
        int len = doubles.length << 3;
        byte[] db = new byte[len + JAVA_INTEGER_SIZE];
        for (int i = 0; i < JAVA_INTEGER_SIZE; i++) {
            db[i] = (byte) (len >> (i << 3));
        }
        UNSAFE.copyMemory(doubles, baseDoubleArrayOffset, db, baseByteArrayOffset + JAVA_INTEGER_SIZE, len);
        return db;
    }

    public static double[] getDoubleArrayFromBytes(byte[] bytes, int offset, int sizeInBytes) {
        double[] doubles = new double[sizeInBytes >> 3];
        UNSAFE.copyMemory(bytes, baseByteArrayOffset + offset + JAVA_INTEGER_SIZE, doubles, baseDoubleArrayOffset, sizeInBytes);
        return doubles;
    }

    // boolean
    public static byte[] getBytesFromBooleanArray(Object baseObj, long booleanArrayOffset) {
        boolean[] booleans = (boolean[]) getFieldObject(baseObj, booleanArrayOffset);
        int len = booleans.length;
        byte[] bb = new byte[len + JAVA_INTEGER_SIZE];
        for (int i = 0; i < JAVA_INTEGER_SIZE; i++) {
            bb[i] = (byte) (len >> (i << 3));
        }
        UNSAFE.copyMemory(booleans, baseBooleanArrayOffset, bb, baseByteArrayOffset + JAVA_INTEGER_SIZE, len);
        return bb;
    }

    public static boolean[] getBooleanArrayFromBytes(byte[] bytes, int offset, int sizeInBytes) {
        boolean[] booleans = new boolean[sizeInBytes];
        UNSAFE.copyMemory(bytes, baseByteArrayOffset + offset + JAVA_INTEGER_SIZE, booleans, baseBooleanArrayOffset, sizeInBytes);
        return booleans;
    }

    // char
    public static byte[] getBytesFromCharArray(Object baseObj, long charArrayOffset) {
        char[] chars = (char[]) getFieldObject(baseObj, charArrayOffset);
        int len = chars.length << 1;
        byte[] cb = new byte[len + JAVA_INTEGER_SIZE];
        for (int i = 0; i < JAVA_INTEGER_SIZE; i++) {
            cb[i] = (byte) (len >> (i << 3));
        }
        UNSAFE.copyMemory(chars, baseCharArrayOffset, cb, baseByteArrayOffset + JAVA_INTEGER_SIZE, len);
        return cb;
    }

    public static char[] getCharArrayFromBytes(byte[] bytes, int offset, int sizeInBytes) {
        char[] chs = new char[sizeInBytes >> 1];
        UNSAFE.copyMemory(bytes, baseByteArrayOffset + offset + JAVA_INTEGER_SIZE, chs, baseCharArrayOffset, sizeInBytes);
        return chs;
    }


    /*
     *  Other
     */


    public static byte[] getBytesFromString(Object baseObj, long stringFieldOffset) {
        if (baseObj == null) {
            return new byte[0];
        }
        return getBytesFromCharArray(getFieldObject(baseObj, stringFieldOffset), charArrayValueFieldOffset);
    }

    public static byte[] getBytesFromUUID(Object uuid) {
        if (uuid == null) {
            return new byte[0];
        }

        byte[] b = new byte[16];
        int index = 0;

        long mostSigBits = UNSAFE.getLong(uuid, mostSigBitsFieldOffset);
        long leastSigBits = UNSAFE.getLong(uuid, leastSigBitsFieldOffset);

        for (int i = 0; i < JAVA_LONG_SIZE; ++i) {
            b[index++] = (byte) (mostSigBits >> (i << 3));
        }

        for (int i = 0; i < JAVA_LONG_SIZE; ++i) {
            b[index++] = (byte) (leastSigBits >> (i << 3));
        }

        return b;
    }
}
