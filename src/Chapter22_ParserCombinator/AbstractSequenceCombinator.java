package Chapter22_ParserCombinator;

public class AbstractSequenceCombinator  extends Combinator{
    private Combinator[] productions;
    private Boolean isOptional;
    public AbstractSequenceCombinator(boolean optional, Combinator[] productions) {
        this.productions = productions;
        this.isOptional = optional;
    }

    @Override
    public CombinatorResult recognizer(CombinatorResult inbound) {
        if (!inbound.matchSuccess()) return inbound;
        /*解析结果*/
        StateMachineMatchValue[] componentResults = new StateMachineMatchValue[productions.length];
        CombinatorResult latestResult = inbound;
        int productionIndex = 0;

        while (latestResult.matchSuccess() && productionIndex < productions.length){
            Combinator p = productions[productionIndex];
            latestResult = p.recognizer(latestResult);
            componentResults[productionIndex] = latestResult.getMatchValue();
            productionIndex = productionIndex + 1;
        }

        if (latestResult.matchSuccess()){
            action((componentResults));
        }else if(isOptional){
            latestResult = new CombinatorResult(inbound.getTokenBuffer(),true,new StateMachineMatchValue(""));
        }else{
            latestResult = new CombinatorResult(inbound.getTokenBuffer(),false,new StateMachineMatchValue(""));
        }

        return (latestResult);
    }
}
