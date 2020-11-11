package com.phamvanviet.demo.domain.utils;

public class CacheKey {
    public static String genKey(String token){
        return "cz_" + token;
    }
}
