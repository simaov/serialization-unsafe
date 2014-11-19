import java.util.Arrays;
import java.util.UUID;

public class TestClasses {

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
        private long aL;
        private int aInt;
        private char[] chars;
        private Long aLong;
        private Integer integer;
        private String string;
        private UUID uuid;

        public TestComplex(long aL, int aInt, char[] chars, Long aLong, Integer integer, String string, UUID uuid) {
            this.aL = aL;
            this.aInt = aInt;
            this.chars = chars;
            this.aLong = aLong;
            this.integer = integer;
            this.string = string;
            this.uuid = uuid;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestComplex that = (TestComplex) o;

            if (aInt != that.aInt) return false;
            if (aL != that.aL) return false;
            if (!aLong.equals(that.aLong)) return false;
            if (!Arrays.equals(chars, that.chars)) return false;
            if (!integer.equals(that.integer)) return false;
            if (!string.equals(that.string)) return false;
            if (!uuid.equals(that.uuid)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (int) (aL ^ (aL >>> 32));
            result = 31 * result + aInt;
            result = 31 * result + Arrays.hashCode(chars);
            result = 31 * result + aLong.hashCode();
            result = 31 * result + integer.hashCode();
            result = 31 * result + string.hashCode();
            result = 31 * result + uuid.hashCode();
            return result;
        }
    }
}
