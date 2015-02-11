import org.jcoffee.serialization.SerializerUnsafe;

/**
 * Created by Aleksandr Simonchuk on 19.11.14.
 */
public class TestSerializers {

    static class TestBytePrimSerializer extends SerializerUnsafe<TestClasses.TestBytePrim> {
        public TestBytePrimSerializer(Class<TestClasses.TestBytePrim> testBytePrimClass) {
            super(testBytePrimClass);
        }
    }

    static class TestShortPrimSerializer extends SerializerUnsafe<TestClasses.TestShortPrim> {
        public TestShortPrimSerializer(Class<TestClasses.TestShortPrim> testShortPrimClass) {
            super(testShortPrimClass);
        }
    }

    static class TestIntegerPrimSerializer extends SerializerUnsafe<TestClasses.TestIntPrim> {
        public TestIntegerPrimSerializer(Class<TestClasses.TestIntPrim> testIntPrimClass) {
            super(testIntPrimClass);
        }
    }

    static class TestLongPrimSerializer extends SerializerUnsafe<TestClasses.TestLongPrim> {
        public TestLongPrimSerializer(Class<TestClasses.TestLongPrim> testLongPrimClass) {
            super(testLongPrimClass);
        }
    }

    static class TestFloatPrimSerializer extends SerializerUnsafe<TestClasses.TestFloatPrim> {
        public TestFloatPrimSerializer(Class<TestClasses.TestFloatPrim> testFloatPrimClass) {
            super(testFloatPrimClass);
        }
    }

    static class TestDoublePrimSerializer extends SerializerUnsafe<TestClasses.TestDoublePrim> {
        public TestDoublePrimSerializer(Class<TestClasses.TestDoublePrim> testDoublePrimClass) {
            super(testDoublePrimClass);
        }
    }

    static class TestBooleanPrimSerializer extends SerializerUnsafe<TestClasses.TestBooleanPrim> {
        public TestBooleanPrimSerializer(Class<TestClasses.TestBooleanPrim> testBooleanPrimClass) {
            super(testBooleanPrimClass);
        }
    }

    static class TestCharPrimSerializer extends SerializerUnsafe<TestClasses.TestCharPrim> {
        public TestCharPrimSerializer(Class<TestClasses.TestCharPrim> testCharPrimClass) {
            super(testCharPrimClass);
        }
    }



    static class TestByteSerializer extends SerializerUnsafe<TestClasses.TestByte> {
        public TestByteSerializer(Class<TestClasses.TestByte> testByteClass) {
            super(testByteClass);
        }
    }

    static class TestShortSerializer extends SerializerUnsafe<TestClasses.TestShort> {
        public TestShortSerializer(Class<TestClasses.TestShort> testShortClass) {
            super(testShortClass);
        }
    }

    static class TestIntegerSerializer extends SerializerUnsafe<TestClasses.TestInteger> {
        public TestIntegerSerializer(Class<TestClasses.TestInteger> testIntegerClass) {
            super(testIntegerClass);
        }
    }

    static class TestLongSerializer extends SerializerUnsafe<TestClasses.TestLong> {
        public TestLongSerializer(Class<TestClasses.TestLong> testLongClass) {
            super(testLongClass);
        }
    }

    static class TestFloatSerializer extends SerializerUnsafe<TestClasses.TestFloat> {
        public TestFloatSerializer(Class<TestClasses.TestFloat> testFloatClass) {
            super(testFloatClass);
        }
    }

    static class TestDoubleSerializer extends SerializerUnsafe<TestClasses.TestDouble> {
        public TestDoubleSerializer(Class<TestClasses.TestDouble> testDoubleClass) {
            super(testDoubleClass);
        }
    }

    static class TestBooleanSerializer extends SerializerUnsafe<TestClasses.TestBoolean> {
        public TestBooleanSerializer(Class<TestClasses.TestBoolean> testBooleanClass) {
            super(testBooleanClass);
        }
    }

    static class TestCharacterSerializer extends SerializerUnsafe<TestClasses.TestCharacter> {
        public TestCharacterSerializer(Class<TestClasses.TestCharacter> testCharacterClass) {
            super(testCharacterClass);
        }
    }


    static class TestByteArraySerializer extends SerializerUnsafe<TestClasses.TestByteArray> {
        public TestByteArraySerializer(Class<TestClasses.TestByteArray>  testByteArrayClass) {
            super(testByteArrayClass);
        }
    }

    static class TestShortArraySerializer extends SerializerUnsafe<TestClasses.TestShortArray> {
        public TestShortArraySerializer(Class<TestClasses.TestShortArray> testShortArrayClass) {
            super(testShortArrayClass);
        }
    }

    static class TestIntArraySerializer extends SerializerUnsafe<TestClasses.TestIntArray> {
        public TestIntArraySerializer(Class<TestClasses.TestIntArray> testIntArrayClass) {
            super(testIntArrayClass);
        }
    }

    static class TestLongArraySerializer extends SerializerUnsafe<TestClasses.TestLongArray> {
        public TestLongArraySerializer(Class<TestClasses.TestLongArray> testLongArrayClass) {
            super(testLongArrayClass);
        }
    }

    static class TestFloatArraySerializer extends SerializerUnsafe<TestClasses.TestFloatArray> {
        public TestFloatArraySerializer(Class<TestClasses.TestFloatArray> testFloatArrayClass) {
            super(testFloatArrayClass);
        }
    }

    static class TestDoubleArraySerializer extends SerializerUnsafe<TestClasses.TestDoubleArray> {
        public TestDoubleArraySerializer(Class<TestClasses.TestDoubleArray> testDoubleArrayClass) {
            super(testDoubleArrayClass);
        }
    }

    static class TestBooleanArraySerializer extends SerializerUnsafe<TestClasses.TestBooleanArray> {
        public TestBooleanArraySerializer(Class<TestClasses.TestBooleanArray> testBooleanArrayClass) {
            super(testBooleanArrayClass);
        }
    }

    static class TestCharArraySerializer extends SerializerUnsafe<TestClasses.TestCharArray> {
        public TestCharArraySerializer(Class<TestClasses.TestCharArray> testCharArraySerializerClass) {
            super(testCharArraySerializerClass);
        }
    }



    static class TestStringSerializer extends SerializerUnsafe<TestClasses.TestString> {
        public TestStringSerializer(Class<TestClasses.TestString> testStringClass) {
            super(testStringClass);
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
