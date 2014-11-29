import java.util.Arrays;
import java.util.UUID;

public class TestClasses {

    static class TestBytePrim {
        private byte b;

        public TestBytePrim(byte b) {
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestBytePrim bytePrim = (TestBytePrim) o;

            if (b != bytePrim.b) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return (int) b;
        }
    }

    static class TestShortPrim {
        private short sh;

        public TestShortPrim(short sh) {
            this.sh = sh;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestShortPrim shortPrim = (TestShortPrim) o;

            if (sh != shortPrim.sh) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return (int) sh;
        }
    }

    static class TestIntPrim {
        int i;

        public TestIntPrim(int i) {
            this.i = i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestIntPrim that = (TestIntPrim) o;

            if (i != that.i) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return i;
        }
    }

    static class TestLongPrim {
        long l;

        public TestLongPrim(long l) {
            this.l = l;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestLongPrim that = (TestLongPrim) o;

            if (l != that.l) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return (int) (l ^ (l >>> 32));
        }
    }

    static class TestFloatPrim {
        private float f;

        public TestFloatPrim(float f) {
            this.f = f;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestFloatPrim that = (TestFloatPrim) o;

            if (Float.compare(that.f, f) != 0) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return (f != +0.0f ? Float.floatToIntBits(f) : 0);
        }
    }

    static class TestDoublePrim {
        double d;

        public TestDoublePrim(double d) {
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestDoublePrim that = (TestDoublePrim) o;

            if (Double.compare(that.d, d) != 0) return false;

            return true;
        }

        @Override
        public int hashCode() {
            long temp = Double.doubleToLongBits(d);
            return (int) (temp ^ (temp >>> 32));
        }
    }

    static class TestBooleanPrim {
        boolean b;

        public TestBooleanPrim(boolean b) {
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestBooleanPrim that = (TestBooleanPrim) o;

            if (b != that.b) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return (b ? 1 : 0);
        }
    }

    static class TestCharPrim {
        public char c;

        public TestCharPrim(char c) {
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestCharPrim that = (TestCharPrim) o;

            if (c != that.c) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return (int) c;
        }
    }



    static class TestByte {
        private Byte b;

        public TestByte(Byte b) {
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestByte testByte = (TestByte) o;

            if (!b.equals(testByte.b)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return b.hashCode();
        }
    }

    static class TestShort {
        private Short sh;

        public TestShort(Short sh) {
            this.sh = sh;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestShort testShort = (TestShort) o;

            if (!sh.equals(testShort.sh)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return sh.hashCode();
        }
    }

    static class TestInteger {
        private Integer aInteger;

        public TestInteger(Integer aInteger) {
            this.aInteger = aInteger;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestInteger that = (TestInteger) o;

            if (!aInteger.equals(that.aInteger)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return aInteger.hashCode();
        }
    }

    static class TestLong {
        private Long aLong;
        public TestLong(Long aLong) {
            this.aLong = aLong;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestLong testLong = (TestLong) o;

            if (!aLong.equals(testLong.aLong)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return aLong.hashCode();
        }
    }

    static class TestFloat {
        Float f;

        public TestFloat(Float f) {
            this.f = f;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestFloat testFloat = (TestFloat) o;

            if (!f.equals(testFloat.f)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return f.hashCode();
        }
    }

    static class TestDouble {
        Double d;

        public TestDouble(Double d) {
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestDouble that = (TestDouble) o;

            if (!d.equals(that.d)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return d.hashCode();
        }
    }

    static class TestBoolean {
        Boolean b;

        public TestBoolean(Boolean b) {
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestBoolean that = (TestBoolean) o;

            if (!b.equals(that.b)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return b.hashCode();
        }
    }

    static class TestCharacter {
        public Character c;

        public TestCharacter(Character c) {
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestCharacter that = (TestCharacter) o;

            if (!c.equals(that.c)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return c.hashCode();
        }
    }



    static class TestByteArray {
        byte[] bytes;

        public TestByteArray(byte[] bytes) {
            this.bytes = bytes;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestByteArray that = (TestByteArray) o;

            if (!Arrays.equals(bytes, that.bytes)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(bytes);
        }
    }

    static class TestShortArray {
        short[] shorts;

        public TestShortArray(short[] shorts) {
            this.shorts = shorts;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestShortArray that = (TestShortArray) o;

            if (!Arrays.equals(shorts, that.shorts)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(shorts);
        }
    }

    static class TestIntArray {
        int[] ints;

        public TestIntArray(int[] ints) {
            this.ints = ints;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestIntArray that = (TestIntArray) o;

            if (!Arrays.equals(ints, that.ints)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(ints);
        }
    }

    static class TestLongArray {
        long[] longs;

        public TestLongArray(long[] longs) {
            this.longs = longs;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestLongArray that = (TestLongArray) o;

            if (!Arrays.equals(longs, that.longs)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(longs);
        }
    }

    static class TestFloatArray {
        float[] floats;

        public TestFloatArray(float[] floats) {
            this.floats = floats;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestFloatArray that = (TestFloatArray) o;

            if (!Arrays.equals(floats, that.floats)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(floats);
        }
    }

    static class TestDoubleArray {
        double[] doubles;

        public TestDoubleArray(double[] doubles) {
            this.doubles = doubles;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestDoubleArray that = (TestDoubleArray) o;

            if (!Arrays.equals(doubles, that.doubles)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(doubles);
        }
    }

    static class TestCharArray {
        char[] chars;

        public TestCharArray(char[] chars) {
            this.chars = chars;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestCharArray that = (TestCharArray) o;

            if (!Arrays.equals(chars, that.chars)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(chars);
        }
    }

    static class TestBooleanArray {
        boolean[] booleans;

        public TestBooleanArray(boolean[] booleans) {
            this.booleans = booleans;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestBooleanArray that = (TestBooleanArray) o;

            if (!Arrays.equals(booleans, that.booleans)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(booleans);
        }
    }


    static class TestString {
        private String string;

        TestString(String string) {
            this.string = string;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestString that = (TestString) o;

            if (!string.equals(that.string)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return string.hashCode();
        }
    }

    static class TestUUID {
        private UUID uuid;

        public TestUUID(UUID uuid) {
            this.uuid = uuid;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestUUID testUUID = (TestUUID) o;

            if (!uuid.equals(testUUID.uuid)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return uuid.hashCode();
        }
    }

    static class TestComplex {
        private byte b;
        private short sh;
        private int i;
        private long l;
        private float f;
        private double d;
        private boolean bool;
        private char c;

        private Byte aByte;
        private Short aShort;
        private Integer aInt;
        private Long aLong;
        private Float aFloat;
        private Double aDouble;
        private Boolean aBoolean;
        private Character character;

        private byte[] bytes;
        private short[] shorts;
        private int[] ints;
        private long[] longs;
        private float[] floats;
        private double[] doubles;
        private boolean[] booleans;
        private char[] chars;

        private String string;
        private UUID uuid;

        public TestComplex(byte b, short sh, int i, long l, float f, double d, boolean bool, char c, Byte aByte, Short aShort, Integer aInt, Long aLong, Float aFloat, Double aDouble, Boolean aBoolean, Character character, byte[] bytes, short[] shorts, int[] ints, long[] longs, float[] floats, double[] doubles, boolean[] booleans, char[] chars, String string, UUID uuid) {
            this.b = b;
            this.sh = sh;
            this.i = i;
            this.l = l;
            this.f = f;
            this.d = d;
            this.bool = bool;
            this.c = c;
            this.aByte = aByte;
            this.aShort = aShort;
            this.aInt = aInt;
            this.aLong = aLong;
            this.aFloat = aFloat;
            this.aDouble = aDouble;
            this.aBoolean = aBoolean;
            this.character = character;
            this.bytes = bytes;
            this.shorts = shorts;
            this.ints = ints;
            this.longs = longs;
            this.floats = floats;
            this.doubles = doubles;
            this.booleans = booleans;
            this.chars = chars;
            this.string = string;
            this.uuid = uuid;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestComplex that = (TestComplex) o;

            if (b != that.b) return false;
            if (bool != that.bool) return false;
            if (c != that.c) return false;
            if (Double.compare(that.d, d) != 0) return false;
            if (Float.compare(that.f, f) != 0) return false;
            if (i != that.i) return false;
            if (l != that.l) return false;
            if (sh != that.sh) return false;
            if (!aBoolean.equals(that.aBoolean)) return false;
            if (!aByte.equals(that.aByte)) return false;
            if (!aDouble.equals(that.aDouble)) return false;
            if (!aFloat.equals(that.aFloat)) return false;
            if (!aInt.equals(that.aInt)) return false;
            if (!aLong.equals(that.aLong)) return false;
            if (!aShort.equals(that.aShort)) return false;
            if (!Arrays.equals(booleans, that.booleans)) return false;
            if (!Arrays.equals(bytes, that.bytes)) return false;
            if (!character.equals(that.character)) return false;
            if (!Arrays.equals(chars, that.chars)) return false;
            if (!Arrays.equals(doubles, that.doubles)) return false;
            if (!Arrays.equals(floats, that.floats)) return false;
            if (!Arrays.equals(ints, that.ints)) return false;
            if (!Arrays.equals(longs, that.longs)) return false;
            if (!Arrays.equals(shorts, that.shorts)) return false;
            if (!string.equals(that.string)) return false;
            if (!uuid.equals(that.uuid)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = (int) b;
            result = 31 * result + (int) sh;
            result = 31 * result + i;
            result = 31 * result + (int) (l ^ (l >>> 32));
            result = 31 * result + (f != +0.0f ? Float.floatToIntBits(f) : 0);
            temp = Double.doubleToLongBits(d);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + (bool ? 1 : 0);
            result = 31 * result + (int) c;
            result = 31 * result + aByte.hashCode();
            result = 31 * result + aShort.hashCode();
            result = 31 * result + aInt.hashCode();
            result = 31 * result + aLong.hashCode();
            result = 31 * result + aFloat.hashCode();
            result = 31 * result + aDouble.hashCode();
            result = 31 * result + aBoolean.hashCode();
            result = 31 * result + character.hashCode();
            result = 31 * result + Arrays.hashCode(bytes);
            result = 31 * result + Arrays.hashCode(shorts);
            result = 31 * result + Arrays.hashCode(ints);
            result = 31 * result + Arrays.hashCode(longs);
            result = 31 * result + Arrays.hashCode(floats);
            result = 31 * result + Arrays.hashCode(doubles);
            result = 31 * result + Arrays.hashCode(chars);
            result = 31 * result + Arrays.hashCode(booleans);
            result = 31 * result + string.hashCode();
            result = 31 * result + uuid.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "TestComplex{" +
                    "b=" + b +
                    ", sh=" + sh +
                    ", i=" + i +
                    ", l=" + l +
                    ", f=" + f +
                    ", d=" + d +
                    ", bool=" + bool +
                    ", c=" + c +
                    ", aByte=" + aByte +
                    ", aShort=" + aShort +
                    ", aInt=" + aInt +
                    ", aLong=" + aLong +
                    ", aFloat=" + aFloat +
                    ", aDouble=" + aDouble +
                    ", aBoolean=" + aBoolean +
                    ", character=" + character +
                    ", bytes=" + Arrays.toString(bytes) +
                    ", shorts=" + Arrays.toString(shorts) +
                    ", ints=" + Arrays.toString(ints) +
                    ", longs=" + Arrays.toString(longs) +
                    ", floats=" + Arrays.toString(floats) +
                    ", doubles=" + Arrays.toString(doubles) +
                    ", booleans=" + Arrays.toString(booleans) +
                    ", chars=" + Arrays.toString(chars) +
                    ", string='" + string + '\'' +
                    ", uuid=" + uuid +
                    '}';
        }
    }

    static class TestEvent {
        private Long long1;
        private Long long2;
        private Integer int1;
        private Long long3;
        private String string1;
        private Long long4;
        private Long long5;
        private String string2;
        private UUID uuid1;

        public TestEvent(Long long1, Long long2, Integer int1, Long long3, String string1, Long long4, Long long5, String string2, UUID uuid1) {
            this.long1 = long1;
            this.long2 = long2;
            this.int1 = int1;
            this.long3 = long3;
            this.string1 = string1;
            this.long4 = long4;
            this.long5 = long5;
            this.string2 = string2;
            this.uuid1 = uuid1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestEvent testEvent = (TestEvent) o;

            if (!int1.equals(testEvent.int1)) return false;
            if (!long1.equals(testEvent.long1)) return false;
            if (!long2.equals(testEvent.long2)) return false;
            if (!long3.equals(testEvent.long3)) return false;
            if (!long4.equals(testEvent.long4)) return false;
            if (!long5.equals(testEvent.long5)) return false;
            if (!string1.equals(testEvent.string1)) return false;
            if (!string2.equals(testEvent.string2)) return false;
            if (!uuid1.equals(testEvent.uuid1)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = long1.hashCode();
            result = 31 * result + long2.hashCode();
            result = 31 * result + int1.hashCode();
            result = 31 * result + long3.hashCode();
            result = 31 * result + string1.hashCode();
            result = 31 * result + long4.hashCode();
            result = 31 * result + long5.hashCode();
            result = 31 * result + string2.hashCode();
            result = 31 * result + uuid1.hashCode();
            return result;
        }
    }
}
