import org.jcoffee.serialization.SerializerFactory;
import org.jcoffee.serialization.SerializerUnsafe;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class UnsafeMemoryTest {

    private static Random random = new Random(System.currentTimeMillis());

    @Test
    public void testStringSerialization() throws Exception {
        String string = UUID.randomUUID().toString();
        TestClasses.TestString testString = new TestClasses.TestString(string);
        TestSerializers.TestStringSerializer testStringSerializer = new TestSerializers.TestStringSerializer(TestClasses.TestString.class);
        byte[] serialize = testStringSerializer.serialize(testString);
        TestClasses.TestString deserialize = testStringSerializer.deserialize(serialize);
        assertEquals(testString, deserialize);
    }

    @Test
    public void testLongSerialization() throws Exception {
        Long aLong = random.nextLong();
        TestClasses.TestLong testLong = new TestClasses.TestLong(aLong);
        TestSerializers.TestLongSerializer testLongSerializer = new TestSerializers.TestLongSerializer(TestClasses.TestLong.class);
        byte[] serialize = testLongSerializer.serialize(testLong);
        TestClasses.TestLong deserialize = testLongSerializer.deserialize(serialize);
        assertEquals(testLong, deserialize);
    }

    @Test
    public void testIntegerSerialization() throws Exception {
        Integer i = random.nextInt();
        TestClasses.TestInteger testInteger = new TestClasses.TestInteger(i);
        TestSerializers.TestIntegerSerializer testIntegerSerializer = new TestSerializers.TestIntegerSerializer(TestClasses.TestInteger.class);
        byte[] serialize = testIntegerSerializer.serialize(testInteger);
        TestClasses.TestInteger deserialize = testIntegerSerializer.deserialize(serialize);
        assertEquals(testInteger, deserialize);
    }

    @Test
    public void testLongPrimSerialization() throws Exception {
        long aLong = random.nextLong();
        TestClasses.TestLongPrim testLongPrim = new TestClasses.TestLongPrim(aLong);
        TestSerializers.TestLongPrimSerializer testLongPrimSerializer = new TestSerializers.TestLongPrimSerializer(TestClasses.TestLongPrim.class);
        byte[] serialize = testLongPrimSerializer.serialize(testLongPrim);
        TestClasses.TestLongPrim deserialize = testLongPrimSerializer.deserialize(serialize);
        assertEquals(testLongPrim, deserialize);
    }

    @Test
    public void testIntPrimSerialization() throws Exception {
        int i = random.nextInt();
        TestClasses.TestIntPrim testIntPrim = new TestClasses.TestIntPrim(i);
        TestSerializers.TestIntegerPrimSerializer testIntegerPrimSerializer = new TestSerializers.TestIntegerPrimSerializer(TestClasses.TestIntPrim.class);
        byte[] serialize = testIntegerPrimSerializer.serialize(testIntPrim);
        TestClasses.TestIntPrim deserialize = testIntegerPrimSerializer.deserialize(serialize);
        assertEquals(testIntPrim, deserialize);
    }

    @Test
    public void testBooleanPrimSerialization() throws Exception {
        boolean b = random.nextBoolean();
        TestClasses.TestBooleanPrim testBooleanPrim = new TestClasses.TestBooleanPrim(b);
        TestSerializers.TestBooleanPrimSerializer testBooleanPrimSerializer = new TestSerializers.TestBooleanPrimSerializer(TestClasses.TestBooleanPrim.class);
        byte[] serialize = testBooleanPrimSerializer.serialize(testBooleanPrim);
        TestClasses.TestBooleanPrim deserialize = testBooleanPrimSerializer.deserialize(serialize);
        assertEquals(testBooleanPrim, deserialize);
    }

    @Test
    public void testBooleanSerialization() throws Exception {
        Boolean b = random.nextBoolean();
        TestClasses.TestBoolean testBoolean = new TestClasses.TestBoolean(b);
        TestSerializers.TestBooleanSerializer testBooleanSerializer = new TestSerializers.TestBooleanSerializer(TestClasses.TestBoolean.class);
        byte[] serialize = testBooleanSerializer.serialize(testBoolean);
        TestClasses.TestBoolean deserialize = testBooleanSerializer.deserialize(serialize);
        assertEquals(testBoolean, deserialize);
    }

    @Test
    public void testDoublePrimSerialization() throws Exception {
        double d = random.nextDouble();
        TestClasses.TestDoublePrim testDoublePrim = new TestClasses.TestDoublePrim(d);
        TestSerializers.TestDoublePrimSerializer testDoublePrimSerializer = new TestSerializers.TestDoublePrimSerializer(TestClasses.TestDoublePrim.class);
        byte[] serialize = testDoublePrimSerializer.serialize(testDoublePrim);
        TestClasses.TestDoublePrim deserialize = testDoublePrimSerializer.deserialize(serialize);
        assertEquals(testDoublePrim, deserialize);
    }

    @Test
    public void testDoubleSerialization() throws Exception {
        Double d = random.nextDouble();
        TestClasses.TestDouble testDouble = new TestClasses.TestDouble(d);
        TestSerializers.TestDoubleSerializer testDoubleSerializer = new TestSerializers.TestDoubleSerializer(TestClasses.TestDouble.class);
        byte[] serialize = testDoubleSerializer.serialize(testDouble);
        TestClasses.TestDouble deserialize = testDoubleSerializer.deserialize(serialize);
        assertEquals(testDouble, deserialize);
    }

    @Test
    public void testFloatPrimSerialization() throws Exception {
        float f = random.nextFloat();
        TestClasses.TestFloatPrim testFloatPrim = new TestClasses.TestFloatPrim(f);
        TestSerializers.TestFloatPrimSerializer testFloatPrimSerializer = new TestSerializers.TestFloatPrimSerializer(TestClasses.TestFloatPrim.class);
        byte[] serialize = testFloatPrimSerializer.serialize(testFloatPrim);
        TestClasses.TestFloatPrim deserialize = testFloatPrimSerializer.deserialize(serialize);
        assertEquals(testFloatPrim, deserialize);
    }

    @Test
    public void testFloatSerialization() throws Exception {
        Float f = random.nextFloat();
        TestClasses.TestFloat testFloat = new TestClasses.TestFloat(f);
        TestSerializers.TestFloatSerializer testFloatSerializer = new TestSerializers.TestFloatSerializer(TestClasses.TestFloat.class);
        byte[] serialize = testFloatSerializer.serialize(testFloat);
        TestClasses.TestFloat deserialize = testFloatSerializer.deserialize(serialize);
        assertEquals(testFloat, deserialize);
    }

    @Test
    public void testCharArraySerialization() throws Exception {
        char[] chars = UUID.randomUUID().toString().toCharArray();
        TestClasses.TestCharArray testCharArray = new TestClasses.TestCharArray(chars);
        TestSerializers.TestCharArraySerializer testCharArraySerializer = new TestSerializers.TestCharArraySerializer(TestClasses.TestCharArray.class);
        byte[] serialize = testCharArraySerializer.serialize(testCharArray);
        TestClasses.TestCharArray deserialize = testCharArraySerializer.deserialize(serialize);
        assertEquals(testCharArray, deserialize);
    }

    @Test
    public void testLongArraySerialization() throws Exception {
        long[] longs = new long[] {random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(),
                random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong()};
        TestClasses.TestLongArray testLongArray = new TestClasses.TestLongArray(longs);
        TestSerializers.TestLongArraySerializer testLongArraySerializer = new TestSerializers.TestLongArraySerializer(TestClasses.TestLongArray.class);
        byte[] serialize = testLongArraySerializer.serialize(testLongArray);
        TestClasses.TestLongArray deserialize = testLongArraySerializer.deserialize(serialize);
        assertEquals(testLongArray, deserialize);
    }

    @Test
    public void testDoubleArraySerialization() throws Exception {
        double[] doubles = new double[] {random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(),
                random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()};
        TestClasses.TestDoubleArray testDoubleArray = new TestClasses.TestDoubleArray(doubles);
        TestSerializers.TestDoubleArraySerializer testDoubleArraySerializer = new TestSerializers.TestDoubleArraySerializer(TestClasses.TestDoubleArray.class);
        byte[] serialize = testDoubleArraySerializer.serialize(testDoubleArray);
        TestClasses.TestDoubleArray deserialize = testDoubleArraySerializer.deserialize(serialize);
        assertEquals(testDoubleArray, deserialize);
    }

    @Test
    public void testUUIDSerialization() throws Exception {
        UUID uuid = UUID.randomUUID();
        TestClasses.TestUUID testUUID = new TestClasses.TestUUID(uuid);
        TestSerializers.TestUUIDSerializer testUUIDSerializer = new TestSerializers.TestUUIDSerializer(TestClasses.TestUUID.class);
        byte[] serialize = testUUIDSerializer.serialize(testUUID);
        TestClasses.TestUUID deserialize = testUUIDSerializer.deserialize(serialize);
        assertEquals(testUUID, deserialize);
    }

    @Test
    public void testComplexSerialization() throws Exception {
        TestClasses.TestComplex testComplex = new TestClasses.TestComplex(
                random.nextBoolean(),
                random.nextInt(),
                random.nextLong(),
                random.nextFloat(),
                random.nextDouble(),
                UUID.randomUUID().toString().toCharArray(),
                new long[] {random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(),
                        random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong()},
                new double[] {random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(),
                        random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()},
                random.nextBoolean(),
                random.nextInt(),
                random.nextLong(),
                random.nextFloat(),
                random.nextDouble(),
                UUID.randomUUID().toString(),
                UUID.randomUUID());
        TestSerializers.TestComplexSerializer testComplexSerializer = new TestSerializers.TestComplexSerializer(TestClasses.TestComplex.class);
        byte[] serialize = testComplexSerializer.serialize(testComplex);
        TestClasses.TestComplex deserialize = testComplexSerializer.deserialize(serialize);
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
        TestSerializers.TestEventSerializer testEventSerializer = new TestSerializers.TestEventSerializer(TestClasses.TestEvent.class);
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
                    random.nextBoolean(),
                    random.nextInt(),
                    random.nextLong(),
                    random.nextFloat(),
                    random.nextDouble(),
                    UUID.randomUUID().toString().toCharArray(),
                    new long[] {random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(),
                            random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong()},
                    new double[] {random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(),
                            random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()},
                    random.nextBoolean(),
                    random.nextInt(),
                    random.nextLong(),
                    random.nextFloat(),
                    random.nextDouble(),
                    UUID.randomUUID().toString(),
                    UUID.randomUUID());
            list.add(testComplex);
        }

        System.out.println("Done.");

        TestSerializers.TestComplexSerializer testComplexSerializer = new TestSerializers.TestComplexSerializer(TestClasses.TestComplex.class);

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
        TestSerializers.TestEventSerializer testEventSerializer = new TestSerializers.TestEventSerializer(TestClasses.TestEvent.class);

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
        SerializerUnsafe<TestClasses.TestBoolean> serializerUnsafe = SerializerFactory.getSerializer(TestClasses.TestBoolean.class);
        SerializerUnsafe<TestClasses.TestBoolean> serializerUnsafe2 = SerializerFactory.getSerializer(TestClasses.TestBoolean.class);
        assertEquals(serializerUnsafe, serializerUnsafe2);
    }
}