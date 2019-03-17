package Chapter20_RegexTableLexer;

import Chapter21_RecursiveDescentParser.TokenBuffer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StateMachineTokenizer {

    private String scannerBuffer;
    private ArrayList<Token> tokenList;
    private TokenBuffer tokenBuffer;
    private ArrayList<ScanRecognizer> recognizerPatterns;

    public void parse() {
        boolean parseInProgress = true;
        while (parseInProgress) {
            Iterator<ScanRecognizer> patternIterator = recognizerPatterns.iterator();
            parseInProgress = matchToken(patternIterator);
        }
    }

    private boolean matchToken(Iterator<ScanRecognizer> patternIterator) {
        boolean tokenMatch;
        ScanRecognizer recognizer;
        Pattern pattern;
        Matcher matcher;
        boolean result;

        tokenMatch = false;
        result = true;

        do{
            recognizer = patternIterator.next();
            pattern = recognizer.getTokenPattern();
            matcher = pattern.matcher(scannerBuffer);
            if (matcher.find()){
                if (recognizer.isOutputToken()){
                    tokenList.add(new Token(recognizer.getToken(),matcher.group()));
                    /*21递归下降语法解析器token列表*/
                    tokenBuffer.addToken(new Token(recognizer.getToken(),matcher.group()));
                }
                tokenMatch  = true;
                /*截取部分*/
                scannerBuffer = scannerBuffer.substring(matcher.end());
            }

        }while(patternIterator.hasNext() && (!tokenMatch));

        if ((!tokenMatch) || matcher.end() == scannerBuffer.length()){
            result = false;
        }
        return result;
    }
}
