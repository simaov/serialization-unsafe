/**
 * Created by sima on 11/3/14.
 */
public class TestClasses {

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

        public TestInteger() {
        }

        public TestInteger(Integer aInteger) {
            this.aInteger = aInteger;
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
    }

    static class TestLongInteger {
        private Long aLong;
        private Integer aInteger;

        public TestLongInteger() {
        }

        public TestLongInteger(Long aLong, Integer aInteger) {
            this.aLong = aLong;
            this.aInteger = aInteger;
        }
    }

    static class TestLongIntegerString {
        private Long aLong;
        private Integer aInteger;
        private String string;

        public TestLongIntegerString(Long aLong, Integer aInteger, String string) {
            this.aLong = aLong;
            this.aInteger = aInteger;
            this.string = string;
        }
    }
}
