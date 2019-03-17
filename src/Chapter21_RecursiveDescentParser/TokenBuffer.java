package Chapter21_RecursiveDescentParser;

import Chapter20_RegexTableLexer.Token;

public class TokenBuffer {
    private int position;
    public int getCurrentPosition() {
        return this.position;
    }

    public Token nextToken() {
        return null;
    }

    public void popToken() {

    }

    public void resetCurrentPosition(int p) {
        this.position = p;
    }
}
