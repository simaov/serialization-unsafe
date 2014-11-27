package org.jcoffee.serialization;

public class Utils {

    public static byte[] bytesFromShort(short sh) {
        int index = 0;
        byte[] buffer = new byte[JavaTypes.JAVA_SHORT_SIZE];
        for (int i = 0; i < buffer.length; i++) {
            buffer[index++] = (byte) (sh >> ((1 - i) << 3));
        }
        return buffer;
    }

    public static short shortFromBytes(byte[] bytes, int offset) {
        int result = 0;
        for (int i = offset; i < 2 + offset; i++) {
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
        return (short) result;
    }

    public static byte[] bytesFromInt(int k) {
        int index = 0;
        byte[] buffer = new byte[4];
        for (int i = 0; i < buffer.length; i++) {
            buffer[index++] = (byte) (k >> ((3 - i) << 3));
        }
        return buffer;
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

    public static byte[] bytesFromLong(long l) {
        int index = 0;
        byte[] buffer = new byte[8];
        for (int i = 0; i < buffer.length; i++) {
            buffer[index++] = (byte) (l >> ((7 - i) << 3));
        }
        return buffer;
    }

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

    public static byte[] byteFromBoolean(boolean b) {
        return b ? new byte[]{(byte) 1} : new byte[]{(byte) 0};
    }

    public static boolean booleanFromBytes(byte[] bytes, int offset) {
        byte b = bytes[offset];
        return b == 1;
    }

    public static byte[] bytesFromChar(char c) {
        byte[] buffer = new byte[JavaTypes.JAVA_CHARACTER_SIZE];
        buffer[0] = (byte) (((int) c & 0xFF00) >> 8);
        buffer[1] = (byte) (((int) c & 0xFF));
        return buffer;
    }


    public static char charFromBytes(byte[] bytes, int offset) {
        int result = 0;
        for (int i = offset; i < 2 + offset; i++) {
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
        return (char)result;
    }
}
