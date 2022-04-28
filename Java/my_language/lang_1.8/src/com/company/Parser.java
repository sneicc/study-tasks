package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private Map<Pattern, String> regexMap = new LinkedHashMap<>();
    private int index;

    Parser(){
        this.index = 0;
    }
    private String genCont(List<Token> tokenList){
        String content = "";
        for (int i = 0; i < tokenList.size(); i++){
            content += tokenList.get(i).getType() + " ";
        }
        return content;
    }

    private void nextToken(List<Token> tokenList) {
        if (index + 1 < tokenList.size())
            index++;
        else{
            System.out.println("Index error");
        }
    }

    private Token getNextToken(List<Token> tokenList) {
        if (++index < tokenList.size())
            return tokenList.get(index);
        else{
            System.out.println("Index error");
            return new Token("END",null);
        }
    }

    private int getNumber(List<Token> tokenList) {
        return Integer.parseInt(tokenList.get(index).getContent());
    }

    public int expr(List<Token> tokenList){ //expr -> sum SC
        int result = 0;
        switch(tokenList.get(index).getType()){
            case"LPR":
            case"DIGIT":
                result = sum(tokenList);
                switch(getNextToken(tokenList).getType()){
                    case"SC":
                        break;
                    default:
                        System.out.println("expr error");
                        break;
                }
                break;
            default:
                System.out.println("expr error");
                break;
        }
        return result;
    }

    public int sum(List<Token> tokenList){ //sum -> mul|mul OPPL sum
        int result = 0;
        switch(tokenList.get(index).getType()){
            case"LPR":
            case"DIGIT":
                result = mul(tokenList);
                switch (getNextToken(tokenList).getType()){
                    case"OPPL":
                        nextToken(tokenList);
                        result += sum(tokenList);
                        break;
                    case"RPR":
                    case"SC":
                        index--;
                        break;
                    default:
                        System.out.println("sum error");
                        break;
                }
                break;
            case"SC":
                break;
            default:
                System.out.println("sum error");
                break;
        }
        return result;
    }

    public int mul(List<Token> tokenList){ //mul -> basic|basic OPMUL mul
        int result = 0;
        switch(tokenList.get(index).getType()){
            case"LPR":
            case"DIGIT":
                result = basic(tokenList);
                switch (getNextToken(tokenList).getType()){
                    case"OPMUL":
                        nextToken(tokenList);
                        result *= mul(tokenList);
                        break;
                    case"RPR":
                    case"OPPL":
                    case"SC":
                        index--;
                        break;
                    default:
                        System.out.println("mul error");
                        break;
                }
                break;
            case"SC":
                break;
            default:
                System.out.println("mul error");
                break;
        }
        return result;
    }

    public int basic(List<Token> tokenList){ // basic -> DIGIT|LPR sum RPR
        int result = 0;
        switch(tokenList.get(index).getType()){
            case"DIGIT":
                result = getNumber(tokenList);
                break;
            case"LPR":
                nextToken(tokenList);
                result = sum(tokenList);
                if(getNextToken(tokenList).getType() != "RPR"){
                    System.out.println("basic error rpl");
                    return -1;
                }
                break;
            case"SC":
                break;
            default:
                System.out.println("basic error");
                break;
        }
        return result;
    }
}
