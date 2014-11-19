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
