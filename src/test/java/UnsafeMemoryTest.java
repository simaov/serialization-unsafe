import com.sun.org.apache.xpath.internal.SourceTree;
import junit.framework.TestCase;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.DataOutputStream;
import java.util.*;

public class UnsafeMemoryTest extends TestCase {

    @Test
    public void testGetStringFieldValue() throws Exception {
        TestClasses.TestString testString = new TestClasses.TestString("myStr");
        TestSerializers.TestStringSerializer testStringSerializer = new TestSerializers.TestStringSerializer(TestClasses.TestString.class);
        byte[] serialize = testStringSerializer.serialize(testString);
        Assert.assertEquals(testString, testStringSerializer.deserialize(serialize));
    }

    @Test
    public void testLongValue() throws Exception {
        TestClasses.TestLong testLong = new TestClasses.TestLong(100500L);
        TestSerializers.TestLongSerializer testLongSerializer = new TestSerializers.TestLongSerializer(TestClasses.TestLong.class);
        byte[] b = testLongSerializer.serialize(testLong);
        assertEquals(testLong, testLongSerializer.deserialize(b));
    }

    @Test
    public void testIntegerValue() throws Exception {
        TestClasses.TestInteger testInteger = new TestClasses.TestInteger(100);
        TestSerializers.TestIntegerSerializer testIntegerSerializer = new TestSerializers.TestIntegerSerializer(TestClasses.TestInteger.class);
        byte[] b = testIntegerSerializer.serialize(testInteger);

        TestClasses.TestInteger testInteger1 = testIntegerSerializer.deserialize(b);
        System.out.println();
    }

    @Test
    public void testLongIntegerValue() throws Exception {
        TestClasses.TestLongInteger testLongInteger = new TestClasses.TestLongInteger(100500L, 100);
        TestSerializers.TestLongIntegerSerializer testLongIntegerSerializer = new TestSerializers.TestLongIntegerSerializer(TestClasses.TestLongInteger.class);
        byte[] b = testLongIntegerSerializer.serialize(testLongInteger);

        TestClasses.TestLongInteger testInteger1 = testLongIntegerSerializer.deserialize(b);
        System.out.println();
    }

    @Test
    public void testCharArrayValue() throws Exception {
        TestClasses.TestCharArray testCharArray = new TestClasses.TestCharArray("sss".toCharArray());
        TestSerializers.TestCharArraySerializer testCharArraySerializer = new TestSerializers.TestCharArraySerializer(TestClasses.TestCharArray.class);
        testCharArraySerializer.serialize(testCharArray);
    }

    @Test
    public void testComplexObject() throws Exception {
        List<TestClasses.TestLongIntegerString> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            TestClasses.TestLongIntegerString testLongIntegerString = new TestClasses.TestLongIntegerString(random.nextLong(), random.nextInt() , UUID.randomUUID().toString());
            list.add(testLongIntegerString);
        }
        TestClasses.TestLongIntegerString testLongIntegerString = new TestClasses.TestLongIntegerString(12345L, 9876, "sss78yhgw46g34d");
        TestSerializers.TestLongIntegerStringSerializer testCharArraySerializer = new TestSerializers.TestLongIntegerStringSerializer(TestClasses.TestLongIntegerString.class);
        byte[] b = testCharArraySerializer.serialize(testLongIntegerString);

        testLongIntegerString = testCharArraySerializer.deserialize(b);
        System.out.println();
    }

    @Test
    public void testPerf() throws Exception {
        List<TestClasses.TestLongIntegerString> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            TestClasses.TestLongIntegerString testLongIntegerString = new TestClasses.TestLongIntegerString(random.nextLong(), random.nextInt() , UUID.randomUUID().toString());
            list.add(testLongIntegerString);
        }

        TestSerializers.TestLongIntegerStringSerializer testCharArraySerializer = new TestSerializers.TestLongIntegerStringSerializer(TestClasses.TestLongIntegerString.class);

        long start = System.nanoTime();
        for (int i = 0; i < list.size(); i++) {
            byte[] b = testCharArraySerializer.serialize(list.get(i));
        }

        System.out.println("Time: " + (System.nanoTime() - start) / 1000000 + " ns.");

//        testLongIntegerString = testCharArraySerializer.deserialize(b);
        System.out.println();
    }



    @Test
    public void test() throws Exception {
        long l = 255L;
        long mask = 0xFFL;
        byte[] longValue = new byte[8];

        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                longValue[i] = (byte) (l & mask);
                continue;
            }
            mask <<= 8;
            longValue[i] = (byte) ((l & mask) >> i * 8);
        }

        ArrayUtils.reverse(longValue);
        System.out.println(Arrays.toString(longValue));
    }
}