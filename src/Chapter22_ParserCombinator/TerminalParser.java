package Chapter22_ParserCombinator;

import Chapter20_RegexTableLexer.ScannerPatterns;
import Chapter20_RegexTableLexer.Token;
import Chapter21_RecursiveDescentParser.TokenBuffer;

public class TerminalParser extends Combinator {
    private ScannerPatterns.TokenTypes tokenMatch;
    public TerminalParser(ScannerPatterns.TokenTypes match) {
        this.tokenMatch = match;
    }

    @Override
    public CombinatorResult recognizer(CombinatorResult inbound) {
        if (!inbound.matchSuccess()) return inbound;
        CombinatorResult result;
        TokenBuffer tokens = inbound.getTokenBuffer();
        Token t = tokens.nextToken();

        if (t.isTokenType(tokenMatch)){
            TokenBuffer outTokens = new TokenBuffer(tokens.makePoppedTokenList());
            result = new CombinatorResult(outTokens,true,new StateMachineMatchValue(t.getTokenValue()));
        }else{
            result = new CombinatorResult(tokens,false,new StateMachineMatchValue(""));
        }

        return result;
    }
}
