package com.company;

public class Token {
    public String type;
    public String content;
    Token(String type, String content){
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}

