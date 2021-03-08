package cz.educanet.webik2;


import java.util.UUID;

public class Token {

    String body;


    public Token() {
        this.body = UUID.randomUUID().toString();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
