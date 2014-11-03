/**
 * Created by sima on 11/3/14.
 */
public class TestClasses {

    static class TestLong {
        private Long aLong;

        public TestLong() {
        }

        public TestLong(Long aLong) {
            this.aLong = aLong;
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
}
