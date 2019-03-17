package Chapter21_RecursiveDescentParser;

import Chapter20_RegexTableLexer.Token;

import java.util.ArrayList;
import java.util.List;

public class TokenBuffer {
    private List<Token> tokens;
    private int position;

    public TokenBuffer(){
        tokens = new ArrayList<>();
        position = 0;
    }
    private TokenBuffer(Token[] t){
        tokens = new ArrayList<>();
        for (int i = 0; i < t.length; i++) {
            tokens.add(t[i]);
        }
        position = t.length;
    }
    public TokenBuffer(Token t){
        tokens.add(t);
        position = 1;
    }
    public void addToken(Token t){
        tokens.add(t);
        position = position + 1;
    }
    public int getCurrentPosition() {
        return this.position;
    }
    public Token nextToken() {
        return tokens.get(position);
    }
    public void popToken() {
        position = position + 1;
    }
    public void resetCurrentPosition(int p) {
        this.position = p;
    }

    public Token makePoppedTokenList() {
        Token t = tokens.get(position);
        position = position + 1;
        return t;
    }
}
