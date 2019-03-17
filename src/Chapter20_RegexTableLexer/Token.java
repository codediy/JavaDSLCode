package Chapter20_RegexTableLexer;

public class Token {

    private ScannerPatterns.TokenTypes token;
    private String tokenValue;

    public Token(ScannerPatterns.TokenTypes token, String value) {
        this.token = token;
        this.tokenValue = value;
    }

    public boolean isTokenType(ScannerPatterns.TokenTypes ttEvent) {
        return false;
    }

    public String getTokenValue() {
        return tokenValue;
    }
}
