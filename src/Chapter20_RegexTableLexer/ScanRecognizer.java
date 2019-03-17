package Chapter20_RegexTableLexer;

import java.util.regex.Pattern;

/*所有待识别类型*/
public class ScanRecognizer {

    private ScannerPatterns.TokenTypes token;
    private Pattern tokenPattern;
    private boolean outputToken;

    public ScanRecognizer(ScannerPatterns.TokenTypes token, Pattern pattern,boolean outputToken){
        this.token = token;
        this.tokenPattern = pattern;
        this.outputToken = outputToken;

    }

    public Pattern getTokenPattern() {
        return tokenPattern;
    }

    public boolean isOutputToken() {
        return outputToken;
    }

    public ScannerPatterns.TokenTypes getToken() {
        return token;
    }
}
