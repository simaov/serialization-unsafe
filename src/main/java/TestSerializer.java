import java.util.Random;
import java.util.UUID;


public class TestSerializer {
    /*private static Random r = new Random();

    public static void main(String[] args) throws Exception {
        int numOfObjects = 1000;
        int numOfExp = 100;
        double[] unsafeVSCurrent = new double[numOfExp];
        double[] unsafePrimVSCurrent = new double[numOfExp];
        double[] unsafePrimVSUnsafe = new double[numOfExp];

        ClickEventDSP[] clickEventDSPs = new ClickEventDSP[numOfObjects];
        for (int i = 0; i < numOfObjects; i++) {
            ClickEventDSP clickEventDSP = new ClickEventDSP();
            clickEventDSP.setId(r.nextLong());
            clickEventDSP.setCreativeId(r.nextLong());
            clickEventDSP.setEventDay(r.nextInt());
            clickEventDSP.setEventTimestamp(r.nextLong());
            clickEventDSP.setImpressionId("impressionIdFieldValue");
            clickEventDSP.setPrice(r.nextLong());
            clickEventDSP.setSspId(r.nextLong());
            clickEventDSP.setUserId("userIdFieldValue");
            clickEventDSP.setWideId(UUID.randomUUID());
            clickEventDSPs[i] = clickEventDSP;
        }

        ClickEventDSPPrim[] clickEventDSPPrims = new ClickEventDSPPrim[numOfObjects];

        for (int i = 0; i < numOfObjects; i++) {
            ClickEventDSPPrim clickEventDSP = new ClickEventDSPPrim();
            clickEventDSP.setId(r.nextLong());
            clickEventDSP.setCreativeId(r.nextLong());
            clickEventDSP.setEventDay(r.nextInt());
            clickEventDSP.setEventTimestamp(r.nextLong());
            clickEventDSP.setImpressionId("impressionIdFieldValue".toCharArray());
            clickEventDSP.setPrice(r.nextLong());
            clickEventDSP.setSspId(r.nextLong());
            clickEventDSP.setUserId("userIdFieldValue".toCharArray());
            clickEventDSP.setWideId(UUID.randomUUID());
            clickEventDSPPrims[i] = clickEventDSP;
        }

        for (int j = 0; j < numOfExp; j++) {
            ClickEventDspSerializer clickEventDspSerializer = new ClickEventDspSerializer();
            long k = System.nanoTime();
            for (int i = 0; i < numOfObjects; i++) {
                clickEventDspSerializer.toBytes(clickEventDSPs[i]);
            }
            long allCurrent = System.nanoTime() - k;

            ClickEventDspSerializerUnsafe clickEventDspSerializerUnsafe = new ClickEventDspSerializerUnsafe();
            long l = System.nanoTime();
            for (int i = 0; i < numOfObjects; i++) {
                clickEventDspSerializerUnsafe.toBytes(clickEventDSPs[i]);
            }
            long allUnsafe = System.nanoTime() - l;

            ClickEventDspSerializerPrimUnsafe clickEventDspSerializerPrimUnsafe = new ClickEventDspSerializerPrimUnsafe();
            long m = System.nanoTime();
            for (int i = 0; i < numOfObjects; i++) {
                clickEventDspSerializerPrimUnsafe.toBytes(clickEventDSPPrims[i]);
            }
            long allPrimUnsafe = System.nanoTime() - m;

            unsafePrimVSCurrent[j] = allCurrent * 1d / allPrimUnsafe;
            unsafePrimVSUnsafe[j] = allUnsafe * 1d / allPrimUnsafe;
            unsafeVSCurrent[j] = allCurrent * 1d / allUnsafe;
        }
        System.out.println("unsafePrimVSCurrent: " + DoubleMath.mean(unsafePrimVSCurrent));
        System.out.println("unsafePrimVSUnsafe: " + DoubleMath.mean(unsafePrimVSUnsafe));
        System.out.println("unsafeVSCurrent: " + DoubleMath.mean(unsafeVSCurrent));

    }*/

    static class ClickEventDSPPrim {
        private long id;
        private long creativeId;
        private int eventDay;
        private long eventTimestamp;
        private char[] impressionId;
        private long price;
        private long sspId;
        private char[] userId;
        private UUID wideId;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getCreativeId() {
            return creativeId;
        }

        public void setCreativeId(long creativeId) {
            this.creativeId = creativeId;
        }

        public int getEventDay() {
            return eventDay;
        }

        public void setEventDay(int eventDay) {
            this.eventDay = eventDay;
        }

        public long getEventTimestamp() {
            return eventTimestamp;
        }

        public void setEventTimestamp(long eventTimestamp) {
            this.eventTimestamp = eventTimestamp;
        }

        public char[] getImpressionId() {
            return impressionId;
        }

        public void setImpressionId(char[] impressionId) {
            this.impressionId = impressionId;
        }

        public long getPrice() {
            return price;
        }

        public void setPrice(long price) {
            this.price = price;
        }

        public long getSspId() {
            return sspId;
        }

        public void setSspId(long sspId) {
            this.sspId = sspId;
        }

        public char[] getUserId() {
            return userId;
        }

        public void setUserId(char[] userId) {
            this.userId = userId;
        }

        public UUID getWideId() {
            return wideId;
        }

        public void setWideId(UUID wideId) {
            this.wideId = wideId;
        }
    }

    static class ClickEventDspSerializerPrimUnsafe {

       /* public byte[] toBytes(ClickEventDSPPrim obj) {
            byte[] buffer = null;
            try {
                long id = getUnsafe().getLong(obj, idFieldOffsetPrim);
                long creativeId = getUnsafe().getLong(obj, creativeIdFieldOffsetPrim);
                long eventTimestamp = getUnsafe().getLong(obj, eventTimestampFieldOffsetPrim);
                long price = getUnsafe().getLong(obj, priceFieldOffsetPrim);
                long sspId = getUnsafe().getLong(obj, sspIdFieldOffsetPrim);

                char[] impressionId = getCharArrayValue(obj, obj.getImpressionId().length);
                char[] userId = getCharArrayValue(obj, obj.getUserId().length);

                byte[] wideId = getUUIDValue(getFieldObject(obj, wideIdFieldOffsetPrim));
                int eventDay = getUnsafe().getInt(obj, eventDayFieldOffsetPrim);

                int index = 0;
                buffer = new byte[5 * 8 *//* 5 longs*//*
                        + 16 *//* 1 UUID *//*
                        + 4  *//* 1 int *//*
                        + impressionId.length * 2
                        + userId.length * 2
                        + 4 *//* impressionId.length *//*
                        + 4 *//* userId.length *//*];

                for (int i = 0; i < 8; ++i) {
                    buffer[index++] = (byte) (id >> ((7 - i) * 8));
                }
                for (int i = 0; i < 8; ++i) {
                    buffer[index++] = (byte) (creativeId >> ((7 - i) * 8));
                }
                for (int i = 0; i < 8; ++i) {
                    buffer[index++] = (byte) (eventTimestamp >> ((7 - i) * 8));
                }
                for (int i = 0; i < 8; ++i) {
                    buffer[index++] = (byte) (price >> ((7 - i) * 8));
                }
                for (int i = 0; i < 8; ++i) {
                    buffer[index++] = (byte) (sspId >> ((7 - i) * 8));
                }
                for (int i = 0; i < 4; ++i) {
                    buffer[index++] = (byte) (impressionId.length >> ((3 - i) * 8));
                }
                for (int i = 0; i < impressionId.length; i++) {
                    buffer[index++] = (byte) (impressionId[i]);
                    buffer[index++] = (byte) (impressionId[i] >> 8);
                }
                for (int i = 0; i < wideId.length; i++) {
                    buffer[index++] = wideId[i];
                }
                for (int i = 0; i < 4; ++i) {
                    buffer[index++] = (byte) (userId.length >> ((3 - i) * 8));
                }
                for (int i = 0; i < userId.length; i++) {
                    buffer[index++] = (byte) (userId[i]);
                    buffer[index++] = (byte) (userId[i] >> 8);
                }

                for (int i = 0; i < 4; ++i) {
                    buffer[index++] = (byte) (eventDay >> ((3 - i) * 8));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return buffer;
        }*/
    }

    static class ClickEventDspSerializerUnsafe {

        /*public byte[] toBytes(ClickEventDSPI obj) {
            byte[] buffer = null;
            try {
                long id = getLongValueFromLongObject(getFieldObject(obj, idFieldOffset));
                long creativeId = getLongValueFromLongObject(getFieldObject(obj, creativeIdFieldOffset));
                long eventTimestamp = getLongValueFromLongObject(getFieldObject(obj, eventTimestampFieldOffset));
                long price = getLongValueFromLongObject(getFieldObject(obj, priceFieldOffset));
                long sspId = getLongValueFromLongObject(getFieldObject(obj, sspIdFieldOffset));
                char[] impressionId = getStringValue(getFieldObject(obj, impressionIdFieldOffset));
                char[] userId = getStringValue(getFieldObject(obj, userIdFieldOffset));
                byte[] wideId = getUUIDValue(getFieldObject(obj, wideIdFieldOffset));
                int eventDay = getIntValueFromIntObject(getFieldObject(obj, eventDayFieldOffset));

                int index = 0;
                buffer = new byte[5 * 8 *//* 5 longs*//*
                        + 16 *//* 1 UUID *//*
                        + 4  *//* 1 int *//*
                        + impressionId.length * 2
                        + userId.length * 2
                        + 4 *//* impressionId.length *//*
                        + 4 *//* userId.length *//*];

                for (int i = 0; i < 8; ++i) {
                    buffer[index++] = (byte) (id >> ((7 - i) * 8));
                }
                for (int i = 0; i < 8; ++i) {
                    buffer[index++] = (byte) (creativeId >> ((7 - i) * 8));
                }
                for (int i = 0; i < 8; ++i) {
                    buffer[index++] = (byte) (eventTimestamp >> ((7 - i) * 8));
                }
                for (int i = 0; i < 8; ++i) {
                    buffer[index++] = (byte) (price >> ((7 - i) * 8));
                }
                for (int i = 0; i < 8; ++i) {
                    buffer[index++] = (byte) (sspId >> ((7 - i) * 8));
                }
                for (int i = 0; i < 4; ++i) {
                    buffer[index++] = (byte) (impressionId.length >> ((3 - i) * 8));
                }
                for (int i = 0; i < impressionId.length; i++) {
                    buffer[index++] = (byte) (impressionId[i]);
                    buffer[index++] = (byte) (impressionId[i] >> 8);
                }
                for (int i = 0; i < wideId.length; i++) {
                    buffer[index++] = wideId[i];
                }
                for (int i = 0; i < 4; ++i) {
                    buffer[index++] = (byte) (userId.length >> ((3 - i) * 8));
                }
                for (int i = 0; i < userId.length; i++) {
                    buffer[index++] = (byte) (userId[i]);
                    buffer[index++] = (byte) (userId[i] >> 8);
                }

                for (int i = 0; i < 4; ++i) {
                    buffer[index++] = (byte) (eventDay >> ((3 - i) * 8));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return buffer;
        }*/
    }

}
