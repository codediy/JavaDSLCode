package Chapter20_RegexTableLexer;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ScannerPatterns {
    /*Token类型*/
    public enum TokenTypes{
        TT_EVENT("^events",true),
        TT_RESET("^resetEvents",true),
        TT_COMMANDS("^commands",true),
        TT_END("^end",true),
        TT_STATE("^state",true),
        TT_ACTIONS("^actions",true),
        TT_LEFT("\\{",true),
        TT_RIGHT("^\\}",true),
        TT_TRANSITION("^=>",true),
        TT_IDENTIFIER("^(\\w)+",true),
        TT_WHITESPACE("^(\\s)+",true),
        TT_COMMENT("^\\\\(.)*$",true),
        TT_EOF("^EOF",false);

        private final String regExPattern;
        private final Boolean outputToken;

        TokenTypes(String regexPattern,Boolean output){
            this.regExPattern = regexPattern;
            this.outputToken = output;
        }
    }
    /*所有的正则表达式*/
    private static ArrayList<ScanRecognizer> patternMatchers;

    /*获取所有Token正则类型*/
    public static ArrayList<ScanRecognizer> LoadPatterns(){
        Pattern pattern;
        for (TokenTypes t : TokenTypes.values()) {
            pattern = Pattern.compile(t.regExPattern);
            patternMatchers.add(new ScanRecognizer(t,pattern,t.outputToken));
        }
        return patternMatchers;
    }
}
