import org.jcoffee.serialization.SerializerUnsafe;

/**
 * Created by sima on 11/3/14.
 */
public class TestSerializers {

    static class TestLongSerializer extends SerializerUnsafe<TestClasses.TestLong> {
        public TestLongSerializer(Class<TestClasses.TestLong> testLongClass) {
            super(testLongClass);
        }
    }

    static class TestLongPrimSerializer extends SerializerUnsafe<TestClasses.TestLongPrim> {
        public TestLongPrimSerializer(Class<TestClasses.TestLongPrim> testLongPrimClass) {
            super(testLongPrimClass);
        }
    }

    static class TestIntegerSerializer extends SerializerUnsafe<TestClasses.TestInteger> {
        public TestIntegerSerializer(Class<TestClasses.TestInteger> testIntegerClass) {
            super(testIntegerClass);
        }
    }

    static class TestIntegerPrimSerializer extends SerializerUnsafe<TestClasses.TestIntPrim> {
        public TestIntegerPrimSerializer(Class<TestClasses.TestIntPrim> testIntPrimClass) {
            super(testIntPrimClass);
        }
    }

    static class TestStringSerializer extends SerializerUnsafe<TestClasses.TestString> {
        public TestStringSerializer(Class<TestClasses.TestString> testStringClass) {
            super(testStringClass);
        }
    }

    static class TestCharArraySerializer extends SerializerUnsafe<TestClasses.TestCharArray> {
        public TestCharArraySerializer(Class<TestClasses.TestCharArray> testCharArraySerializerClass) {
            super(testCharArraySerializerClass);
        }
    }

    static class TestUUIDSerializer extends SerializerUnsafe<TestClasses.TestUUID> {
        public TestUUIDSerializer(Class<TestClasses.TestUUID> testUUID) {
            super(testUUID);
        }
    }

    static class TestComplexSerializer extends SerializerUnsafe<TestClasses.TestComplex> {
        public TestComplexSerializer(Class<TestClasses.TestComplex> testComplexClass) {
            super(testComplexClass);
        }
    }
}
