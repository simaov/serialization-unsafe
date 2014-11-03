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
}
