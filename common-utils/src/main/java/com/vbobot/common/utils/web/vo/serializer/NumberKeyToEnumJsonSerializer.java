package com.vbobot.common.utils.web.vo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vbobot.common.utils.enums.EnumUtils;
import com.vbobot.common.utils.enums.EnumWithKeyDesc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 数值型枚举key序列化为key-value
 * Created by liang.ma on 08/12/2016.
 */
public class NumberKeyToEnumJsonSerializer<T extends Enum<T> & EnumWithKeyDesc<Number>> extends JsonSerializer<Number> {
    private static final Logger logger = LoggerFactory.getLogger(NumberKeyToEnumJsonSerializer.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public void serialize(Number value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (value == null) {
            gen.writeString("");
            return;
        }

        Class clazz = gen.getCurrentValue().getClass();
        String filedName = gen.getOutputContext().getCurrentName();
        try {
            Field field = clazz.getDeclaredField(filedName);
            EnumTypeSpecify annotation = field.getAnnotation(EnumTypeSpecify.class);
            Class<T> enumClass = (Class<T>) annotation.using();

            final EnumWithKeyDesc tmp = EnumUtils.enumForKey(enumClass, value);
            final EnumJson enumForJson = new EnumJson(tmp.getKey(), tmp.getDesc());
            gen.writeObject(enumForJson);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            gen.writeString("Error");
        }
    }
}
