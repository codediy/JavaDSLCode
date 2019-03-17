package Chapter17_DelimiterDirectedTranslation.MissGrantController;

import Chapter1_IntroductoryExample.Event;
import Chapter1_IntroductoryExample.State;

public class StateLineParser extends LineParser {
    /*当前State对象*/
    private State state;
    public StateLineParser(StateMachineParser context,State state) {
        super(context);
        this.state = state;
    }

    @Override
    void doParse() {
        if (hasOnlyWord("end")){
            returnToTopLevel();
        }else if(isTransiTIon()){
            processTransition();
        }else if(hasOnlyWord("actions")){
            processActions();
        }else{
            failToRecognizeLine();
        }
    }
    private boolean isTransiTIon() {
        return line.matches(".*=>.*");
    }
    private void processTransition() {
        String[] tokens = line.split("=>");
        Event trigger = context.getEvent(tokens[0].trim());
        State target = context.obtainState(tokens[1].trim());
        /*注册state的转换流程*/
        state.addTransition(trigger,target);
    }



    private void processActions() {
        for (String s : wordsStartStringWith(1)) {
            state.addAction(context.getCommand(s));
        }
    }


}
