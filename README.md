# serialization-unsafe

Serializer can serialize plain java objects.

Supported types:
* all primitive types and their wrappers
* all primitive type arrays
* string
* UUID

For example we have a class we want to serialize
```
public class TestObj {
  private Integer integer;
  private long l;
  private someString s;
  
  ..... getters and setters
  
}
```

Get serializer for this class:
```
SerializerUnsafeI<TestObj> serializerUnsafe = SerializerFactory.getSerializer(TestObj.class);
```

Use it to serialize/deserialize objects:
```
byte[] b = serializerUnsafe.serialize(testObj);
TestObj deserialized = serializerUnsafe.deserialize(b);
```

# WARNING!!!
TEST IT BEFORE USE
