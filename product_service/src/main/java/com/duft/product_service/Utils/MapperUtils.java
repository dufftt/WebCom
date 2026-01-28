package com.duft.product_service.Utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public final class MapperUtils {

    private MapperUtils() {}
    static final Logger logger = LoggerFactory.getLogger(MapperUtils.class);
    
    public static <T> T map(Object source, Class<T> targetClass) {
        if (source == null) return null;
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            logger.info("Mapper: "+target);
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to map " + source.getClass() + " to " + targetClass, ex);
        }
    }

    public static <S, T> List<T> mapList(Collection<S> source, Class<T> targetClass) {
        if (source == null) return null;
        return source.stream().map(s -> map(s, targetClass)).collect(Collectors.toList());
    }

}
