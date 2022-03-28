package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static void main(String[] args) throws IOException {
        String content = Files.lines(Paths.get("Code.txt")).reduce("",String::concat);
        System.out.println(content);
        defType(content).stream()
                .forEach(l -> System.out.println(l.content+" "+l.type));

    }
    public static List<Token> defType(String content){
        TokenMatch tokenMatch= new TokenMatch();
        List<Token> list = new ArrayList<>();
        int err =0;
        Matcher match;
        while(content.length()!=0) {
            if(err==1){
                System.out.println("\u001B[31m" +"Syntax error !"+"\u001B[0m");
                break;
            }
            err=1;
            for (Pattern pattern : tokenMatch.matchMap.keySet()) {
                match = pattern.matcher(content);
                if (match.find()) {
                    if(match.start()==0){
                       list.add(new Token(tokenMatch.matchMap.get(pattern),match.group()));
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
