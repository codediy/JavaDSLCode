package Chapter22_ParserCombinator;

abstract class Combinator {
    public Combinator(){}

    public abstract CombinatorResult recognizer(CombinatorResult inbound);
    public void action(StateMachineMatchValue...results){

    }
}
