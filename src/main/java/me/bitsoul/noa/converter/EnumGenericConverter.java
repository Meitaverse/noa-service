package me.bitsoul.noa.converter;

import me.bitsoul.noa.enums.ConverterEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liujinhua
 */
@Component
public class EnumGenericConverter implements GenericConverter {
    private static final Logger log = LoggerFactory.getLogger(EnumGenericConverter.class);
    private static final String GET_VALUE_METHOD_NAME = "getValue";
    private static final String PARSE_METHOD_NAME = "parse";

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> pairs = new HashSet<>();

        pairs.add(new ConvertiblePair(ConverterEnum.class, Integer.class));
        pairs.add(new ConvertiblePair(ConverterEnum.class, String.class));
        pairs.add(new ConvertiblePair(Integer.class, ConverterEnum.class));
        pairs.add(new ConvertiblePair(String.class, ConverterEnum.class));
        return pairs;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        try {
            if (isImplementsConverterEnum(sourceType.getType())) {
                return sourceType.getType().getMethod(GET_VALUE_METHOD_NAME).invoke(source);
            } else if (isImplementsConverterEnum(targetType.getType())) {
                return targetType.getType().getMethod(PARSE_METHOD_NAME, sourceType.getType()).invoke(null, source);
            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException();
        }
    }

    private boolean isImplementsConverterEnum(Class type) {
        for (Class cls : type.getInterfaces()) {
            if (cls == ConverterEnum.class) {
                return true;
            }
        }
        return false;
    }

}
