import com.sun.deploy.util.ArrayUtil;
import junit.framework.TestCase;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Arrays;

public class UnsafeMemoryTest extends TestCase {

    @Test
    public void testGetStringFieldValue() throws Exception {
        TestClasses.TestString testString = new TestClasses.TestString("myStr");
        byte[] bytes = UnsafeMemory.getStringFieldValue(testString, TestClasses.TestString.class.getDeclaredField("string"));
        System.out.println(new String(bytes, Charset.forName("UTF16")));

        TestSerializers.TestStringSerializer testStringSerializer = new TestSerializers.TestStringSerializer(TestClasses.TestString.class);

        System.out.println(new String(testStringSerializer.serialize(testString), Charset.forName("UTF16")));

        byte[] b = testStringSerializer.serialize(testString);

    }

    @Test
    public void testLongValue() throws Exception {
        TestClasses.TestLong testLong = new TestClasses.TestLong(100500L);
        System.out.println(Long.toBinaryString(100500L));
        TestSerializers.TestLongSerializer testLongSerializer = new TestSerializers.TestLongSerializer(TestClasses.TestLong.class);
        byte[] b = testLongSerializer.serialize(testLong);

        TestClasses.TestLong testLong1 = testLongSerializer.deserialize(b);
        System.out.println();
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