package com.company;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TokenType {
    public Map<String, String> regexMap = new LinkedHashMap<String, String>();

    public TokenType() {
        regexMap.put("\\s*_int_\\s*","DTINT");
        regexMap.put("\\s*_double_\\s*","DTDB");
        regexMap.put("\\s*_bool_\\s*","DTBL");
        regexMap.put("\\s*_while_\\s*","OPWH");
        regexMap.put("\\s*_end_\\s*","END");
        regexMap.put("\\s*==\\s*","OPEQ");
        regexMap.put("\\s*=\\s*","OPASS");
        regexMap.put("\\s*>=\\s*","OPMOEQ");
        regexMap.put("\\s*<=\\s*","OPLSEQ");
        regexMap.put("\\s*>\\s*","OPMORE");
        regexMap.put("\\s*<\\s*","OPLESS");
        regexMap.put("\\s*&&\\s*","OPAND");
        regexMap.put("\\s*\\|\\|\\s*","OPOR");
        regexMap.put("\\s*\\(\\s*","LPR");
        regexMap.put("\\s*\\)\\s*","RPR");
        regexMap.put("\\s*\\{\\s*","LBR");
        regexMap.put("\\s*\\}\\s*","RBR");
        regexMap.put("\\s*\\+\\s*","OPPL");
        regexMap.put("\\s*-\\s*","OPMIN");
        regexMap.put("\\s*/\\s*","OPDIV");
        regexMap.put("\\s*\\*\\s*","OPMUL");
        regexMap.put("\\s*[A-Za-z]+[0-9]*\\s*","VAR");
        regexMap.put("\\s*[0-9]+(\\.[0-9]+)?\\s*","DIGIT");
        regexMap.put("\\s*;\\s*","SC");
    }

}
