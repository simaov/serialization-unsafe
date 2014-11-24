package org.jcoffee.serialization;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.jcoffee.serialization.JavaTypes.JAVA_INTEGER_SIZE;

public class UnsafeMemory {

    private static final Unsafe UNSAFE;

    public static long longValueFieldOffset;
    public static long intValueFieldOffset;
    public static long charValueFieldOffset;
    public static long booleanValueFieldOffset;
    public static long doubleValueFieldOffset;
    public static long mostSigBitsFieldOffset;
    public static long leastSigBitsFieldOffset;
    public static long baseCharArrayOffset;
    public static long baseByteArrayOffset;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
            longValueFieldOffset = UNSAFE.objectFieldOffset(Long.class.getDeclaredField("value"));
            intValueFieldOffset = UNSAFE.objectFieldOffset(Integer.class.getDeclaredField("value"));
            charValueFieldOffset = UNSAFE.objectFieldOffset(String.class.getDeclaredField("value"));
            doubleValueFieldOffset = UNSAFE.objectFieldOffset(Double.class.getDeclaredField("value"));
            booleanValueFieldOffset = UNSAFE.objectFieldOffset(Boolean.class.getDeclaredField("value"));
            mostSigBitsFieldOffset = UNSAFE.objectFieldOffset(UUID.class.getDeclaredField("mostSigBits"));
            leastSigBitsFieldOffset = UNSAFE.objectFieldOffset(UUID.class.getDeclaredField("leastSigBits"));
            baseCharArrayOffset = UNSAFE.arrayBaseOffset(char[].class);
            baseByteArrayOffset = UNSAFE.arrayBaseOffset(byte[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public static int getPrimitiveInt(Object baseObj, long intFieldOffset) throws Exception {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getInt(baseObj, intFieldOffset);
    }

    public static long getPrimitiveLong(Object baseObj, long longFieldOffset) throws Exception {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getLong(baseObj, longFieldOffset);
    }

    public static double getPrimitiveDouble(Object baseObj, long doubleFieldOffset) throws Exception {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getDouble(baseObj, doubleFieldOffset);
    }

    /*
     *  Primitive wrapper types
     */

    public static boolean getBooleanFieldValue(Object baseObj, long booleanFieldOffset) throws Exception {
        if (baseObj == null) {
            return false;
        }
        return UNSAFE.getBoolean(getFieldObject(baseObj, booleanFieldOffset), booleanValueFieldOffset);
    }

    public static int getIntegerFieldValue(Object baseObj, long integerFieldOffset) throws Exception {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getInt(getFieldObject(baseObj, integerFieldOffset), intValueFieldOffset);
    }

    public static long getLongFieldValue(Object baseObj, long longFieldOffset) throws Exception {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getLong(getFieldObject(baseObj, longFieldOffset), longValueFieldOffset);
    }

    public static double getDoubleFieldValue(Object baseObj, long doubleFieldOffset) throws Exception {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getDouble(getFieldObject(baseObj, doubleFieldOffset), doubleValueFieldOffset);
    }

    /*
     *  Arrays of primitives
     */


    public static byte[] getBytesFromCharArray(Object baseObj, long charArrayOffset) throws Exception {
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

    /*
     *  Other
     */


    public static byte[] getBytesFromString(Object baseObj, long stringFieldOffset) throws Exception {
        if (baseObj == null) {
            return new byte[0];
        }
        return getBytesFromCharArray(getFieldObject(baseObj, stringFieldOffset), charValueFieldOffset);
    }

    public static byte[] getBytesFromUUID(Object uuid) throws Exception {
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
