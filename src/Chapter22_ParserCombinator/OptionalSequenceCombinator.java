package Chapter22_ParserCombinator;

/*可选序列组合字*/
public class OptionalSequenceCombinator extends AbstractSequenceCombinator {

    public OptionalSequenceCombinator(Combinator ... productions){
        super(true,productions);
    }
}
