package Chapter20_RegexTableLexer;

public class Token {

    private ScannerPatterns.TokenTypes token;
    private String content;

    public Token(ScannerPatterns.TokenTypes token, String content) {
        this.token = token;
        this.content = content;
    }
}
