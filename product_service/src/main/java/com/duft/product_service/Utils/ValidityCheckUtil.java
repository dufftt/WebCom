package com.duft.product_service.Utils;

public class ValidityCheckUtil {

    public static boolean isNotNullOrEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean isPositive(Integer value) {
        return value != null && value > 0;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }
    
    public static boolean isValidWebDTO(Object obj, Class targetClass){
            //check obj have all valid non null values in all fields
            return true;
    }

}
