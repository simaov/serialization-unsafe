package org.jcoffee;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public class UnsafeMemory {

    private static final Unsafe UNSAFE;
    public static long longValueFieldOffset;
    public static long intValueFieldOffset;
    public static long charValueFieldOffset;
    public static long mostSigBitsFieldOffset;
    public static long leastSigBitsFieldOffset;
    public static long baseCharArrayOffset;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
            longValueFieldOffset = UNSAFE.objectFieldOffset(Long.class.getDeclaredField("value"));
            intValueFieldOffset = UNSAFE.objectFieldOffset(Integer.class.getDeclaredField("value"));
            charValueFieldOffset = UNSAFE.objectFieldOffset(String.class.getDeclaredField("value"));
            mostSigBitsFieldOffset = UNSAFE.objectFieldOffset(UUID.class.getDeclaredField("mostSigBits"));
            leastSigBitsFieldOffset = UNSAFE.objectFieldOffset(UUID.class.getDeclaredField("leastSigBits"));
            baseCharArrayOffset = UNSAFE.arrayBaseOffset(char[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static long getLongValue(Object baseObj, long fieldOffset) throws Exception {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getLong(baseObj, fieldOffset);
    }

    public static int getIntValue(Object baseObj, long fieldOffset) throws Exception {
        if (baseObj == null) {
            return 0;
        }
        return UNSAFE.getInt(baseObj, fieldOffset);
    }

    public static long getLongValueFromLongObject(Object l) throws Exception {
        if (l == null) {
            return 0;
        }
        return UNSAFE.getLong(l, longValueFieldOffset);
    }

    public static int getIntValueFromIntObject(Object i) throws Exception {
        if (i == null) {
            return 0;
        }
        return UNSAFE.getInt(i, intValueFieldOffset);
    }

    public static char[] getStringValue(Object string) throws Exception {
        if (string == null) {
            return new char[0];
        }

        String s = (String) string;
        char[] charBuffer = new char[s.length()];
        UNSAFE.copyMemory(getFieldObject(string, charValueFieldOffset),
                baseCharArrayOffset, charBuffer, baseCharArrayOffset, s.length() * 2);

        return charBuffer;
    }

    public static char[] getCharArrayValue(Object baseObj, long charArrayOffset) throws Exception {
        char[] chars = (char[]) getFieldObject(baseObj, charArrayOffset);
        //char[] buffer = new char[chars.length];

        //UNSAFE.copyMemory(chars, baseCharArrayOffset, buffer, baseCharArrayOffset, chars.length * 2);


//        UNSAFE.copyMemory(i, baseCharArrayOffset, buffer, baseCharArrayOffset, size);
        return chars;
    }

    public static byte[] getUUIDValue(Object uuid) throws Exception {
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

    public static Object getFieldObject(Object baseObject, long offset) {
        return UNSAFE.getObject(baseObject, offset);
    }

    public static long getFieldOffset(Field field) {
        return UNSAFE.objectFieldOffset(field);
    }

    public static long getLongFieldValue(Object baseObj, Field field) throws Exception {
        return getLongValueFromLongObject(getFieldObject(baseObj, getFieldOffset(field)));
    }

    public static int getIntegerFieldValue(Object baseObj, Field field) throws Exception {
        return getIntValueFromIntObject(getFieldObject(baseObj, getFieldOffset(field)));
    }

    public static char[] getStringFieldValue(Object baseObj, Field field) throws Exception {
        return getStringValue(getFieldObject(baseObj, getFieldOffset(field)));
    }

    public static Unsafe getUnsafe() {
        return UNSAFE;
    }

}
