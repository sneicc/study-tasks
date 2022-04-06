package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private Map<Pattern, String> regexMap = new HashMap<Pattern, String>();

    Parser(){
        String operator = "(OPPL|OPMIN) ";
        String value = "(VAR|DIGIT) ";
        String dataType = "(DTINT|DTDB)? ";
        String expr = dataType +"VAR OPASS " + value + operator + value + "SC\\s?";
        //String expr = "(DTINT|DTDB) VAR OPASS (VAR|DIGIT) (OPPL|OPMIN) (VAR|DIGIT) SC\\s?";
        this.regexMap.put(Pattern.compile(expr), "expr");

    }
    private String genCont(List<Token> tokenList){
        String content = "";
        for (int i = 0; i < tokenList.size(); i++){
            content += tokenList.get(i).getType() + " ";
        }
        return content;
    }

    public List<String> checkOrder(List<Token> tokenList){
        String content = genCont(tokenList);
        List<String> list = new ArrayList<>();
        Matcher match;
        int err =0;
        while(content.length() != 0) {
            if(err==1){
                System.out.println("\u001B[33m" +"Syntax error !"+"\u001B[33m");
                break;
            }
            err=1;
            for (Pattern pattern : this.regexMap.keySet()) {
                match = pattern.matcher(content);
                if (match.find()) {
                    System.out.println(match.group() + " " + match.start());
                    if(match.start()==0){

                        list.add(regexMap.get(pattern));
                        content = content.substring(match.end()-match.start());
                        err=0;
                        break;
                    }

                }

            }
        }
        return list;
    }
}
