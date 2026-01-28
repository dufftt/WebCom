package com.duft.customer_service.Utils.MaperUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

public final class MapperUtils {

    private MapperUtils() {}

    public static <T> T map(Object source, Class<T> targetClass) {
        if (source == null) return null;
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
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
