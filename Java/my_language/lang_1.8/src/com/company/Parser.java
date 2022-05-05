package com.company;

import java.util.*;
import java.util.regex.Pattern;

public class Parser {
    private Map<Pattern, String> regexMap = new LinkedHashMap<>();
    private int index;
    public Map<String, Integer> intMap;

    Parser(){
        this.index = 0;
        this.intMap = new HashMap<>();
    }

    private void createIntVar(String name, int value) {
        intMap.put(name, value);
    }
    private int getIntVar(String name) {
        return intMap.get(name);
    }
    private boolean checkIntVar(String name) {
            return intMap.containsKey(name);
    }

    private void nextToken(List<Token> tokenList) {
        if (index + 1 < tokenList.size())
            index++;
        else{
            System.out.println("Index error");
            System.exit(0);
        }
    }

    private Token getNextToken(List<Token> tokenList) {
        if (++index < tokenList.size())
            return tokenList.get(index);
        else{
            System.out.println("Index error");
            System.exit(0);
            return new Token("ERR",null);
        }
    }

    private int getNumber(List<Token> tokenList) {
        return Integer.parseInt(tokenList.get(index).getContent());
    }

    public void programRec(List<Token> tokenList){
        switch (getNextToken(tokenList).getType()){
            case"DTINT":
            case"VAR":
            case"PRINT":
                program(tokenList);
                break;
            case"END":
                break;
            default:
                System.out.println("program error");
                System.exit(0);
        }
    }

    public void program(List<Token> tokenList){ //program -> initint|assign|(program END)
        switch(tokenList.get(index).getType()){
            case"DTINT":
                initInt(tokenList);
                programRec(tokenList);
                break;
            case"VAR":
                assign(tokenList);
                programRec(tokenList);
                break;
            case"PRINT":
                print(tokenList);
                programRec(tokenList);
                break;
            default:
                System.out.println("program error");
                System.exit(0);
        }
    }

    public void initInt(List<Token> tokenList){ //initint -> DTINT VAR OPASS expr SC
        int result = 0;
        switch (tokenList.get(index).getType()){
            case"DTINT":
                switch (getNextToken(tokenList).getType()){
                    case"VAR":
                        String content = tokenList.get(index).getContent();
                        if(checkIntVar(content)){
                            System.out.println("variable '" + content + "' already exists !" );
                            System.exit(0);
                        }
                        switch (getNextToken(tokenList).getType()){
                            case"OPASS":
                                switch (getNextToken(tokenList).getType()){
                                    case"LPR":
                                    case"VAR":
                                    case"DIGIT":
                                        result = expr(tokenList);
                                        createIntVar(content, result);
                                        switch (getNextToken(tokenList).getType()){
                                            case"SC":
                                                break;
                                            default:
                                                System.out.println("initInt error\n semicolon expected");
                                                System.exit(0);
                                        }
                                        break;
                                    default:
                                        System.out.println("initInt error\n expected an expression");
                                        System.exit(0);
                                }
                                break;
                            default:
                                System.out.println("initInt error\n expected assign operator");
                                System.exit(0);
                        }
                        break;
                    default:
                        System.out.println("initInt error\n expected name");
                        System.exit(0);
                }
                break;
            default:
                System.out.println("initInt error");
                System.exit(0);
        }
    }

    public void assign(List<Token> tokenList){ //assign -> VAR OPASS expr SC
        int result = 0;
        switch(tokenList.get(index).getType()){
            case"VAR":
                String content = tokenList.get(index).getContent();
                if(!checkIntVar(content)){
                    System.out.println("variable '" + content + "' does not exist !" );
                    System.exit(0);
                }
                switch (getNextToken(tokenList).getType()){
                    case"OPASS":
                        switch (getNextToken(tokenList).getType()){
                            case"LPR":
                            case"VAR":
                            case"DIGIT":
                                result = expr(tokenList);
                                createIntVar(content, result);
                                switch (getNextToken(tokenList).getType()){
                                    case"SC":
                                        break;
                                    default:
                                        System.out.println("assign error\n semicolon expected");
                                        System.exit(0);
                                }
                                break;
                            default:
                                System.out.println("assign error\n expected an expression");
                                System.exit(0);
                        }
                        break;
                    default:
                        System.out.println("assign error\n expected assign operator");
                        System.exit(0);
                }
                break;
            default:
                System.out.println("assign error");
                System.exit(0);
        }
    }

    public void print(List<Token> tokenList){ //print -> PRINT LPR expr RPR SC
        int result = 0;
        switch(tokenList.get(index).getType()){
            case"PRINT":
                switch (getNextToken(tokenList).getType()){
                    case"LPR":
                        switch (getNextToken(tokenList).getType()){
                            case"LPR":
                            case"VAR":
                            case"DIGIT":
                                result = expr(tokenList);
                                switch (getNextToken(tokenList).getType()){
                                    case"RPR":
                                        switch (getNextToken(tokenList).getType()){
                                            case"SC":
                                                break;
                                            default:
                                                System.out.println("print error");
                                                System.exit(0);
                                        }
                                        break;
                                    default:
                                        System.out.println("print error");
                                        System.exit(0);
                                }
                                break;
                            default:
                                System.out.println("print error");
                                System.exit(0);
                        }
                        break;
                    default:
                        System.out.println("print error");
                        System.exit(0);
                }
                break;
            default:
                System.out.println("print error");
                System.exit(0);
        }
        System.out.println(result);
    }


        public int expr(List<Token> tokenList){ //expr -> sum SC
        int result = 0;
        switch(tokenList.get(index).getType()){
            case"LPR":
            case"VAR":
            case"DIGIT":
                result = sum(tokenList);
                break;
            case"SC":
                break;
            default:
                System.out.println("expr error");
                System.exit(0);
        }
        return result;
    }

    public int sum(List<Token> tokenList){ //sum -> sub|sub OPPL sum
        int result = 0;
        switch(tokenList.get(index).getType()){
            case"LPR":
            case"VAR":
            case"DIGIT":
                result = sub(tokenList);
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
                        System.exit(0);
                }
                break;
            case"SC":
                break;
            default:
                System.out.println("sum error");
                System.exit(0);
        }
        return result;
    }

    public int sub(List<Token> tokenList){ //sub-> mul|mul OPMIN sub
        int result = 0;
        switch(tokenList.get(index).getType()){
            case"LPR":
            case"VAR":
            case"DIGIT":
                result = mul(tokenList);
                switch (getNextToken(tokenList).getType()){
                    case"OPMIN":
                        nextToken(tokenList);
                        result -= sub(tokenList);
                        break;
                    case"RPR":
                    case"OPPL":
                    case"SC":
                        index--;
                        break;
                    default:
                        System.out.println("sub error");
                        System.exit(0);
                }
                break;
            case"SC":
                break;
            default:
                System.out.println("sub error");
                System.exit(0);
        }
        return result;
    }

    public int mul(List<Token> tokenList){ //mul -> div|div OPMUL mul
        int result = 0;
        switch(tokenList.get(index).getType()){
            case"LPR":
            case"VAR":
            case"DIGIT":
                result = div(tokenList);
                switch (getNextToken(tokenList).getType()){
                    case"OPMUL":
                        nextToken(tokenList);
                        result *= mul(tokenList);
                        break;
                    case"RPR":
                    case"OPPL":
                    case"OPMIN":
                    case"SC":
                        index--;
                        break;
                    default:
                        System.out.println("mul error");
                        System.exit(0);
                }
                break;
            case"SC":
                break;
            default:
                System.out.println("mul error");
                System.exit(0);
        }
        return result;
    }

    public int div(List<Token> tokenList){ //div -> basic|basic OPDIV div
        int result = 0;
        switch(tokenList.get(index).getType()){
            case"LPR":
            case"VAR":
            case"DIGIT":
                result = basic(tokenList);
                switch (getNextToken(tokenList).getType()){
                    case"OPDIV":
                        nextToken(tokenList);
                        result /= div(tokenList);
                        break;
                    case"RPR":
                    case"OPPL":
                    case"OPMIN":
                    case"OPMUL":
                    case"SC":
                        index--;
                        break;
                    default:
                        System.out.println("error");
                        System.exit(0);
                }
                break;
            case"SC":
                break;
            default:
                System.out.println("error");
                System.exit(0);
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
                    System.exit(0);
                }
                break;
            case"VAR":
                try {
                    result = getIntVar(tokenList.get(index).getContent());
                } catch (NullPointerException e){
                    System.out.println("variable '"+ tokenList.get(index).getContent() +"' is not initialized !");
                    System.exit(0);
                }
                break;
            case"SC":
                break;
            default:
                System.out.println("basic error");
                System.exit(0);
        }
        return result;
    }
}