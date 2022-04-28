package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) throws IOException {
        String content = Files.lines(Paths.get("Code.txt")).reduce("",String::concat);
        System.out.println(content);
        Lexer lexer = new Lexer();
        lexer.defType(content).stream()
                .forEach(l -> System.out.println(l.content+" "+l.type));
        Parser parser = new Parser();
        System.out.println(parser.expr(lexer.defType(content)));
    }

}
