package com.company;

import java.util.HashMap;
import java.util.Map;

public class TokenType {
    public Map<String, String> regexMap = new HashMap<String, String>();

    public TokenType() {
        regexMap.put("\\s*int\\s+","DTINT");
        regexMap.put("\\s*double\\s+","DTDB");
        regexMap.put("\\s*=\\s*","OPASS");
        regexMap.put("\\s*\\+\\s*","OPPL");
        regexMap.put("\\s*-\\s*","OPMIN");
        regexMap.put("\\s*[A-Za-z]+[0-9]*\\s*","VAR");
        regexMap.put("\\s*[0-9]+(.[0-9]+)?+\\s*","DIGIT");
        regexMap.put("\\s*;\\s*","SC");
    }

}
