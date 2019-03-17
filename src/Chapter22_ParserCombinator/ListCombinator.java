package Chapter22_ParserCombinator;

import java.util.ArrayList;

public class ListCombinator extends Combinator {
    private Combinator production;
    public ListCombinator(Combinator productions) {
        this.production = productions;
    }

    @Override
    public CombinatorResult recognizer(CombinatorResult inbound) {
        if (!inbound.matchSuccess()) return inbound;
        CombinatorResult latestResult = inbound;
        StateMachineMatchValue returnValues[];
        ArrayList<StateMachineMatchValue> results = new ArrayList<>();

        while (latestResult.matchSuccess()){
            latestResult = production.recognizer(latestResult);
            if (latestResult.matchSuccess()){
                results.add(latestResult.getMatchValue());
            }
        }

        if (results.size() > 0){
            returnValues = results.toArray(new StateMachineMatchValue[results.size()]);
            action(returnValues);
            latestResult = new CombinatorResult(latestResult.getTokenBuffer(),true,new StateMachineMatchValue(""));
        }

        return (latestResult);
    }
}
