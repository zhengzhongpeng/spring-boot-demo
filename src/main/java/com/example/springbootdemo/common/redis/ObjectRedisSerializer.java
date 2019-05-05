package com.example.springbootdemo.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class ObjectRedisSerializer implements RedisSerializer<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectRedisSerializer.class);

    //序列化和反序列化转换类
    private Converter<Object,byte[]> serializer = new SerializingConverter();
    private Converter<byte[],Object> deserializer = new DeserializingConverter();

    //定义转换字节空数组
    private static final byte[] EMPTY_ARRAY = new byte[0];

    /**
     * 序列化
     * @param o
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        byte[] byteArray = null;
        if (o == null){
            byteArray = EMPTY_ARRAY;
            LOGGER.info("Redis待序列化对象为空");
        }else {
            try {
                byteArray = serializer.convert(o);
            }catch (Exception e){
                byteArray = EMPTY_ARRAY;
                LOGGER.info("Redis序列化对象失败，异常:{}",e);
            }
        }
        return byteArray;
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        Object o = null;
        if (bytes == null || bytes.length == 0){
            LOGGER.info("Redis待反序列化对象为空");
        }else {
            try {
                o = deserializer.convert(bytes);
            }catch (Exception e){
                LOGGER.info("Redis反序列化对象失败,异常:{}",e
                );
            }
        }
        return o;
    }
}
