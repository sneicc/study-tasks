package com.company;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class TokenMatch {
    public  Map<Pattern, String> matchMap = new LinkedHashMap<>();
    TokenMatch(){
        TokenType TT = new TokenType();
        for (String key : TT.regexMap.keySet()){
            matchMap.put(Pattern.compile(key), TT.regexMap.get(key));
        }
    }
}
