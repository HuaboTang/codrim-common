package com.codrim.common.utils.web.vo.dserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateDeSerializer extends StdDeserializer<Date> {
    private SimpleDateFormat formatter =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public StringToDateDeSerializer() {
        this(null);
    }

    public StringToDateDeSerializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context)
        throws IOException, JsonProcessingException {
        String date = jsonparser.getText();
        if (StringUtils.isBlank(date)) {
            return  null;
        }
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
