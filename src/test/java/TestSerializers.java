/**
 * Created by sima on 11/3/14.
 */
public class TestSerializers {

    static class TestLongSerializer extends AbstractSerializerUnsafe<TestClasses.TestLong> {
        public TestLongSerializer(Class<TestClasses.TestLong> testLongClass) {
            super(testLongClass);
        }
    }

    static class TestIntegerSerializer extends AbstractSerializerUnsafe<TestClasses.TestInteger> {
        public TestIntegerSerializer(Class<TestClasses.TestInteger> testIntegerClass) {
            super(testIntegerClass);
        }
    }

    static class TestStringSerializer extends AbstractSerializerUnsafe<TestClasses.TestString> {
        public TestStringSerializer(Class<TestClasses.TestString> testStringClass) {
            super(testStringClass);
        }
    }

    static class TestCharArraySerializer extends AbstractSerializerUnsafe<TestClasses.TestCharArray> {
        public TestCharArraySerializer(Class<TestClasses.TestCharArray> testCharArraySerializerClass) {
            super(testCharArraySerializerClass);
        }
    }

    static class TestLongIntegerSerializer extends AbstractSerializerUnsafe<TestClasses.TestLongInteger> {
        public TestLongIntegerSerializer(Class<TestClasses.TestLongInteger> testLongIntegerClass) {
            super(testLongIntegerClass);
        }
    }
}
