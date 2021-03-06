import org.jcoffee.serialization.SerializerFactory;
import org.jcoffee.serialization.SerializerUnsafe;
import org.jcoffee.serialization.SerializerUnsafeI;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created by Aleksandr Simonchuk on 19.11.14.
 */
public class UnsafeMemoryTest {

    private static Random random = new Random(System.currentTimeMillis());

    @Test
    public void testBytePrimSerialization() throws Exception {
        byte b = (byte) random.nextInt();
        TestClasses.TestBytePrim testBytePrim = new TestClasses.TestBytePrim(b);
        SerializerUnsafeI<TestClasses.TestBytePrim> testBytePrimSerializer = SerializerFactory.getSerializer(TestClasses.TestBytePrim.class);
        byte[] serialize = testBytePrimSerializer.serialize(testBytePrim);
        TestClasses.TestBytePrim deserialize = testBytePrimSerializer.deserialize(serialize);
        assertEquals(testBytePrim, deserialize);
    }

    @Test
    public void testShortPrimSerialization() throws Exception {
        short sh = (short) random.nextInt();
        TestClasses.TestShortPrim testShortPrim = new TestClasses.TestShortPrim(sh);
        SerializerUnsafeI<TestClasses.TestShortPrim> testShortPrimSerializer = SerializerFactory.getSerializer(TestClasses.TestShortPrim.class);
        byte[] serialize = testShortPrimSerializer.serialize(testShortPrim);
        TestClasses.TestShortPrim deserialize = testShortPrimSerializer.deserialize(serialize);
        assertEquals(testShortPrim, deserialize);
    }

    @Test
    public void testIntPrimSerialization() throws Exception {
        int i = random.nextInt();
        TestClasses.TestIntPrim testIntPrim = new TestClasses.TestIntPrim(i);
        SerializerUnsafeI<TestClasses.TestIntPrim> testIntegerPrimSerializer = SerializerFactory.getSerializer(TestClasses.TestIntPrim.class);
        byte[] serialize = testIntegerPrimSerializer.serialize(testIntPrim);
        TestClasses.TestIntPrim deserialize = testIntegerPrimSerializer.deserialize(serialize);
        assertEquals(testIntPrim, deserialize);
    }

    @Test
    public void testLongPrimSerialization() throws Exception {
        long aLong = random.nextLong();
        TestClasses.TestLongPrim testLongPrim = new TestClasses.TestLongPrim(aLong);
        SerializerUnsafeI<TestClasses.TestLongPrim> testLongPrimSerializer = SerializerFactory.getSerializer(TestClasses.TestLongPrim.class);
        byte[] serialize = testLongPrimSerializer.serialize(testLongPrim);
        TestClasses.TestLongPrim deserialize = testLongPrimSerializer.deserialize(serialize);
        assertEquals(testLongPrim, deserialize);
    }

    @Test
    public void testFloatPrimSerialization() throws Exception {
        float f = random.nextFloat();
        TestClasses.TestFloatPrim testFloatPrim = new TestClasses.TestFloatPrim(f);
        SerializerUnsafeI<TestClasses.TestFloatPrim> testFloatPrimSerializer = SerializerFactory.getSerializer(TestClasses.TestFloatPrim.class);
        byte[] serialize = testFloatPrimSerializer.serialize(testFloatPrim);
        TestClasses.TestFloatPrim deserialize = testFloatPrimSerializer.deserialize(serialize);
        assertEquals(testFloatPrim, deserialize);
    }

    @Test
    public void testDoublePrimSerialization() throws Exception {
        double d = random.nextDouble();
        TestClasses.TestDoublePrim testDoublePrim = new TestClasses.TestDoublePrim(d);
        SerializerUnsafeI<TestClasses.TestDoublePrim> testDoublePrimSerializer = SerializerFactory.getSerializer(TestClasses.TestDoublePrim.class);
        byte[] serialize = testDoublePrimSerializer.serialize(testDoublePrim);
        TestClasses.TestDoublePrim deserialize = testDoublePrimSerializer.deserialize(serialize);
        assertEquals(testDoublePrim, deserialize);
    }

    @Test
    public void testBooleanPrimSerialization() throws Exception {
        boolean b = random.nextBoolean();
        TestClasses.TestBooleanPrim testBooleanPrim = new TestClasses.TestBooleanPrim(b);
        SerializerUnsafeI<TestClasses.TestBooleanPrim> testBooleanPrimSerializer = SerializerFactory.getSerializer(TestClasses.TestBooleanPrim.class);
        byte[] serialize = testBooleanPrimSerializer.serialize(testBooleanPrim);
        TestClasses.TestBooleanPrim deserialize = testBooleanPrimSerializer.deserialize(serialize);
        assertEquals(testBooleanPrim, deserialize);
    }

    @Test
    public void testCharPrimSerialization() throws Exception {
        char c = (char) random.nextInt(Character.MAX_VALUE);
        TestClasses.TestCharPrim testCharPrim = new TestClasses.TestCharPrim(c);
        SerializerUnsafeI<TestClasses.TestCharPrim> testCharPrimSerializer = SerializerFactory.getSerializer(TestClasses.TestCharPrim.class);
        byte[] serialize = testCharPrimSerializer.serialize(testCharPrim);
        TestClasses.TestCharPrim deserialize = testCharPrimSerializer.deserialize(serialize);
        assertEquals(testCharPrim, deserialize);
    }




    @Test
    public void testByteSerialization() throws Exception {
        Byte b = (byte) random.nextInt();
        TestClasses.TestByte testByte = new TestClasses.TestByte(b);
        SerializerUnsafeI<TestClasses.TestByte> testByteSerializer = SerializerFactory.getSerializer(TestClasses.TestByte.class);
        byte[] serialize = testByteSerializer.serialize(testByte);
        TestClasses.TestByte deserialize = testByteSerializer.deserialize(serialize);
        assertEquals(testByte, deserialize);

        testByte = new TestClasses.TestByte(null);
        serialize = testByteSerializer.serialize(testByte);
        deserialize = testByteSerializer.deserialize(serialize);
        assertEquals(testByte, deserialize);
    }

    @Test
    public void testShortSerialization() throws Exception {
        Short sh = (short) random.nextInt();
        TestClasses.TestShort testShort = new TestClasses.TestShort(sh);
        SerializerUnsafeI<TestClasses.TestShort> testShortSerializer = SerializerFactory.getSerializer(TestClasses.TestShort.class);
        byte[] serialize = testShortSerializer.serialize(testShort);
        TestClasses.TestShort deserialize = testShortSerializer.deserialize(serialize);
        assertEquals(testShort, deserialize);

        testShort = new TestClasses.TestShort(null);
        serialize = testShortSerializer.serialize(testShort);
        deserialize = testShortSerializer.deserialize(serialize);
        assertEquals(testShort, deserialize);
    }

    @Test
    public void testIntegerSerialization() throws Exception {
        Integer i = random.nextInt();
        TestClasses.TestInteger testInteger = new TestClasses.TestInteger(i);
        SerializerUnsafeI<TestClasses.TestInteger> testIntegerSerializer = SerializerFactory.getSerializer(TestClasses.TestInteger.class);
        byte[] serialize = testIntegerSerializer.serialize(testInteger);
        TestClasses.TestInteger deserialize = testIntegerSerializer.deserialize(serialize);
        assertEquals(testInteger, deserialize);

        testInteger = new TestClasses.TestInteger(null);
        serialize = testIntegerSerializer.serialize(testInteger);
        deserialize = testIntegerSerializer.deserialize(serialize);
        assertEquals(testInteger, deserialize);
    }

    @Test
    public void testLongSerialization() throws Exception {
        Long aLong = random.nextLong();
        TestClasses.TestLong testLong = new TestClasses.TestLong(aLong);
        SerializerUnsafeI<TestClasses.TestLong> testLongSerializer = SerializerFactory.getSerializer(TestClasses.TestLong.class);
        byte[] serialize = testLongSerializer.serialize(testLong);
        TestClasses.TestLong deserialize = testLongSerializer.deserialize(serialize);
        assertEquals(testLong, deserialize);

        testLong = new TestClasses.TestLong(null);
        serialize = testLongSerializer.serialize(testLong);
        deserialize = testLongSerializer.deserialize(serialize);
        assertEquals(testLong, deserialize);
    }

    @Test
    public void testFloatSerialization() throws Exception {
        Float f = random.nextFloat();
        TestClasses.TestFloat testFloat = new TestClasses.TestFloat(f);
        SerializerUnsafeI<TestClasses.TestFloat> testFloatSerializer = SerializerFactory.getSerializer(TestClasses.TestFloat.class);
        byte[] serialize = testFloatSerializer.serialize(testFloat);
        TestClasses.TestFloat deserialize = testFloatSerializer.deserialize(serialize);
        assertEquals(testFloat, deserialize);

        testFloat = new TestClasses.TestFloat(null);
        serialize = testFloatSerializer.serialize(testFloat);
        deserialize = testFloatSerializer.deserialize(serialize);
        assertEquals(testFloat, deserialize);
    }

    @Test
    public void testDoubleSerialization() throws Exception {
        Double d = random.nextDouble();
        TestClasses.TestDouble testDouble = new TestClasses.TestDouble(d);
        SerializerUnsafeI<TestClasses.TestDouble> testDoubleSerializer = SerializerFactory.getSerializer(TestClasses.TestDouble.class);
        byte[] serialize = testDoubleSerializer.serialize(testDouble);
        TestClasses.TestDouble deserialize = testDoubleSerializer.deserialize(serialize);
        assertEquals(testDouble, deserialize);

        testDouble = new TestClasses.TestDouble(null);
        serialize = testDoubleSerializer.serialize(testDouble);
        deserialize = testDoubleSerializer.deserialize(serialize);
        assertEquals(testDouble, deserialize);
    }

    @Test
    public void testBooleanSerialization() throws Exception {
        Boolean b = random.nextBoolean();
        TestClasses.TestBoolean testBoolean = new TestClasses.TestBoolean(b);
        SerializerUnsafeI<TestClasses.TestBoolean> testBooleanSerializer = SerializerFactory.getSerializer(TestClasses.TestBoolean.class);
        byte[] serialize = testBooleanSerializer.serialize(testBoolean);
        TestClasses.TestBoolean deserialize = testBooleanSerializer.deserialize(serialize);
        assertEquals(testBoolean, deserialize);

        testBoolean = new TestClasses.TestBoolean(null);
        serialize = testBooleanSerializer.serialize(testBoolean);
        deserialize = testBooleanSerializer.deserialize(serialize);
        assertEquals(testBoolean, deserialize);
    }

    @Test
    public void testCharacterSerialization() throws Exception {
        Character c = (char) random.nextInt(Character.MAX_VALUE);
        TestClasses.TestCharacter testCharacter = new TestClasses.TestCharacter(c);
        SerializerUnsafeI<TestClasses.TestCharacter> testCharacterSerializer = SerializerFactory.getSerializer(TestClasses.TestCharacter.class);
        byte[] serialize = testCharacterSerializer.serialize(testCharacter);
        TestClasses.TestCharacter deserialize = testCharacterSerializer.deserialize(serialize);
        assertEquals(testCharacter, deserialize);

        testCharacter = new TestClasses.TestCharacter(null);
        serialize = testCharacterSerializer.serialize(testCharacter);
        deserialize = testCharacterSerializer.deserialize(serialize);
        assertEquals(testCharacter, deserialize);
    }



    @Test
    public void testByteArraySerialization() throws Exception {
        byte[] bytes = new byte[] {(byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(),
                (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt()};
        TestClasses.TestByteArray testByteArray = new TestClasses.TestByteArray(bytes);
        SerializerUnsafeI<TestClasses.TestByteArray> testByteArraySerializer = SerializerFactory.getSerializer(TestClasses.TestByteArray.class);
        byte[] serialize = testByteArraySerializer.serialize(testByteArray);
        TestClasses.TestByteArray deserialize = testByteArraySerializer.deserialize(serialize);
        assertEquals(testByteArray, deserialize);

        testByteArray = new TestClasses.TestByteArray(null);
        serialize = testByteArraySerializer.serialize(testByteArray);
        deserialize = testByteArraySerializer.deserialize(serialize);
        assertEquals(testByteArray, deserialize);
    }

    @Test
    public void testShortArraySerialization() throws Exception {
        short[] shorts = new short[] {(short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(),
                (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt()};
        TestClasses.TestShortArray testShortArray = new TestClasses.TestShortArray(shorts);
        SerializerUnsafeI<TestClasses.TestShortArray> testShortArraySerializer = SerializerFactory.getSerializer(TestClasses.TestShortArray.class);
        byte[] serialize = testShortArraySerializer.serialize(testShortArray);
        TestClasses.TestShortArray deserialize = testShortArraySerializer.deserialize(serialize);
        assertEquals(testShortArray, deserialize);

        testShortArray = new TestClasses.TestShortArray(null);
        serialize = testShortArraySerializer.serialize(testShortArray);
        deserialize = testShortArraySerializer.deserialize(serialize);
        assertEquals(testShortArray, deserialize);
    }

    @Test
    public void testIntArraySerialization() throws Exception {
        int[] ints = new int[] {random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt(),
                random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt()};
        TestClasses.TestIntArray testIntArray = new TestClasses.TestIntArray(ints);
        SerializerUnsafeI<TestClasses.TestIntArray> testIntArraySerializer = SerializerFactory.getSerializer(TestClasses.TestIntArray.class);
        byte[] serialize = testIntArraySerializer.serialize(testIntArray);
        TestClasses.TestIntArray deserialize = testIntArraySerializer.deserialize(serialize);
        assertEquals(testIntArray, deserialize);

        testIntArray = new TestClasses.TestIntArray(null);
        serialize = testIntArraySerializer.serialize(testIntArray);
        deserialize = testIntArraySerializer.deserialize(serialize);
        assertEquals(testIntArray, deserialize);
    }

    @Test
    public void testLongArraySerialization() throws Exception {
        long[] longs = new long[] {random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(),
                random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong()};
        TestClasses.TestLongArray testLongArray = new TestClasses.TestLongArray(longs);
        SerializerUnsafeI<TestClasses.TestLongArray> testLongArraySerializer = SerializerFactory.getSerializer(TestClasses.TestLongArray.class);
        byte[] serialize = testLongArraySerializer.serialize(testLongArray);
        TestClasses.TestLongArray deserialize = testLongArraySerializer.deserialize(serialize);
        assertEquals(testLongArray, deserialize);

        testLongArray = new TestClasses.TestLongArray(null);
        serialize = testLongArraySerializer.serialize(testLongArray);
        deserialize = testLongArraySerializer.deserialize(serialize);
        assertEquals(testLongArray, deserialize);
    }

    @Test
    public void testFloatArraySerialization() throws Exception {
        float[] floats = new float[] {random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(),
                random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat()};
        TestClasses.TestFloatArray testFloatArray = new TestClasses.TestFloatArray(floats);
        SerializerUnsafeI<TestClasses.TestFloatArray> testFloatArraySerializer = SerializerFactory.getSerializer(TestClasses.TestFloatArray.class);
        byte[] serialize = testFloatArraySerializer.serialize(testFloatArray);
        TestClasses.TestFloatArray deserialize = testFloatArraySerializer.deserialize(serialize);
        assertEquals(testFloatArray, deserialize);

        testFloatArray = new TestClasses.TestFloatArray(null);
        serialize = testFloatArraySerializer.serialize(testFloatArray);
        deserialize = testFloatArraySerializer.deserialize(serialize);
        assertEquals(testFloatArray, deserialize);
    }

    @Test
    public void testDoubleArraySerialization() throws Exception {
        double[] doubles = new double[] {random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(),
                random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()};
        TestClasses.TestDoubleArray testDoubleArray = new TestClasses.TestDoubleArray(doubles);
        SerializerUnsafeI<TestClasses.TestDoubleArray> testDoubleArraySerializer = SerializerFactory.getSerializer(TestClasses.TestDoubleArray.class);
        byte[] serialize = testDoubleArraySerializer.serialize(testDoubleArray);
        TestClasses.TestDoubleArray deserialize = testDoubleArraySerializer.deserialize(serialize);
        assertEquals(testDoubleArray, deserialize);

        testDoubleArray = new TestClasses.TestDoubleArray(null);
        serialize = testDoubleArraySerializer.serialize(testDoubleArray);
        deserialize = testDoubleArraySerializer.deserialize(serialize);
        assertEquals(testDoubleArray, deserialize);
    }

    @Test
    public void testBooleanArraySerialization() throws Exception {
        boolean[] booleans = new boolean[] {random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(),
                random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean()};
        TestClasses.TestBooleanArray testBooleanArray = new TestClasses.TestBooleanArray(booleans);
        SerializerUnsafeI<TestClasses.TestBooleanArray> testBooleanArraySerializer = SerializerFactory.getSerializer(TestClasses.TestBooleanArray.class);
        byte[] serialize = testBooleanArraySerializer.serialize(testBooleanArray);
        TestClasses.TestBooleanArray deserialize = testBooleanArraySerializer.deserialize(serialize);
        assertEquals(testBooleanArray, deserialize);

        testBooleanArray = new TestClasses.TestBooleanArray(null);
        serialize = testBooleanArraySerializer.serialize(testBooleanArray);
        deserialize = testBooleanArraySerializer.deserialize(serialize);
        assertEquals(testBooleanArray, deserialize);
    }

    @Test
    public void testCharArraySerialization() throws Exception {
        char[] chars = UUID.randomUUID().toString().toCharArray();
        TestClasses.TestCharArray testCharArray = new TestClasses.TestCharArray(chars);
        SerializerUnsafeI<TestClasses.TestCharArray> testCharArraySerializer = SerializerFactory.getSerializer(TestClasses.TestCharArray.class);
        byte[] serialize = testCharArraySerializer.serialize(testCharArray);
        TestClasses.TestCharArray deserialize = testCharArraySerializer.deserialize(serialize);
        assertEquals(testCharArray, deserialize);

        testCharArray = new TestClasses.TestCharArray(null);
        serialize = testCharArraySerializer.serialize(testCharArray);
        deserialize = testCharArraySerializer.deserialize(serialize);
        assertEquals(testCharArray, deserialize);
    }




    @Test
    public void testStringSerialization() throws Exception {
        String string = UUID.randomUUID().toString();
        TestClasses.TestString testString = new TestClasses.TestString(string);
        SerializerUnsafeI<TestClasses.TestString> testStringSerializer = SerializerFactory.getSerializer(TestClasses.TestString.class);
        byte[] serialize = testStringSerializer.serialize(testString);
        TestClasses.TestString deserialize = testStringSerializer.deserialize(serialize);
        assertEquals(testString, deserialize);

        testString = new TestClasses.TestString(null);
        serialize = testStringSerializer.serialize(testString);
        deserialize = testStringSerializer.deserialize(serialize);
        assertEquals(testString, deserialize);
    }

    @Test
    public void testUUIDSerialization() throws Exception {
        UUID uuid = UUID.randomUUID();
        TestClasses.TestUUID testUUID = new TestClasses.TestUUID(uuid);
        SerializerUnsafeI<TestClasses.TestUUID> testUUIDSerializer = SerializerFactory.getSerializer(TestClasses.TestUUID.class);
        byte[] serialize = testUUIDSerializer.serialize(testUUID);
        TestClasses.TestUUID deserialize = testUUIDSerializer.deserialize(serialize);
        assertEquals(testUUID, deserialize);

        testUUID = new TestClasses.TestUUID(null);
        serialize = testUUIDSerializer.serialize(testUUID);
        deserialize = testUUIDSerializer.deserialize(serialize);
        assertEquals(testUUID, deserialize);
    }




    @Test
    public void testComplexSerialization() throws Exception {
        TestClasses.TestComplex testComplex = new TestClasses.TestComplex(
                (byte) random.nextInt(),
                (short) random.nextInt(),
                random.nextInt(),
                random.nextLong(),
                random.nextFloat(),
                random.nextDouble(),
                random.nextBoolean(),
                (char) random.nextInt(Character.MAX_VALUE),
                (byte) random.nextInt(),
                (short) random.nextInt(),
                random.nextInt(),
                random.nextLong(),
                random.nextFloat(),
                random.nextDouble(),
                random.nextBoolean(),
                (char) random.nextInt(Character.MAX_VALUE),
                new byte[] {(byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(),
                        (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt()},
                new short[] {(short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(),
                        (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt()},
                new int[] {random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt(),
                        random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt()},
                new long[] {random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(),
                        random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong()},
                new float[] {random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(),
                        random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat()},
                new double[] {random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(),
                        random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()},
                new boolean[] {random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(),
                        random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean()},
                UUID.randomUUID().toString().toCharArray(),

                UUID.randomUUID().toString(),
                UUID.randomUUID());
        SerializerUnsafeI<TestClasses.TestComplex> testComplexSerializer = SerializerFactory.getSerializer(TestClasses.TestComplex.class);
        byte[] serialize = testComplexSerializer.serialize(testComplex);
        TestClasses.TestComplex deserialize = testComplexSerializer.deserialize(serialize);
        assertEquals(testComplex, deserialize);

        testComplex = new TestClasses.TestComplex(
                (byte) random.nextInt(),
                (short) random.nextInt(),
                random.nextInt(),
                random.nextLong(),
                random.nextFloat(),
                random.nextDouble(),
                random.nextBoolean(),
                (char) random.nextInt(Character.MAX_VALUE),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,

                null,
                null
        );
        serialize = testComplexSerializer.serialize(testComplex);
        deserialize = testComplexSerializer.deserialize(serialize);
        assertEquals(testComplex, deserialize);

        testComplex = new TestClasses.TestComplex(
                (byte) random.nextInt(),
                (short) random.nextInt(),
                random.nextInt(),
                random.nextLong(),
                random.nextFloat(),
                random.nextDouble(),
                random.nextBoolean(),
                (char) random.nextInt(Character.MAX_VALUE),
                null,
                (short) random.nextInt(),
                random.nextInt(),
                null,
                random.nextFloat(),
                null,
                random.nextBoolean(),
                null,
                new byte[] {(byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(),
                        (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt()},
                null,
                new int[] {random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt(),
                        random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt()},
                null,
                new float[] {random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(),
                        random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat()},
                null,
                new boolean[] {random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(),
                        random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean()},
                null,

                UUID.randomUUID().toString(),
                null);
        serialize = testComplexSerializer.serialize(testComplex);
        deserialize = testComplexSerializer.deserialize(serialize);
        assertEquals(testComplex, deserialize);
    }

    @Test
    public void testEventSerialization() throws Exception {
        TestClasses.TestEvent testEvent = new TestClasses.TestEvent(
                random.nextLong(),
                random.nextLong(),
                random.nextInt(),
                random.nextLong(),
                UUID.randomUUID().toString(),
                random.nextLong(),
                random.nextLong(),
                UUID.randomUUID().toString(),
                UUID.randomUUID()
        );
        SerializerUnsafeI<TestClasses.TestEvent> testEventSerializer = SerializerFactory.getSerializer(TestClasses.TestEvent.class);
        byte[] serialize = testEventSerializer.serialize(testEvent);
        TestClasses.TestEvent deserialize = testEventSerializer.deserialize(serialize);
        assertEquals(testEvent, deserialize);
    }

    @Test
    public void testNull() throws Exception {
        TestClasses.TestEvent testEvent = null;
        SerializerUnsafeI<TestClasses.TestEvent> testEventSerializer = SerializerFactory.getSerializer(TestClasses.TestEvent.class);
        byte[] serialize = testEventSerializer.serialize(testEvent);
        TestClasses.TestEvent deserialize = testEventSerializer.deserialize(serialize);
        assertEquals(testEvent, deserialize);
    }

    @Ignore
    @Test
    public void testPerformance() throws Exception {
        int objCount = 1_000_000;
        List<TestClasses.TestComplex> list = new ArrayList<>(objCount);
        System.out.print("Creating [" + objCount + "] objects ... ");
        for (int i = 0; i < objCount; i++) {
            TestClasses.TestComplex testComplex = new TestClasses.TestComplex(
                    (byte) random.nextInt(),
                    (short) random.nextInt(),
                    random.nextInt(),
                    random.nextLong(),
                    random.nextFloat(),
                    random.nextDouble(),
                    random.nextBoolean(),
                    (char) random.nextInt(Character.MAX_VALUE),
                    (byte) random.nextInt(),
                    (short) random.nextInt(),
                    random.nextInt(),
                    random.nextLong(),
                    random.nextFloat(),
                    random.nextDouble(),
                    random.nextBoolean(),
                    (char) random.nextInt(Character.MAX_VALUE),
                    new byte[] {(byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(),
                            (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt(), (byte)random.nextInt()},
                    new short[] {(short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(),
                            (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt(), (short)random.nextInt()},
                    new int[] {random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt(),
                            random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt()},
                    new long[] {random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(),
                            random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong()},
                    new float[] {random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(),
                            random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat()},
                    new double[] {random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(),
                            random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()},
                    new boolean[] {random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(),
                            random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean()},
                    UUID.randomUUID().toString().toCharArray(),

                    UUID.randomUUID().toString(),
                    UUID.randomUUID());
            list.add(testComplex);
        }

        System.out.println("Done.");

        SerializerUnsafeI<TestClasses.TestComplex> testComplexSerializer = SerializerFactory.getSerializer(TestClasses.TestComplex.class);

        for (int j = 0; j < 10; j++) {
            long start = System.nanoTime();
            for (int i = 0; i < list.size(); i++) {
                byte[] b = testComplexSerializer.serialize(list.get(i));
                TestClasses.TestComplex deserialize = testComplexSerializer.deserialize(b);
            }

            System.out.println("Time per object: ~ " + (System.nanoTime() - start) / objCount + " ns.");
            Thread.yield();
        }
    }

    @Ignore
    @Test
    public void testEventPerformance() throws Exception {
        SerializerUnsafeI<TestClasses.TestEvent> testEventSerializer = SerializerFactory.getSerializer(TestClasses.TestEvent.class);

        int objCount = 1_000_000;

        List<TestClasses.TestEvent> list = new ArrayList<>(objCount);
        System.out.print("Creating [" + objCount + "] objects ... ");
        for (int i = 0; i < objCount; i++) {
            TestClasses.TestEvent testEvent = new TestClasses.TestEvent(
                    random.nextLong(),
                    random.nextLong(),
                    random.nextInt(),
                    random.nextLong(),
                    UUID.randomUUID().toString(),
                    random.nextLong(),
                    random.nextLong(),
                    UUID.randomUUID().toString(),
                    UUID.randomUUID()
            );

            list.add(testEvent);
        }

        System.out.println("Done.");

        for (int j = 0; j < 10; j++) {
            long start = System.nanoTime();
            for (int i = 0; i < list.size(); i++) {
                byte[] b = testEventSerializer.serialize(list.get(i));
                TestClasses.TestEvent deserialize = testEventSerializer.deserialize(b);
            }
            System.out.println("Time per object: ~ " + (System.nanoTime() - start) / objCount + " ns.");
            Thread.yield();
        }

    }

    @Test
    public void testFactory() {
        SerializerUnsafeI<TestClasses.TestBoolean> serializerUnsafe = SerializerFactory.getSerializer(TestClasses.TestBoolean.class);
        SerializerUnsafeI<TestClasses.TestBoolean> serializerUnsafe2 = SerializerFactory.getSerializer(TestClasses.TestBoolean.class);
        assertEquals(serializerUnsafe, serializerUnsafe2);
    }
}