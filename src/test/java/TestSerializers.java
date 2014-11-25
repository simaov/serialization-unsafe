import org.jcoffee.serialization.SerializerUnsafe;

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

    static class TestBooleanPrimSerializer extends SerializerUnsafe<TestClasses.TestBooleanPrim> {
        public TestBooleanPrimSerializer(Class<TestClasses.TestBooleanPrim> testBooleanPrimClass) {
            super(testBooleanPrimClass);
        }
    }

    static class TestBooleanSerializer extends SerializerUnsafe<TestClasses.TestBoolean> {
        public TestBooleanSerializer(Class<TestClasses.TestBoolean> testBooleanClass) {
            super(testBooleanClass);
        }
    }

    static class TestDoublePrimSerializer extends SerializerUnsafe<TestClasses.TestDoublePrim> {
        public TestDoublePrimSerializer(Class<TestClasses.TestDoublePrim> testDoublePrimClass) {
            super(testDoublePrimClass);
        }
    }

    static class TestDoubleSerializer extends SerializerUnsafe<TestClasses.TestDouble> {
        public TestDoubleSerializer(Class<TestClasses.TestDouble> testDoubleClass) {
            super(testDoubleClass);
        }
    }

    static class TestFloatPrimSerializer extends SerializerUnsafe<TestClasses.TestFloatPrim> {
        public TestFloatPrimSerializer(Class<TestClasses.TestFloatPrim> testFloatPrimClass) {
            super(testFloatPrimClass);
        }
    }

    static class TestFloatSerializer extends SerializerUnsafe<TestClasses.TestFloat> {
        public TestFloatSerializer(Class<TestClasses.TestFloat> testFloatClass) {
            super(testFloatClass);
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

    static class TestEventSerializer extends SerializerUnsafe<TestClasses.TestEvent> {
        public TestEventSerializer(Class<TestClasses.TestEvent> testEventClass) {
            super(testEventClass);
        }
    }
}
