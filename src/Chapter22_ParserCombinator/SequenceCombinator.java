package Chapter22_ParserCombinator;

public class SequenceCombinator extends AbstractSequenceCombinator {
    public SequenceCombinator(Combinator ... productions){
        super(false,productions);
    }
}
