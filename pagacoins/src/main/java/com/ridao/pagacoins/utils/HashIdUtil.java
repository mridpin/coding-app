package com.ridao.pagacoins.utils;

import lombok.experimental.UtilityClass;
import org.hashids.Hashids;

@UtilityClass
public class HashIdUtil {

    private static final Hashids HASHIDS = new Hashids("the salt", 4);

    public static String encode(Long source) {
        return HASHIDS.encode(source);
    }

    public static Long decode(String source) {
        return HASHIDS.decode(source)[0];
    }
}