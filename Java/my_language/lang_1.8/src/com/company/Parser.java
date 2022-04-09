package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private Map<Pattern, String> regexMap = new LinkedHashMap<>();

    Parser(){
        String operator = "(OPPL|OPMIN) ";
        String value = "(VAR|DIGIT) ";
        String dataType = "(DTINT|DTDB)? ";
        //String exprVal = "(LPR (VAR|DIGIT)(( (OPPL|OPMIN) (VAR|DIGIT))+)? RPR)|((VAR|DIGIT)(( (OPPL|OPMIN) (VAR|DIGIT))+)?) ";
        String exprVal = "((VAR|DIGIT)(( (OPPL|OPMIN|OPDIV|OPMUL) (VAR|DIGIT))+)?) ";
        String expr = "((DTINT|DTDB) )?VAR OPASS " + exprVal + "SC ";
        //String expr = "(DTINT|DTDB) VAR OPASS (VAR|DIGIT) (OPPL|OPMIN) (VAR|DIGIT) SC\\s?";
        this.regexMap.put(Pattern.compile(expr), "expr");

        String compExprVal = "(VAR|DIGIT)(( (OPEQ|OPMORE|OPLESS|OPMOEQ|OPLSEQ|OPAND|OPOR) (VAR|DIGIT))+) ";
        String compExpr = "(DTBL )?VAR OPASS " + compExprVal + "SC ";
        this.regexMap.put(Pattern.compile(compExpr), "compExpr");


        String opWhile = "OPWH LPR "+compExprVal + "RPR LBR (((" + expr + ")|(" + compExpr + "))*)RBR ";
        this.regexMap.put(Pattern.compile(opWhile), "opWhile");

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
        System.out.println(content);
        List<String> list = new ArrayList<>();
        Matcher m;
        int err =0;
        while(content.length() != 0) {
            if(err==1){
                System.out.println("\u001B[33m"+"Syntax error !"+"\u001B[33m");
                break;
            }
            err=1;
            for (Pattern pattern : this.regexMap.keySet()) {
                m = pattern.matcher(content);
                if (m.find()) {
                    //System.out.println(m.group() + " " + m.start()); //убрать после отладки
                    if(m.start()==0){

                        list.add(regexMap.get(pattern));
                        content = content.substring(m.end()-m.start());
                        err=0;
                        break;
                    }

                }

            }
        }
        return list;
    }
}
