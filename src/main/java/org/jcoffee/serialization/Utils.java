package org.jcoffee.serialization;

import static org.jcoffee.serialization.JavaTypes.JAVA_INTEGER_SIZE;

public class Utils {

    public static long longFromBytes(byte[] bytes, int offset) {
        long result = 0;
        for (int i = offset; i < 8 + offset; i++) {
            byte b = bytes[i];
            if (b < 0) {
                b &= ~(1 << 7);
                result = result << 1;
                result += 1;
                result = result << 7;
                result += b;
            } else {
                result = result << 8;
                result += b;
            }
        }
        return result;
    }

    public static long longFromBytes(byte[] bytes) {
        return longFromBytes(bytes, 0);
    }

    public static byte[] bytesFromLong(long l) {
        int index = 0;
        byte[] buffer = new byte[8];
        for (int i = 0; i < buffer.length; i++) {
            buffer[index++] = (byte) (l >> ((7 - i) * 8));
        }
        return buffer;
    }

    public static byte[] bytesFromInt(int k) {
        int index = 0;
        byte[] buffer = new byte[4];
        for (int i = 0; i < buffer.length; i++) {
            buffer[index++] = (byte) (k >> ((3 - i) * 8));
        }
        return buffer;
    }

    public static byte[] bytesFromChars(char[] chars) {
        int charsSize = chars.length * 2;
        byte[] byteBuffer = new byte[JAVA_INTEGER_SIZE + charsSize];

        int index = 0;

        for (int i = index; i < 4; i++) {
            byteBuffer[index++] = (byte) (charsSize >> ((3 - i) * 8));
        }

        for (int i = 0; i < chars.length; i++) {
            byteBuffer[index++] = (byte) (chars[i] >> 8);
            byteBuffer[index++] = (byte) (chars[i]);
        }
        return byteBuffer;
    }

    public static int intFromBytes(byte[] bytes, int offset) {
        int result = 0;
        for (int i = offset; i < 4 + offset; i++) {
            byte b = bytes[i];
            if (b < 0) {
                b &= ~(1 << 7);
                result = result << 1;
                result += 1;
                result = result << 7;
                result += b;
            } else {
                result = result << 8;
                result += b;
            }
        }
        return result;
    }

    public static int intFromBytes(byte[] bytes) {
        return intFromBytes(bytes, 0);
    }

    public static char[] charsFromBytes(byte[] bytes, int size, int offset) {
        char[] result = new char[size / 2];
        for (int i = 0; i < size; i += 2) {
            char c = (char) bytes[i + offset + JAVA_INTEGER_SIZE];
            c = (char) ((c << 8) + bytes[i + 1 + offset + JAVA_INTEGER_SIZE]);
            result[i / 2] = c;
        }
        return result;
    }
}
