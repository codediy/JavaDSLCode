package Chapter22_ParserCombinator;

import Chapter1_IntroductoryExample.Command;
import Chapter1_IntroductoryExample.Event;
import Chapter1_IntroductoryExample.State;
import Chapter1_IntroductoryExample.StateMachine;
import Chapter20_RegexTableLexer.ScannerPatterns;
import Chapter21_RecursiveDescentParser.TokenBuffer;

import java.util.ArrayList;
import java.util.Map;

public class StateMachineCombinatorParser  extends Combinator{
    /*匹配结果*/
    private TokenBuffer tokenBuffer;
    private StateMachine machineResult;

    private ArrayList<Event> machineEvents;
    private ArrayList<Command> machineCommands;
    private ArrayList<Event> resetEvents;
    private Map<String, State> matchineStates;
    private State partialState;

    /*终结符组合字*/
    private Combinator matchEventsKeyword = new TerminalParser(
            ScannerPatterns.TokenTypes.TT_EVENT
    );
    private Combinator matchResetKeyword = new TerminalParser(
            ScannerPatterns.TokenTypes.TT_RESET
    );
    private Combinator matchCommandKeyword = new TerminalParser(
            ScannerPatterns.TokenTypes.TT_COMMANDS
    );
    private Combinator matchEndKeyword = new TerminalParser(
            ScannerPatterns.TokenTypes.TT_END
    );
    private Combinator matchIdentifier = new TerminalParser(
            ScannerPatterns.TokenTypes.TT_IDENTIFIER
    );
    private Combinator matchTransitionOperator = new TerminalParser(
            ScannerPatterns.TokenTypes.TT_TRANSITION
    );
    private Combinator matchActionsKeyword = new TerminalParser(
            ScannerPatterns.TokenTypes.TT_ACTIONS
    );
    private Combinator matchLeftOperator = new TerminalParser(
            ScannerPatterns.TokenTypes.TT_LEFT
    );
    private Combinator matchRightOperator = new TerminalParser(
            ScannerPatterns.TokenTypes.TT_RIGHT
    );
    private Combinator matchStateKeyword = new TerminalParser(
            ScannerPatterns.TokenTypes.TT_STATE
    );
    /*Event组合字*/
    private Combinator matchEventDec = new EventDec(matchIdentifier,matchIdentifier);

    private Combinator matchEventDecList = new ListCombinator(matchEventDec);
    private Combinator matchEventBlock = new SequenceCombinator(
            matchEventsKeyword,matchEventDecList,matchEndKeyword
    );
    private Combinator matchEventList = new RestEventsList(matchIdentifier);

    /*ResetEvent组合字*/
    private Combinator matchResetBlock = new OptionalSequenceCombinator(
           matchResetKeyword,matchEventList,matchEndKeyword
    );

    /*Comand组合字*/
    private Combinator matchCommandDec = new CommandDec(matchIdentifier,matchIdentifier);
    private Combinator matchCommandList = new ListCombinator(matchCommandDec);
    private Combinator matchCommandBlock = new OptionalSequenceCombinator(
            matchCommandKeyword,matchCommandList,matchEndKeyword
    );

    /*State组合字*/
    private Combinator matchActionDec = new ActionDec(
            ScannerPatterns.TokenTypes.TT_IDENTIFIER
    );
    private Combinator matchActionList = new ListCombinator(matchActionDec);

    private Combinator matchActionBlock = new OptionalSequenceCombinator(
            matchActionsKeyword,matchLeftOperator,matchActionList,matchRightOperator
    );
    private Combinator matchTransition = new TransitionDec(
            matchIdentifier,matchTransitionOperator,matchIdentifier
    );
    private Combinator matchTransitionList = new ListCombinator(matchTransition);

    private Combinator matchStateName = new StateName(
            ScannerPatterns.TokenTypes.TT_IDENTIFIER
    );
    private Combinator matchStateDec = new StateDec(
            matchStateKeyword, matchStateName,
            matchActionBlock,
            matchTransitionList,
            matchEndKeyword
    );
    private Combinator matchStateList = new ListCombinator(matchStateDec);

    /*StateMachine组合字*/
    private Combinator matchStateMachine = new StateMachineDec(
            matchEventBlock,
            matchResetBlock,
            matchCommandBlock,
            matchStateList
    );

    private void addMachineEvent(Event event) {

    }
    private void addMachineCommand(Command command) {

    }
    private void addMachineState(State state){

    }

    @Override
    public CombinatorResult recognizer(CombinatorResult inbound) {
        return null;
    }

    /*简单内部类*/
    private class EventDec extends SequenceCombinator{
        public EventDec(Combinator... productions){
            super(productions);
        }

        public void action(StateMachineMatchValue...results){
            assert results.length == 2;
            addMachineEvent(new Event(results[0].getMatchString(),results[1].getMatchString()));
        }
    }
    private class RestEventsList extends ListCombinator {
        public RestEventsList(Combinator productions) {
            super(productions);
        }
    }

    private class CommandDec extends SequenceCombinator {
        public CommandDec(Combinator... productions){
            super(productions);
        }

        public void action(StateMachineMatchValue...results){
            assert results.length == 2;
            addMachineCommand(new Command(results[0].getMatchString(),results[1].getMatchString()));
        }
    }
    private class TransitionDec extends SequenceCombinator {
        public TransitionDec(Combinator... productions){
            super(productions);
        }

    }
    private class ActionDec extends TerminalParser {
        public ActionDec(ScannerPatterns.TokenTypes match){
            super(match);
        }
    }

    private class StateName extends TerminalParser {
        public StateName(ScannerPatterns.TokenTypes match){
            super(match);
        }
    }
    private class StateDec extends SequenceCombinator {
        public StateDec(Combinator... productions){
            super(productions);
        }
    }
    private class StateMachineDec extends SequenceCombinator {
        public StateMachineDec(Combinator... productions){
            super(productions);
        }
    }
}
