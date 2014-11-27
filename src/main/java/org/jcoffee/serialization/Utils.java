package org.jcoffee.serialization;

import static org.jcoffee.serialization.JavaTypes.*;
import static org.jcoffee.serialization.UnsafeMemory.*;

public class Utils {

    public static byte[] bytesFromShort(short sh) {
        int index = 0;
        byte[] buffer = new byte[JAVA_SHORT_SIZE];
        for (int i = 0; i < buffer.length; i++) {
            buffer[index++] = (byte) (sh >> (i << 3));
        }
        return buffer;
    }

    public static short shortFromBytes(byte[] bytes, int offset) {
        short[] shorts = new short[1];
        UnsafeMemory.copyMemory(bytes, offset + baseByteArrayOffset, shorts, baseShortArrayOffset, JAVA_SHORT_SIZE);
        return shorts[0];
    }

    public static byte[] bytesFromInt(int k) {
        int index = 0;
        byte[] buffer = new byte[JAVA_INTEGER_SIZE];
        for (int i = 0; i < buffer.length; i++) {
            buffer[index++] = (byte) (k >> (i << 3));
        }
        return buffer;
    }

    public static int intFromBytes(byte[] bytes, int offset) {
        int[] ints = new int[1];
        UnsafeMemory.copyMemory(bytes, offset + baseByteArrayOffset, ints, baseIntArrayOffset, JAVA_INTEGER_SIZE);
        return ints[0];
    }

    public static byte[] bytesFromLong(long l) {
        int index = 0;
        byte[] buffer = new byte[JAVA_LONG_SIZE];
        for (int i = 0; i < buffer.length; i++) {
            buffer[index++] = (byte) (l >> (i << 3));
        }
        return buffer;
    }

    public static long longFromBytes(byte[] bytes, int offset) {
        long[] l = new long[1];
        UnsafeMemory.copyMemory(bytes, offset + baseByteArrayOffset, l, baseLongArrayOffset, JAVA_LONG_SIZE);
        return l[0];
    }

    public static byte[] byteFromBoolean(boolean b) {
        return b ? new byte[]{(byte) 1} : new byte[]{(byte) 0};
    }

    public static boolean booleanFromBytes(byte[] bytes, int offset) {
        byte b = bytes[offset];
        return b == 1;
    }

    public static byte[] bytesFromChar(char c) {
        byte[] buffer = new byte[JAVA_CHARACTER_SIZE];
        buffer[0] = (byte) (((int) c & 0xFF));
        buffer[1] = (byte) (((int) c & 0xFF00) >> 8);
        return buffer;
    }


    public static char charFromBytes(byte[] bytes, int offset) {
        char[] chars = new char[1];
        UnsafeMemory.copyMemory(bytes, offset + baseByteArrayOffset, chars, baseCharArrayOffset, JAVA_CHARACTER_SIZE);
        return chars[0];
    }
}