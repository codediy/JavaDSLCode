package Chapter22_ParserCombinator;

import Chapter21_RecursiveDescentParser.TokenBuffer;

public class CombinatorResult {

    private TokenBuffer tokens;
    private Boolean matchStatus;
    private StateMachineMatchValue matchValue;

    public CombinatorResult(TokenBuffer tokens, boolean matchStatus, StateMachineMatchValue matchValue) {
        this.tokens = tokens;
        this.matchStatus = matchStatus;
        this.matchValue = matchValue;
    }

    public boolean matchSuccess() {
        return false;
    }

    public TokenBuffer getTokenBuffer() {
        return tokens;
    }

    public StateMachineMatchValue getMatchValue() {
        return matchValue;
    }


}

