package org.jcoffee.serialization;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.jcoffee.serialization.JavaTypes.JAVA_INTEGER_SIZE;

public class UnsafeMemory {

    private static final Unsafe UNSAFE;
    private static final String VALUE_FIELD = "value";

    public static final long booleanValueFieldOffset;
    public static final long intValueFieldOffset;
    public static final long longValueFieldOffset;
    public static final long floatValueFieldOffset;
    public static final long doubleValueFieldOffset;
    public static final long charArrayValueFieldOffset;
    public static final long mostSigBitsFieldOffset;
    public static final long leastSigBitsFieldOffset;
    public static final long baseByteArrayOffset;
    public static final long baseCharArrayOffset;
    public static final long baseLongArrayOffset;
    public static final long baseDoubleArrayOffset;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
            booleanValueFieldOffset = UNSAFE.objectFieldOffset(Boolean.class.getDeclaredField(VALUE_FIELD));
            intValueFieldOffset = UNSAFE.objectFieldOffset(Integer.class.getDeclaredField(VALUE_FIELD));
            longValueFieldOffset = UNSAFE.objectFieldOffset(Long.class.getDeclaredField(VALUE_FIELD));
            floatValueFieldOffset = UNSAFE.objectFieldOffset(Float.class.getDeclaredField(VALUE_FIELD));
            doubleValueFieldOffset = UNSAFE.objectFieldOffset(Double.class.getDeclaredField(VALUE_FIELD));
            charArrayValueFieldOffset = UNSAFE.objectFieldOffset(String.class.getDeclaredField(VALUE_FIELD));
            mostSigBitsFieldOffset = UNSAFE.objectFieldOffset(UUID.class.getDeclaredField("mostSigBits"));
            leastSigBitsFieldOffset = UNSAFE.objectFieldOffset(UUID.class.getDeclaredField("leastSigBits"));
            baseByteArrayOffset = UNSAFE.arrayBaseOffset(byte[].class);
            baseCharArrayOffset = UNSAFE.arrayBaseOffset(char[].class);
            baseLongArrayOffset = UNSAFE.arrayBaseOffset(long[].class);
            baseDoubleArrayOffset = UNSAFE.arrayBaseOffset(double[].class);
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

    public static Unsafe getUnsafe() {
        return UNSAFE;
    }

    /*
     *  Primitive types
     */

    public static boolean getPrimitiveBoolean(Object baseObject, long fieldOffset) {
        if (baseObject == null) {
            return false;
        }
        return UNSAFE.getBoolean(baseObject, fieldOffset);
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

    /*
     *  Primitive wrapper types
     */

    public static boolean getBooleanFieldValue(Object baseObj, long booleanFieldOffset) {
        if (baseObj == null) {
            return false;
        }
        return UNSAFE.getBoolean(getFieldObject(baseObj, booleanFieldOffset), booleanValueFieldOffset);
    }

    public static int getIntegerFieldValue(Object baseObj, long integerFieldOffset) {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getInt(getFieldObject(baseObj, integerFieldOffset), intValueFieldOffset);
    }

    public static long getLongFieldValue(Object baseObj, long longFieldOffset) {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getLong(getFieldObject(baseObj, longFieldOffset), longValueFieldOffset);
    }

    public static float getFloatFieldValue(Object baseObj, long floatFieldOffset) {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getFloat(getFieldObject(baseObj, floatFieldOffset), floatValueFieldOffset);
    }

    public static double getDoubleFieldValue(Object baseObj, long doubleFieldOffset) {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getDouble(getFieldObject(baseObj, doubleFieldOffset), doubleValueFieldOffset);
    }

    /*
     *  Arrays of primitives
     */


    public static byte[] getBytesFromCharArray(Object baseObj, long charArrayOffset) {
        char[] chars = (char[]) getFieldObject(baseObj, charArrayOffset);
        int len = chars.length << 1;
        byte[] cb = new byte[len + JAVA_INTEGER_SIZE];
        for (int i = 0; i < 4; i++) {
            cb[i] = (byte) (len >> ((3 - i) * 8));
        }
        UNSAFE.copyMemory(chars, baseCharArrayOffset, cb, baseByteArrayOffset + JAVA_INTEGER_SIZE, len);
        return cb;
    }

    public static char[] getCharArrayFromBytes(byte[] bytes, int offset, int sizeInBytes) {
        char[] chs = new char[sizeInBytes >> 1];
        UNSAFE.copyMemory(bytes, baseByteArrayOffset + offset + JAVA_INTEGER_SIZE, chs, baseCharArrayOffset, sizeInBytes);
        return chs;
    }

    public static byte[] getBytesFromLongArray(Object baseObj, long longArrayOffset) {
        long[] longs = (long[]) getFieldObject(baseObj, longArrayOffset);
        int len = longs.length << 3;
        byte[] lb = new byte[len + JAVA_INTEGER_SIZE];
        for (int i = 0; i < 4; i++) {
            lb[i] = (byte) (len >> ((3 - i) * 8));
        }
        UNSAFE.copyMemory(longs, baseLongArrayOffset, lb, baseByteArrayOffset + JAVA_INTEGER_SIZE, len);
        return lb;
    }

    public static long[] getLongArrayFromBytes(byte[] bytes, int offset, int sizeInBytes) {
        long[] longs = new long[sizeInBytes >> 3];
        UNSAFE.copyMemory(bytes, baseByteArrayOffset + offset + JAVA_INTEGER_SIZE, longs, baseLongArrayOffset, sizeInBytes);
        return longs;
    }

    public static byte[] getBytesFromDoubleArray(Object baseObj, long doubleArrayOffset) {
        double[] doubles = (double[]) getFieldObject(baseObj, doubleArrayOffset);
        int len = doubles.length << 3;
        byte[] db = new byte[len + JAVA_INTEGER_SIZE];
        for (int i = 0; i < 4; i++) {
            db[i] = (byte) (len >> ((3 - i) * 8));
        }
        UNSAFE.copyMemory(doubles, baseDoubleArrayOffset, db, baseByteArrayOffset + JAVA_INTEGER_SIZE, len);
        return db;
    }

    public static double[] getDoubleArrayFromBytes(byte[] bytes, int offset, int sizeInBytes) {
        double[] doubles = new double[sizeInBytes >> 3];
        UNSAFE.copyMemory(bytes, baseByteArrayOffset + offset + JAVA_INTEGER_SIZE, doubles, baseDoubleArrayOffset, sizeInBytes);
        return doubles;
    }

    /*
     *  Other
     */


    public static byte[] getBytesFromString(Object baseObj, long stringFieldOffset) throws Exception {
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

        for (int i = 0; i < 8; ++i) {
            b[index++] = (byte) (mostSigBits >> ((7 - i) * 8));
        }

        for (int i = 0; i < 8; ++i) {
            b[index++] = (byte) (leastSigBits >> ((7 - i) * 8));
        }

        return b;
    }
}
